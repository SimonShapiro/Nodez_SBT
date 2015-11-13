package Nodez_core

import scala.xml.Elem
import scala.collection.mutable.HashMap

/**
 * Created by simonshapiro on 05/11/15.
 */

case class Graph2YedGraphML(g: Graph, nodeGraphControls: HashMap[String, archNodes => Any] = null, edgeGraphControls: HashMap[String, archEdges => Any] = null) {

  private val colourTable = HashMap("black" -> "#000000",
                            "red"   -> "#ff0000",
                            "grey"  -> "#ccccff",
                            "yellow"-> "#ffcc00")

  private val DEFAULT_COLOUR = "#555555"
  private val DEFAULT_SHAPE = "ellipse"

  private def getColourFromTable(c: String): String = {
    if(colourTable.contains(c)) colourTable(c)
    else DEFAULT_COLOUR
  }

  private def node2YedGraphML(n: archNodes): Elem = {
    val ccode = if (nodeGraphControls.contains("node colour")) nodeGraphControls("node colour")(n) else DEFAULT_COLOUR
    val col = getColourFromTable(ccode.toString)
    val shape = if (nodeGraphControls.contains("node shape")) nodeGraphControls("node shape")(n) else DEFAULT_SHAPE
    <node id={"@" + n.hashCode.toString}>
      <data key="d0">
        <y:ShapeNode selected="false">
          <y:Geometry x="170.5" y="-15.0" width="59.0" height="30.0"/>
          <y:Fill color={col} transparent="false"/>
          <y:BorderStyle type="line" width="1.0" color="#000000"/>
          <y:NodeLabel>{n.name}</y:NodeLabel>
          <y:Shape type={shape.toString}/>
        </y:ShapeNode>
      </data>
    </node>
  }

  private def edge2YedGraphML(e: archEdges) = {
    val reversed = if (edgeGraphControls.contains("edge direction reversed")) edgeGraphControls("edge direction reversed")(e) else false
    val label = if (edgeGraphControls.contains("edge label")) edgeGraphControls("edge label")(e) else "...>"
    val cCode = if (edgeGraphControls.contains("edge colour")) edgeGraphControls("edge colour")(e) else DEFAULT_COLOUR
    val eColour = getColourFromTable((cCode).toString)
    //is there coding to do the display in reverse on some items?
    <edge id={"@" + e.hashCode.toString}
          source={if (reversed.asInstanceOf[Boolean]) "@" + e.toNode.hashCode.toString else "@" + e.fromNode.hashCode}
          target={if (reversed.asInstanceOf[Boolean]) "@" + e.fromNode.hashCode.toString else "@" + e.toNode.hashCode}>
      <data key="d1">
        <y:PolyLineEdge>
          <y:LineStyle type="line" width="1.0" color={eColour.toString}/>
          <y:Arrows source="none" target="standard"/>
          <y:EdgeLabel>{label}</y:EdgeLabel>
          <y:BendStyle smoothed="false"/>
        </y:PolyLineEdge>
      </data>
    </edge>
  }

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
