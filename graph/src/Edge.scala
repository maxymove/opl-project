import scala.collection.mutable.ArrayBuffer

class Edge {
  var edge = new ArrayBuffer[Vertex]
  var weight = 0

  def this(v1: Vertex, v2: Vertex) {
    this()
    this.edge += v1
    this.edge += v2
  }

  def this(v1: Vertex, v2: Vertex, weight: Int) {
    this()
    this.edge += v1
    this.edge += v2
    this.weight = weight
  }
  
}
