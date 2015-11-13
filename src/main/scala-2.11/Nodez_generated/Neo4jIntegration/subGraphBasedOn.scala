package Nodez_generated.Neo4jIntegration

import Nodez_core.{archEdges, archNodes, Graph}
import Nodez_generated.Nodez_node_classes._
import org.neo4j.graphdb.{Node, Relationship, RelationshipType, Direction}


import scala.collection.mutable
import scala.collection.mutable.Map
import org.neo4j.kernel.impl.core.{RelationshipProxy, NodeProxy}
import scala.collection.JavaConverters._

/**
 * Created by simonshapiro on 12/11/15.
 */
case class subGraphBasedOn(nodeProxy: NodeProxy) {

  private def makeArchNodeFromPropertyMap(properties: Map[String, Object]): archNodes = {
    properties("TYPE").toString match {
      case "System" => {
        System(
          name=properties("name").toString,
          version = if(properties.contains("version")) properties("version").toString else null,
          description = if(properties.contains("description")) properties("description").toString else null
        )}
      case "Function" => Function(properties("name").toString)
      case "DataSet" => DataSet(properties("name").toString)
        // case ......................... etc GENERATED
    }
  }

  private def makeEdge(start: Node, rel: Relationship, end: Node)  = { // : archEdges
    val startArchNode = makeArchNodeFromPropertyMap(start.getAllProperties.asScala)
    val endArchNode = makeArchNodeFromPropertyMap(end.getAllProperties.asScala)
    println("Ready to make an edge %s %s %s".format(startArchNode.getType,rel.getType.name,endArchNode.getType))
    (startArchNode.getType,rel.getType.name,endArchNode.getType) match {
      case ("System","PRODUCES","DataSet") => {
        startArchNode.asInstanceOf[System].PRODUCES(endArchNode.asInstanceOf[DataSet])
      }  // startArchNode PRODUCES endArchNode
      case ("System","CONSUMES","DataSet") => {
        startArchNode.asInstanceOf[System].CONSUMES(endArchNode.asInstanceOf[DataSet])
      }  // startArchNode PRODUCES endArchNode
      case ("System","CONNECTS","System") => {
        startArchNode.asInstanceOf[System].CONNECTS(endArchNode.asInstanceOf[System])
      }  // startArchNode PRODUCES endArchNode
      case ("Function","USES","System") => {
        startArchNode.asInstanceOf[Function].USES(endArchNode.asInstanceOf[System])
      }  // startArchNode PRODUCES endArchNode
//      case _ => println("UNKNOWN!")
    }
  }

  def makeCompleteNode: Graph = {
    val g = new Graph("Single node - complete")
    val properties = nodeProxy.getAllProperties.asScala
//    val focusNode = makeArchNodeFromPropertyMap(properties)
    val relationships = nodeProxy.getRelationships.asScala
    relationships.foreach(rel => {
      println("rel",rel.getClass)
      val startNode = rel.getStartNode
      g <= makeArchNodeFromPropertyMap(startNode.getAllProperties.asScala)
      val endNode = rel.getEndNode
      g <= makeArchNodeFromPropertyMap(endNode.getAllProperties.asScala)
      println(startNode.getProperty("name"), rel.getType, endNode.getProperty("name"))
      g <=> makeEdge(startNode, rel, endNode)
    })
//    focusNode
    g
  }
}
