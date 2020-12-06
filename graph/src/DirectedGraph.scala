import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class DirectedGraph {

  var numV = 0
  var numE = 0
  var vertices = new ArrayBuffer[Vertex]()
  var edges = new ArrayBuffer[Edge]()

  var inDeg = new mutable.HashMap[Vertex, Int]() // for topsort()

  def addVertex(v1: Vertex): Unit = {
    if (!vertices.contains()) {
      this.vertices += v1
      this.numV += 1
      this.inDeg += v1 -> 0
    }
  }

  def addEdge(v1: Vertex, v2: Vertex): Unit = {
    var e1 = new Edge(v1, v2)
    this.edges += e1
    this.numE += 1
    this.inDeg.update(e1.edge(1), this.inDeg.get(e1.edge(1)).get + 1)
  }

  def getNbrs(v1: Vertex): ArrayBuffer[Edge] = {
    this.edges.filter(_.edge(0) == v1)
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
      bfsQ.enqueueAll(this.edges.filter(_.edge(0) == curr).map(_.edge(1))).filter(!visitted.contains(_))
    }
    walk
  }

  def topSort(): Unit = {
    var topOrder = new ArrayBuffer[Vertex]()
    var q = new mutable.Queue[Vertex]()
    var cloneInDeg = this.inDeg.clone() // to preserve the actual inDegree
    cloneInDeg.foreach(x => {
      if (x._2 == 0) {
        q.enqueue(x._1)
      }
    })
    while (!q.isEmpty) {
      val now = q.dequeue()
      topOrder += now
      getNbrs(now).filter(_.edge(0) == now).foreach(x => {
        val to = x.edge(1)
        if (cloneInDeg.get(to).get > 0) {
          var tmp = cloneInDeg.get(to).get
          cloneInDeg.update(to, tmp - 1)
          if (cloneInDeg.get(to).get == 0) {
            q.enqueue(to)
          }
        }
      })
    }
    //
    println("This is one of the possible topological ordering.")
    topOrder.foreach(x => {
      print(s"${x}->")
    })
    println()
  }
}
