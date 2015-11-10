package Nodez_models

import Nodez_core.Graph
import Nodez_generated.Nodez_node_classes.{DataSet, System}

/**
 * Created by simonshapiro on 05/11/15.
 */

class Model() {

    val model = Graph(author = "SimonS")

    val optimus = System(
                        name="Optimus",
                        version="1.0",
                        description="A replacement for rapid that stores business forecasts (amongst other datsets)")
    val cyrus = System(
                      name="Cyrus",
                      version="1.0",
                      description="Cyrus is the Treasury Planning and Forecasting application.  it is due to go live in Q4 2015")
    val excel = System(
                      name="Microsoft Excel",
                      version="Multiple versions",
                      description="Spreadsheet software")

    val capplanInTemplate = DataSet(
                                  name="Capital Plan Input Templates",
                                  description = "Excel based capital plan templates")
    val capplanIn = DataSet(
                            name="Capital Plan Automated Input",
                            description = "Capital Plan conforming to Cyrus automated input requirements")
    val capplanOut = DataSet(
                            name="Capital Plan Output",
                            description = "Routine output the capital plan")
    val capplanOutAdHoc = DataSet(
                                  name="Capital Plan Ad-hoc Output",
                                  description = "Ad-hoc output of the capital plan")

    model <= optimus
    model <= cyrus
    model <= excel
    model <= capplanInTemplate
    model <= capplanIn
    model <= capplanOut
    model <= capplanOutAdHoc

    model <=> (excel PRODUCES capplanInTemplate)
    model <=> (optimus PRODUCES capplanIn)
    model <=> (cyrus CONSUMES capplanIn)
    model <=> (cyrus CONSUMES capplanInTemplate)
    model <=> (cyrus PRODUCES capplanOut)
    model <=> (cyrus PRODUCES capplanOutAdHoc)
//    model <=> (cyrus CONNECTS cyrus)

  println(optimus)
//  println(optimus.toJson.getClass)
//  val cl = System() fromJson optimus.toJson
  val ar = System() <-- (version = "fred", name = "bob")
//  println(cl)
}
