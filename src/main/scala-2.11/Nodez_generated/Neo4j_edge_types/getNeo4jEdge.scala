package Nodez_generated.Neo4j_edge_types

import org.neo4j.graphdb.RelationshipType

/**
 * Created by simonshapiro on 10/11/15.
 */

case class getNeo4jEdge() {
  def Type(t: String): RelationshipType = {

    object PRODUCES extends RelationshipType {
      override def name(): String = "PRODUCES"
    }
    object USES extends RelationshipType {
      override def name(): String = "USES"
    }
    object CONSUMES extends RelationshipType {
      override def name(): String = "CONSUMES"
    }
    object CONNECTS extends RelationshipType {
      override def name(): String = "CONNECTS"
    }
    object DEFAULT extends RelationshipType {
      override def name(): String = ""
    }
    t match {
      case "PRODUCES" => PRODUCES
      case "CONNECTS" => CONNECTS
      case "USES"     => USES
      case "CONSUMES" => CONSUMES
      case _          => DEFAULT
    }
  }
}
