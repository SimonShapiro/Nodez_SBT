package Nodez_core

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer


/**
 * Created by simonshapiro on 02/11/15.
 */


case class Graph(author: String=null) {
  val nodes = new mutable.HashMap[String,archNodes]
  val edges = new mutable.HashMap[String,archEdges]

  def <= (node: archNodes): Graph = {
    val nodeName = node.getClass.toString.split(" ")(1).split('.').last + "__" + node.name
    nodes(nodeName) = node
    this
  }
  def <=> (edge: archEdges): Graph = {
    edges(edge.name) = edge
    this
  }
  override def toString: String = {
    val header =
    """Auto generated graph.
Author: %s
""".format(author)
    val nodeString = new ArrayBuffer[String]
//    nodes.foreach(node => {nodeString += node._2.toYedGraphML(id="@"+node._2.hashCode.toString,"rectangle").toString})
    val edgeString = new ArrayBuffer[String]
//    edges.foreach(edge => {edgeString += edge._2.toYedGraphML(id="@"+edge._2.hashCode.toString,
//                    "@"+edge._2.fromNode.hashCode.toString,
//                    edge._2.getClass.toString.split(" ")(1).split('.').last,
//                    "@"+edge._2.toNode.hashCode.toString,
//                    "red").toString})
    header + nodeString.mkString + edgeString.mkString
  }
}
