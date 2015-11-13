package Nodez_generated.Nodez_node_classes

import java.util.NoSuchElementException

import Nodez_core.archNodes
import Nodez_generated.Nodez_edge_classes._
import play.api.libs.json.{JsValue, JsObject, Json}
import play.api.libs.json

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 * Created by simonshapiro on 04/11/15.
 */
case class System(
             name: String = null,
             version: String = null,
             description : String = null) extends archNodes {

  def CONNECTS(b: System): System_CONNECTS_System  = {
    val e = System_CONNECTS_System(name+"-[System_CONNECTS_System]->"+b.name, this, b)
    outboundEdges += e
    b.inboundEdges += e
    e
  }
  def PRODUCES(dataset: DataSet): System_PRODUCES_DataSet = {
    val e = System_PRODUCES_DataSet(name+"-[System_PRODUCES_DataSet]->"+dataset.name, this, dataset)
    outboundEdges += e
    dataset.inboundEdges += e
    e
  }
  def CONSUMES(dataset: DataSet): System_CONSUMES_DataSet = {
    val e = System_CONSUMES_DataSet(name+"-[System_CONSUMES_DataSet]->"+dataset.name, this, dataset)
    outboundEdges += e
    dataset.inboundEdges += e
    e
  }
  override def toString = {
    """System(
      |     name =  "%s",
      |     version = "%s",
      |     description = "%s"
      |     )
    """
    .stripMargin.format(name,version,description)
  }
  def toHashMap = {
    val properties = new mutable.HashMap[String, Any]
    properties("_ID_") =  "System__" + name
    properties("TYPE") = "System"
    properties("name") = name
    properties("version") = version
    properties("description") = description
    properties
  }
}
