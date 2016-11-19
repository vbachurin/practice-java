package com.sundogsoftware.spark

import org.apache.spark.SparkContext
import org.apache.log4j._

object TenMostPopularSuperheroes {

  // Function to extract hero ID -> hero name tuples (or None in case of failure)
  def parseNames(line: String): Option[(Int, String)] = {
    var fields = line.split("\"")
    if (fields.length > 1) Some((fields(0).trim.toInt, fields(1))) else None
  }

  // Function to extract the hero ID and number of connections from each line
  def countCoOccurrences(line: String) = {
    var elements = line.split("\\s+")
    (elements(0).toInt, elements.length - 1)
  }

  def main(args: Array[String]) {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]", "MostPopularSuperhero2")

    val names = sc.textFile("../marvel-names.txt")

    val namesRdd = names.flatMap(parseNames)

    val lines = sc.textFile("../marvel-graph.txt")

    val pairings = lines.map(countCoOccurrences)

    val totalFriendsByCharacter = pairings.reduceByKey((x, y) => x + y)

    val flipped = totalFriendsByCharacter.map(x => (x._2, x._1))

    val mostPopular10 = flipped.takeOrdered(10)(Ordering[(Int, Int)].reverse)
    
    for (heroId <- mostPopular10) {
      val heroName = namesRdd.lookup(heroId._2)(0)
      println(s"$heroName is the superhero with ${heroId._1} co-appearances.")
    }

  }
}