package Nodez_core

import scala.xml.Elem
import scala.collection.immutable.HashMap

/**
 * Created by simonshapiro on 05/11/15.
 */



case class Graph2YedGraphML(g: Graph,
                            eReversedFN: (archEdges) => (Boolean, String),
                            eColourFN: (archEdges) => String,
                            nColourFN: (archNodes) => String,
                            nShapeFunction: (archNodes) => String) {

  val colourTable = HashMap("black" -> "#000000",
                            "red"   -> "#ff0000",
                            "grey"  -> "#ccccff",
                            "yellow"-> "#ffcc00")
  def getColourFromTable(c: String): String = {
    val DEFAULT_COLOUR = "#ffffff"
    if(colourTable.contains(c)) colourTable(c)
    else DEFAULT_COLOUR
  }

  def node2YedGraphML(n: archNodes): Elem = {
    <node id={"@" + n.hashCode.toString}>
      <data key="d0">
        <y:ShapeNode selected="false">
          <y:Geometry x="170.5" y="-15.0" width="59.0" height="30.0"/>
          <y:Fill color={getColourFromTable(nColourFN(n))} transparent="false"/>
          <y:BorderStyle type="line" width="1.0" color="#000000"/>
          <y:NodeLabel>{n.name}</y:NodeLabel>
          <y:Shape type={nShapeFunction(n)}/>
        </y:ShapeNode>
      </data>
    </node>
  }

  case class edgeControl(
                          reversed: Boolean = false,
                          colour: String = "#ff0000" ) {
  }

  def edge2YedGraphML(e: archEdges) = {
    //is there coding to do the display in reverse on some items?
    <edge id={"@" + e.hashCode.toString}
          source={if (eReversedFN(e)._1) "@" + e.toNode.hashCode.toString else "@" + e.fromNode.hashCode.toString}
          target={if (eReversedFN(e)._1) "@" + e.fromNode.hashCode.toString else "@" + e.toNode.hashCode.toString}>
      <data key="d1">
        <y:PolyLineEdge>
          <y:LineStyle type="line" width="1.0" color={getColourFromTable(eColourFN(e))}/>
          <y:Arrows source="none" target="standard"/>
          <y:EdgeLabel>{if (eReversedFN(e)._1) eReversedFN(e)._2 else e.getClass.toString.split('.').last.split('_')(1)}</y:EdgeLabel>
          <y:BendStyle smoothed="false"/>
        </y:PolyLineEdge>
      </data>
    </edge>
  }

  val edgeDefault = edgeControl()

  def asXML =
    <graphml xmlns="http://graphml.graphdrawing.org/xmlns"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://graphml.graphdrawing.org/xmlns
           http://www.yworks.com/xml/schema/graphml/1.1/ygraphml.xsd"
             xmlns:y="http://www.yworks.com/xml/graphml">
      <key id="d0" for="node" yfiles.type="nodegraphics"/>
      <key id="d1" for="edge" yfiles.type="edgegraphics"/>
      <graph id="G" edgedefault="directed">
        {g.nodes.map {n => {node2YedGraphML(n._2)}}}
        {g.edges.map {e => {edge2YedGraphML(e._2)}}}
      </graph>
    </graphml>

}
