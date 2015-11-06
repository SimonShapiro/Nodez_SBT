package Nodez_generated.Nodez_node_classes

import Nodez_core.archNodes
import Nodez_generated.Nodez_edge_classes._

import scala.collection.mutable.ArrayBuffer

/**
 * Created by simonshapiro on 04/11/15.
 */
case class Function(
                 name: String
                   ) extends archNodes {
  def USES(s: System): Function_USES_System = {
    val e = Function_USES_System(name+"-[Person_USES_System]->"+s.name,this,s)
    outboundEdges += e
    s.inboundEdges += e
    e
  }
}
