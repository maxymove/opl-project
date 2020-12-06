object Main extends App {

  var g1 = new UndirectedGraph()
  var v11 = new Vertex('1')
  var v12 = new Vertex('2')
  var v13 = new Vertex('3')
  var v14 = new Vertex('4')
  var v15 = new Vertex('5')
  g1.addVertex(v11)
  g1.addVertex(v12)
  g1.addVertex(v13)
  g1.addVertex(v14)
  g1.addVertex(v15)
  g1.addEdge(v11, v12)
  g1.addEdge(v11, v13)
  g1.addEdge(v13, v14)

  g1.printShortestReach(v11)


  var g2 = new DirectedGraph()
  var v1 = new Vertex('A')
  var v2 = new Vertex('B')
  var v3 = new Vertex('C')
  var v4 = new Vertex('D')
  var v5 = new Vertex('E')
  var v6 = new Vertex('F')
  g2.addVertex(v1)
  g2.addVertex(v2)
  g2.addVertex(v3)
  g2.addVertex(v4)
  g2.addVertex(v5)
  g2.addVertex(v6)
  g2.addEdge(v3, v4)
  g2.addEdge(v4, v2)
  g2.addEdge(v5, v2)
  g2.addEdge(v5, v1)
  g2.addEdge(v6, v1)
  g2.addEdge(v6, v3)

  g2.topSort()

}
