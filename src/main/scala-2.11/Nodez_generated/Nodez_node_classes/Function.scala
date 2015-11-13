package Nodez_generated.Nodez_node_classes

import Nodez_core.archNodes
import Nodez_generated.Nodez_edge_classes._
import play.api.libs.json.{Json, JsValue}

import scala.collection.mutable
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
  override def toString: String = {
    """Function(
      |     name =  "%s"
      |     )
    """
      .stripMargin.format(name)
  }
  def toHashMap = {
    val properties = new mutable.HashMap[String, Any]
    properties("_ID_") =  "Function__" + name
    properties("TYPE") = "Function"
    properties("name") = name
    properties
  }
}
