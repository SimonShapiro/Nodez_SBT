package Nodez_core

import play.api.libs.json.JsValue

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.xml.Elem

/**
 * Created by simonshapiro on 04/11/15.
 */
trait archNodes {
  val name: String
  val outboundEdges = new ArrayBuffer[archEdges]
  val inboundEdges = new ArrayBuffer[archEdges]
  override def toString: String
  def getTypeAndName = {
    "%s__%s".format(this.getClass.toString.split('.').last,this.name)
  }
  def getType = {
    this.getClass.toString.split('.').last
  }
  def toHashMap: mutable.HashMap[String, Any]  // the HashMap may have additional 'properties' to support persistence model
}
