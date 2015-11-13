package Nodez_generated.Nodez_edge_classes

import Nodez_core.{archNodes, archEdges}

import scala.collection.mutable

/**
 * Created by simonshapiro on 04/11/15.
 */
case class Function_USES_System(name: String, fromNode: archNodes, toNode: archNodes) extends archEdges {
  var importance: Int = 5
  var risk: String = "low"
  def WITH(risk: String, importance: Int): Function_USES_System = {
    this.importance = importance
    this.risk = risk
    this
  }
  def toHashMap: mutable.HashMap[String, Any] = {
    val properties = new mutable.HashMap[String, Any]
    properties("_ID_") = name
    properties("importance") = importance
    properties("risk") = risk
    properties
  } // the HashMap may have additional 'properties' to support persistence model
}
