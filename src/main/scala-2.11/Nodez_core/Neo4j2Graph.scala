package Nodez_core

import java.util.{NoSuchElementException, Calendar}

import Nodez_generated.Neo4jIntegration.getNeo4jEdge
import Nodez_generated.Neo4jIntegration.subGraphBasedOn
import org.neo4j.graphdb.{Label, Transaction}
import org.neo4j.graphdb.factory.GraphDatabaseFactory
import org.neo4j.kernel.impl.core.NodeProxy

import scala.collection.mutable

import org.neo4j.graphdb.{Label, Transaction, RelationshipType, GraphDatabaseService}
import org.neo4j.graphdb.factory._
import org.neo4j.kernel.impl.core.NodeProxy
import org.neo4j.tooling.GlobalGraphOperations
import scala.collection.JavaConverters._
import java.io.File


/**
 * Created by simonshapiro on 12/11/15.
 */
case class Neo4j2Graph(Neo4jDbLocation: String){


  def getAll: Graph = {
    val graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(new File(Neo4jDbLocation))  //seems to reset the whole directory

    def withTransaction (doWithTransaction: Transaction => Unit) {
      val tempTx = graphDb.beginTx()

      try {
        doWithTransaction(tempTx)
      }
      finally {
        tempTx.close()
      }
    }

    /*
    recursive
      if tail empty: return g
      else merge subgraph head, recursive tail

    def processNodes(i: Iterator) = {
      println(i)
    }
     */

    var g = new Graph(author = "Neo4j")
    withTransaction( tx => {
      val ret = graphDb.execute("MATCH (n) return n")
      println(ret.asScala,getClass)

      ret.asScala.foreach(r => {
//        val g1 = new Graph("source")
        val neoNodeProxy = r.values.toArray()(0).asInstanceOf[NodeProxy]
//        val properties = neoNodeProxy.getAllProperties.asScala
//        val nodeId = "Neo" + neoNodeProxy.getId
        val subGraph = subGraphBasedOn(neoNodeProxy).makeCompleteNode
        g = g.merge(subGraph)
//        val g2 = g.merge(g1)
//        val g3 = g2.merge(g1)
//        println(g3.getClass)
        println(neoNodeProxy.getLabels)
      })

      println(ret.getClass)
      tx.success()
    })
    g
  }
}
