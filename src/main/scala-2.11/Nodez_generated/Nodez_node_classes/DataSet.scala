package Nodez_generated.Nodez_node_classes

/**
 * Created by simonshapiro on 04/11/15.
 */

import Nodez_core.archNodes
import Nodez_generated.Nodez_edge_classes._
import play.api.libs.json.{Json, JsValue}

import scala.collection.mutable


case class DataSet(
             name: String,
             version: String = null,
             description: String = null
               ) extends archNodes {
  override def toString: String = {
    """DataSet(
      |     name =  "%s",
      |     version = "%s",
      |     description = "%s"
      |     )
    """
    .stripMargin.format(name,version,description)
  }
  def toHashMap = {
    val properties = new mutable.HashMap[String, Any]
    properties("_ID_") =  "DataSet__" + name
    properties("TYPE") = "DataSet"
    properties("name") = name
    properties("version") = version
    properties("description") = description
    properties
  }
}
