package com.sundogsoftware.spark

import org.apache.spark.SparkContext
import org.apache.log4j.Logger
import org.apache.log4j.Level

object MostPopularSuperhero2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  // Function to extract hero ID -> hero name tuples (or None in case of failure)
  def parseNames(line: String): Option[(Int, String)] = {
    var fields = line.split("\"")
    if (fields.length > 1) Some((fields(0).trim.toInt, fields(1))) else None
  }                                               //> parseNames: (line: String)Option[(Int, String)]

  println(parseNames("1\"A"))                     //> Some((1,A))

  def countCoOccurrences(line: String) = {
    var elements = line.split("\\s+")
    (elements(0).toInt, elements.length - 1)
  }                                               //> countCoOccurrences: (line: String)(Int, Int)

  Logger.getLogger("org").setLevel(Level.ERROR)

  print(countCoOccurrences("5988 748 1722 3752 4655 5743 1872 3413 5527 6368 6085 4319 4728 1636 2397 3364 "))
                                                  //> (5988,15)

  val sc = new SparkContext("local[*]", "MostPopularSuperhero2")
                                                  //> Using Spark's default log4j profile: org/apache/spark/log4j-defaults.propert
                                                  //| ies
                                                  //| sc  : org.apache.spark.SparkContext = org.apache.spark.SparkContext@6b5f8707
                                                  //| 

  System.getProperty("user.dir")                  //> res0: String = D:\Java\eclipse
  

  // Build up a hero ID -> name RDD
  val names = sc.textFile("marvel-names.txt")     //> names  : org.apache.spark.rdd.RDD[String] = marvel-names.txt MapPartitionsRD
                                                  //| D[1] at textFile at com.sundogsoftware.spark.MostPopularSuperhero2.scala:33
                                                  //| 

  val namesRdd = names.flatMap(parseNames)        //> namesRdd  : org.apache.spark.rdd.RDD[(Int, String)] = MapPartitionsRDD[2] a
                                                  //| t flatMap at com.sundogsoftware.spark.MostPopularSuperhero2.scala:35

  val lines = sc.textFile("marvel-graph.txt")     //> lines  : org.apache.spark.rdd.RDD[String] = marvel-graph.txt MapPartitionsR
                                                  //| DD[4] at textFile at com.sundogsoftware.spark.MostPopularSuperhero2.scala:3
                                                  //| 7

  val pairings = lines.map(countCoOccurrences)    //> pairings  : org.apache.spark.rdd.RDD[(Int, Int)] = MapPartitionsRDD[5] at m
                                                  //| ap at com.sundogsoftware.spark.MostPopularSuperhero2.scala:39

  val totalFriendsByCharacter = pairings.reduceByKey((x, y) => x + y)
                                                  //> totalFriendsByCharacter  : org.apache.spark.rdd.RDD[(Int, Int)] = ShuffledR
                                                  //| DD[6] at reduceByKey at com.sundogsoftware.spark.MostPopularSuperhero2.scal
                                                  //| a:41

  val flipped = totalFriendsByCharacter.map(x => (x._2, x._1))
                                                  //> flipped  : org.apache.spark.rdd.RDD[(Int, Int)] = MapPartitionsRDD[7] at ma
                                                  //| p at com.sundogsoftware.spark.MostPopularSuperhero2.scala:43
  
  val mostPopular = flipped.max()                 //> [Stage 0:>                                                          (0 + 2
                                                  //| ) / 2][Stage 0:=============================>                             
                                                  //| (1 + 1) / 2]                                                              
                                                  //|                   mostPopular  : (Int, Int) = (1933,859)
  val mostPopularName = namesRdd.lookup(mostPopular._2)(0)
                                                  //> mostPopularName  : String = CAPTAIN AMERICA
  
  println(s"$mostPopularName is the most popular superhero with ${mostPopular._1} co-appearances.")
                                                  //> CAPTAIN AMERICA is the most popular superhero with 1933 co-appearances.
}