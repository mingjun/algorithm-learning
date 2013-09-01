package name.xmj.util.graph;

public class Edge<Vertex, WeightType> {
	Vertex source, target;
	WeightType weight;
	
	public Edge(Vertex f, Vertex t, WeightType w) {
		source = f;
		target = t;
		weight = w;
	}
	
	public Vertex getSource() {
		return source;
	}
	public Vertex getTarget() {
		return target;
	}
	public WeightType getWeight() {
		return weight;
	}
}