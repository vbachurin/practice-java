package com.sundogsoftware.spark

import org.apache.spark.SparkContext
import org.apache.log4j.Logger
import org.apache.log4j.Level

object PurchaseByCustomer {
  
  def extractCustomerPricePairs(line: String) = {
    val fields = line.split(",")
    (fields(0).toInt, fields(2).toFloat)
  }
  
  def main(args: Array[String]): Unit = {
    
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    val sc = new SparkContext("local[*]", "PurchaseByCustomer")
    
    val input = sc.textFile("../customer-orders.csv")
    
    val rows = input.map(extractCustomerPricePairs)
    
    val reduce = rows.reduceByKey((x,y) => x + y)
    
    val flip = reduce.map(x => (x._2, x._1))
    
    val result = flip.sortByKey().collect()
    
    result.foreach(println);        
  }
}