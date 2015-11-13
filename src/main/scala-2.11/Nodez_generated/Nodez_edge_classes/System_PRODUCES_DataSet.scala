package Nodez_generated.Nodez_edge_classes

import Nodez_core.{archNodes, archEdges}

import scala.collection.mutable

/**
 * Created by simonshapiro on 04/11/15.
 */
case class System_PRODUCES_DataSet(name: String, fromNode: archNodes, toNode: archNodes) extends archEdges {
  var volume: Long  = 0
  def WITH (v: Long) = {
    this.volume = v
    this
  }
  def toHashMap: mutable.HashMap[String, Any] = {
    val properties = new mutable.HashMap[String, Any]
    properties("_ID_") = name
    properties("volume") = volume
    properties
  } // the HashMap may have additional 'properties' to support persistence model
}
