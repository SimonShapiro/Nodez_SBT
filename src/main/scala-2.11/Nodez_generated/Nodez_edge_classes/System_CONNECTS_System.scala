package Nodez_generated.Nodez_edge_classes

import Nodez_core.{archNodes, archEdges}

import scala.collection.mutable

/**
 * Created by simonshapiro on 04/11/15.
 */
case class System_CONNECTS_System(name: String, fromNode: archNodes, toNode: archNodes) extends archEdges {
  def toHashMap: mutable.HashMap[String, Any] = {
    val properties = new mutable.HashMap[String, Any]
    properties("_ID_") = name
    properties
  } // the HashMap may have additional 'properties' to support persistence model
}
