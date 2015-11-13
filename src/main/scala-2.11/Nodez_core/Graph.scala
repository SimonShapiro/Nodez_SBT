package Nodez_core

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer


/**
 * Created by simonshapiro on 02/11/15.
 */


case class Graph(author: String=null) extends GraphInterface {
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

  def merge(g1: Graph): Graph = {
    val g = Graph("merged")
    this.nodes.foreach(n => g <= n._2)
    this.edges.foreach(e => g <=> e._2)
    g1.nodes.foreach(n => g <= n._2)
    g1.edges.foreach((e => g <=> e._2))
    g
  }

  def compare(g1: Graph): ArrayBuffer[String] = {
    val result = new ArrayBuffer[String]()
    if (author == g1.author) result += "Authors match" else result += "Exepcting author= %s, found %s".format(author, g1.author)
    if (nodes.size == g1.nodes.size) result += "Node size match" else result += "Exepcting author= %s, found %s".format(nodes.size,g1.nodes.size)
    if (edges.size == g1.edges.size) result += "Node size match" else result += "Exepcting author= %s, found %s".format(edges.size,g1.edges.size)
    nodes.foreach(n => {
//      if (n.)
    })
    new ArrayBuffer[String]()
  }
}
