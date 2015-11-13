package Nodez_core

import java.io.File
import java.util.{NoSuchElementException, Calendar}

import Nodez_generated.Neo4jIntegration.getNeo4jEdge
import org.neo4j.graphdb.{Label, Transaction}
import org.neo4j.graphdb.factory.GraphDatabaseFactory

import scala.collection.mutable

/**
 * Created by simonshapiro on 08/11/15.
 */

case class Graph2Neo4j (g: Graph) {
  val DB_PATH = "/Users/simonshapiro/IdeaProjects/Nodez_SBT/src/main/data/models/"

  val gNeo4jMap = new mutable.HashMap[String, Long]
  val dateQualifier = Calendar.getInstance.getTime.toString.replace(".","_").replace(":","_").replace(" ","_")

  val graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(new File(DB_PATH+dateQualifier+".graphdb"))  //seems to reset the whole directory

  def withTransaction (doWithTransaction: Transaction => Unit) {
    val tempTx = graphDb.beginTx()

    try {
      doWithTransaction(tempTx)
    }
    finally {
      tempTx.close()
    }
  }
  withTransaction(tx => {
    g.nodes.foreach(n => {
      val aka = n._2.getTypeAndName
      val dbNode = graphDb.createNode
      gNeo4jMap(aka) = dbNode.getId
      dbNode.addLabel(new Label {
        override def name = n._2.getClass.toString.split('.').last
      })
      n._2.toHashMap.foreach(p => {
        if (p._2 != null) dbNode.setProperty(p._1, p._2)
      })
    })
    g.edges.foreach(e => {
      val fromNodeId = gNeo4jMap(e._2.fromNode.getTypeAndName)
      val toNodeId   = gNeo4jMap(e._2.toNode.getTypeAndName)
      val fromNode = graphDb.getNodeById(fromNodeId)
      val toNode   = graphDb.getNodeById(toNodeId)
      val edgeType = e._2.name.split('_')(1)
      val neo4jEdge = getNeo4jEdge().Type(edgeType)
      val edge = fromNode.createRelationshipTo(toNode,neo4jEdge)
      e._2.toHashMap.foreach(p => {
        if (p._2 != null) edge.setProperty(p._1,p._2)
      })
//      val relationship = firstNode.createRelationshipTo(secondNode, KNOWS)
      println(e._2.fromNode,e._2.toNode)
    })
    tx.success()
  })
  graphDb.shutdown()
}
