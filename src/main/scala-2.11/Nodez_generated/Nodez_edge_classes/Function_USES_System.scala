package Nodez_generated.Nodez_edge_classes

import Nodez_core.{archNodes, archEdges}

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
}
