
import Nodez_core.{archNodes, archEdges, Graph2YedGraphML, Graph}
import Nodez_generated.Nodez_node_classes._
import Nodez_models.Model

import scala.collection.mutable.HashMap

object scratchPad extends App {

//  val eControl = new HashMap[String, edgeControl]
//  eControl("System_CONSUMES_DataSet") = new edgeControl(reversed = true, colour = "#ff00ff")

  // need to find a way to express style rules to Yed as parameters to Graph2YedGraphML

  // when edgetype = xx, base style y on data in edge

  // eg when edgetype = 'consumes' base 'line color' on edge.fromNode.owner => color table
  def edgeColourFunction (e: archEdges): String = {
    e.getClass.toString.split('.').last match {
      case "System_CONSUMES_DataSet" => "red"
      case _ => "black"

    }
  }

  def edgeDirectionReversed(e: archEdges): (Boolean, String) = {
    e.getClass.toString.split('.').last match {
      case "System_CONSUMES_DataSet" => (true,"CONSUMED BY")
      case _                         => (false,null)
    }
  }

  def nodeShapeFunction(n :archNodes): String = {
    n.getClass.toString.split('.').last match {
      case "System" => "rectangle"
      case "DataSet" => "ellipse"
      case _         => "diamond"
    }
  }

  def nodeColourFunction(n :archNodes): String = {
    n.getClass.toString.split('.').last match {
      case "System" => "grey"
      case "DataSet" => "yellow"
      case _         => "red"
    }
  }

  val arch = new Model()
  arch.model.edges.foreach(e => println(edgeColourFunction(e._2)))

  println(Graph2YedGraphML(arch.model, edgeDirectionReversed, edgeColourFunction, nodeColourFunction, nodeShapeFunction).asXML.toString)
}
