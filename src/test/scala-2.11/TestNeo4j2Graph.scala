import Nodez_core.Neo4j2Graph
import Nodez_models.Model

/**
 * Created by simonshapiro on 12/11/15.
 */
object TestNeo4j2Graph extends App{

  val DB_LOCATION = "/Users/simonshapiro/IdeaProjects/Nodez_SBT/src/main/data/models/Wed_Nov_11_18_09_02_GMT_2015.graphdb"
  println(DB_LOCATION)

  val base = new Model()
  val g = Neo4j2Graph(DB_LOCATION).getAll
  println("Comparing\n" + base.model.compare(g).mkString("\n"))
//  assert(true)
}
