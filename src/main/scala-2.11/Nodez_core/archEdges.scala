package Nodez_core

import scala.collection.mutable

/**
 * Created by simonshapiro on 04/11/15.
 */
trait archEdges {
  val name: String
  val fromNode: archNodes
  val toNode: archNodes
  override def toString: String
  def toHashMap: mutable.HashMap[String, Any]  // the HashMap may have additional 'properties' to support persistence model
}
