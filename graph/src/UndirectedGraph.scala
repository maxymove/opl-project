import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class UndirectedGraph {

  var numV = 0
  var numE = 0
  var vertices = new ArrayBuffer[Vertex]()
  var edges = new ArrayBuffer[Edge]()

  def addVertex(label: Char): Unit = {
    var v1 = new Vertex(label)
    if (!this.vertices.contains(v1)) {
      vertices.addOne(v1)
      this.numV += 1
    }
  }

  def addVertex(v1: Vertex): Unit = {
    if (!this.vertices.contains(v1)) {
      vertices.addOne(v1)
      this.numV += 1
    }
  }

  def addEdge(v1: Vertex, v2: Vertex): Unit = {
    val e1 = new Edge(v1, v2)
    val e2 = new Edge(v2, v1)
    this.edges.addOne(e1)
    this.edges.addOne(e2)
    this.numE += 2
  }

  def getNbrs(v1: Vertex): ArrayBuffer[Vertex] = {
    this.edges.filter(_.edge(0) == v1).map(_.edge(1))
  }

  def bfs(s: Vertex): ArrayBuffer[Vertex] = {
    var walk = new ArrayBuffer[Vertex]()
    var visitted = new mutable.HashSet[Vertex]()
    var bfsQ = new mutable.Queue[Vertex]()
    visitted += s
    bfsQ += s
    while (!bfsQ.isEmpty) {
      val curr = bfsQ.dequeue()
      walk.addOne(curr)
      visitted.add(curr)
      bfsQ.enqueueAll(getNbrs(curr).filter(!visitted.contains(_)))
    }
    walk
  }

  def shortestReach(s: Vertex): mutable.HashMap[Vertex, Int] = {
    var dis = new mutable.HashMap[Vertex, Int]()
    this.vertices.foreach(x => {
      dis += (x -> -1)
    })
    dis.update(s, 0)
    var visitted = new mutable.HashSet[Vertex]()
    var bfsQ = new mutable.Queue[Vertex]()
    bfsQ += s
    visitted += s
    while (!bfsQ.isEmpty) {
      val curr = bfsQ.dequeue()
      visitted += curr
      // get nbrs
      // if not in visited: dis[nbr] = dis[curr] + weight
      // Assume weight = 6
      getNbrs(curr).filter(!visitted.contains(_)).foreach(x => {
        bfsQ += x
        dis.update(x, dis.get(curr).get + 1)
      })
    }
    dis
  }

  def printShortestReach(s: Vertex): Unit = {
    val dis = shortestReach(s)
    this.vertices.foreach(x => {
      if (x != s && dis.get(x).get > 0) {
        println(s"shortest distance from ${s} to ${x} is ${dis.get(x).get} unit.")
      }
    })
  }
}
