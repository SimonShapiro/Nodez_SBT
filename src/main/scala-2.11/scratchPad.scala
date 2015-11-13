
import Nodez_core._
import Nodez_generated.Nodez_node_classes._
import Nodez_models.Model
import java.io._

import scala.collection.mutable
import scala.collection.mutable.HashMap

object scratchPad extends App {

//  val eControl = new HashMap[String, edgeControl]
//  eControl("System_CONSUMES_DataSet") = new edgeControl(reversed = true, colour = "#ff00ff")

  // need to find a way to express style rules to Yed as parameters to Graph2YedGraphML

  // when edgetype = xx, base style y on data in edge

  // eg when edgetype = 'consumes' base 'line color' on edge.fromNode.owner => color table

  val edgeGraphControls = new mutable.HashMap[String, archEdges => Any]
  edgeGraphControls("edge colour") = {(e: archEdges) =>
                              e.getClass.toString.split('.').last match {
                                case "System_CONSUMES_DataSet" => "red"
                                case _ => "black"
                                }
                              }
  edgeGraphControls("edge direction reversed") = {(e: archEdges) =>
                              e.getClass.toString.split('.').last match {
                                case "System_CONSUMES_DataSet" => true
                                case _                         => false
                                }
                              }

  edgeGraphControls("edge label") = {( e: archEdges) =>
                              e.getClass.toString.split('.').last match {
                                case "System_CONSUMES_DataSet" => ""
                                case "System_CONNECTS_System" => ""
                                case "System_PRODUCES_DataSet" => ""
                                case _ => e.name.split('_')(1)
                                  }
                              }

  val nodeGraphControls = new mutable.HashMap[String, archNodes => Any]
  nodeGraphControls("node colour") = { (n: archNodes) =>
                              n.getClass.toString.split('.').last match {
                                case "System" => "grey"
                                case "DataSet" => "yellow"
                                case _ => "red"
                              }
                            }
  nodeGraphControls("node shape") = {(n :archNodes) =>
    n.getClass.toString.split('.').last match {
      case "System" => "rectangle"
      case "DataSet" => "ellipse"
      case _         => "diamond"
    }}

  val arch = new Model()
  arch.model.nodes.foreach(n => println(n._1))
  println(nodeGraphControls("node colour"){arch.cyrus})
//  arch.model.edges.foreach(e => println(edgeColourFunction(e._2)))
  val pw = new PrintWriter(new File("/Users/simonshapiro/IdeaProjects/Nodez_SBT/src/main/data/out.graphml" ))
  pw.write(Graph2YedGraphML(arch.model, nodeGraphControls, edgeGraphControls).asXML.toString)
  pw.close()
  Graph2Neo4j(arch.model)
//  println(Graph2YedGraphML(arch.model, nodeGraphControls, edgeGraphControls).asXML.toString)
}
