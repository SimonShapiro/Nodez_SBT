package Nodez_core

import scala.collection.mutable

/**
 * Created by simonshapiro on 07/11/15.
 */
trait GraphInterface {
  val author: String
  val nodes: mutable.HashMap[String,archNodes]
  val edges: mutable.HashMap[String,archEdges]
  def <= (node: archNodes): Graph
  def <=> (edge: archEdges): Graph
  def toString: String
  }
