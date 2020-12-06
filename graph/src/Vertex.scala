class Vertex {

  var label: Char = '0'

  def this(label: Char) {
    this()
    this.label = label
  }

  override def toString: String = {
    label.toString
  }

}
