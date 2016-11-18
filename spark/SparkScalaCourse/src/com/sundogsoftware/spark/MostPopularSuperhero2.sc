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

  // Create a SparkContext using every core of the local machine
  val sc = new SparkContext("local[*]", "MostPopularSuperhero2")
                                                  //> Using Spark's default log4j profile: org/apache/spark/log4j-defaults.propert
                                                  //| ies
                                                  //| sc  : org.apache.spark.SparkContext = org.apache.spark.SparkContext@62577d6
                                                  //| 

	System.getProperty("user.dir")            //> res0: String = D:\Java\eclipse
	
  // Build up a hero ID -> name RDD
  val names = sc.textFile("marvel-names.txt")     //> names  : org.apache.spark.rdd.RDD[String] = marvel-names.txt MapPartitionsR
                                                  //| DD[1] at textFile at com.sundogsoftware.spark.MostPopularSuperhero2.scala:3
                                                  //| 3

  val namesRdd = names.flatMap(parseNames)        //> namesRdd  : org.apache.spark.rdd.RDD[(Int, String)] = MapPartitionsRDD[2] a
                                                  //| t flatMap at com.sundogsoftware.spark.MostPopularSuperhero2.scala:35

  val lines = sc.textFile("marvel-graph.txt")     //> lines  : org.apache.spark.rdd.RDD[String] = marvel-graph.txt MapPartitionsR
                                                  //| DD[4] at textFile at com.sundogsoftware.spark.MostPopularSuperhero2.scala:3
                                                  //| 7

  val pairings = lines.map(countCoOccurrences)    //> pairings  : org.apache.spark.rdd.RDD[(Int, Int)] = MapPartitionsRDD[5] at m
                                                  //| ap at com.sundogsoftware.spark.MostPopularSuperhero2.scala:39

  val totalFriendsByCharacter = pairings.reduceByKey((x, y) => x + y)
                                                  //> org.apache.hadoop.mapred.InvalidInputException: Input path does not exist: 
                                                  //| file:/D:/Java/eclipse/marvel-graph.txt
                                                  //| 	at org.apache.hadoop.mapred.FileInputFormat.singleThreadedListStatus(Fil
                                                  //| eInputFormat.java:287)
                                                  //| 	at org.apache.hadoop.mapred.FileInputFormat.listStatus(FileInputFormat.j
                                                  //| ava:229)
                                                  //| 	at org.apache.hadoop.mapred.FileInputFormat.getSplits(FileInputFormat.ja
                                                  //| va:315)
                                                  //| 	at org.apache.spark.rdd.HadoopRDD.getPartitions(HadoopRDD.scala:199)
                                                  //| 	at org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:248)
                                                  //| 	at org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:246)
                                                  //| 	at scala.Option.getOrElse(Option.scala:121)
                                                  //| 	at org.apache.spark.rdd.RDD.partitions(RDD.scala:246)
                                                  //| 	at org.apache.spark.rdd.MapPartitionsRDD.getPartitions(MapPartitionsRDD.
                                                  //| scala:35)
                                                  //| 	at org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(RDD.scala:248)
                                                  //| 	at org.apache.spark.rdd.RDD$$anonfun$partitions$2.apply(
                                                  //| Output exceeds cutoff limit.

  totalFriendsByCharacter.take(1)
  val flipped = totalFriendsByCharacter.map(x => (x._2, x._1))

  flipped.take(1)

  val mostPopular = flipped.max()
  
  val mostPopularName = namesRdd.lookup(mostPopular._2)(0)

  println(s"$mostPopularName is the most popular superhero with ${mostPopular._1} co-appearances.")
}