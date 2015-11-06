package Nodez_core

import scala.collection.mutable.ArrayBuffer
import scala.xml.Elem

/**
 * Created by simonshapiro on 04/11/15.
 */
trait archNodes {
  val name: String
  val outboundEdges = new ArrayBuffer[archEdges]
  val inboundEdges = new ArrayBuffer[archEdges]
}
