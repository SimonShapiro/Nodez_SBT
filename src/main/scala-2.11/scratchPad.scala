
import Nodez_core.{archNodes, archEdges, Graph2YedGraphML, Graph}
import Nodez_generated.Nodez_node_classes._
import Nodez_models.Model
import java.io._

import scala.collection.mutable.HashMap

object scratchPad extends App {

//  val eControl = new HashMap[String, edgeControl]
//  eControl("System_CONSUMES_DataSet") = new edgeControl(reversed = true, colour = "#ff00ff")

  // need to find a way to express style rules to Yed as parameters to Graph2YedGraphML

  // when edgetype = xx, base style y on data in edge

  // eg when edgetype = 'consumes' base 'line color' on edge.fromNode.owner => color table

  val edgeGraphControls = new HashMap[String, archEdges => Any]
  edgeGraphControls("edge colour") = {(e: archEdges) =>
                              e.getClass.toString.split('.').last match {
                                case "System_CONSUMES_DataSet" => "red"
                                case _ => "black"
                                }
                              }
  edgeGraphControls("edge direction reversed") = {(e: archEdges) =>
                              e.getClass.toString.split('.').last match {
                                case "System_CONSUMES_DataSet" => (true,"CONSUMED BY")
                                case _                         => (false,null)
                                }
                              }

  val nodeGraphControls = new HashMap[String, archNodes => Any]
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
  println(nodeGraphControls("node colour"){arch.cyrus})
//  arch.model.edges.foreach(e => println(edgeColourFunction(e._2)))
  val pw = new PrintWriter(new File("/Users/simonshapiro/IdeaProjects/Nodez_SBT/src/main/data/out.graphml" ))
  pw.write(Graph2YedGraphML(arch.model, nodeGraphControls, edgeGraphControls).asXML.toString)
  pw.close

//  println(Graph2YedGraphML(arch.model, nodeGraphControls, edgeGraphControls).asXML.toString)
}
