package name.xmj.util.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * directed weighted graph
 *
 * @param <Vertex>
 * @param <WeightType>
 */
public class DirectedGraph<Vertex, WeightType> {
	List<Vertex> vertexes;
	List<Edge<Vertex, WeightType>> edges;
	
	
	public DirectedGraph(List<Vertex> vertexes, List<Edge<Vertex, WeightType>> edges) {
		this.vertexes = vertexes;
		this.edges = edges;
		
		buildDirectedConnected();
		buildConnectedClosures();
	}
	
	public void addEdges(List<Edge<Vertex, WeightType>> newEdges) {
		int increase = 0;
		for(Edge<Vertex, WeightType> newE : newEdges) {
			boolean duplicate  = false;
			for(Edge<Vertex, WeightType> existE: edges) {
				if(newE.source == existE.source && newE.target == existE.target && newE.weight.equals(existE.weight)) {
					duplicate = true;
					break;
				}
			}
			if( ! duplicate) {
				edges.add(newE);
				increase ++;
			}
		}
		if(increase > 0) {
			buildDirectedConnected();
			buildConnectedClosures();
		}
	}
	
	public List<Edge<Vertex, WeightType>> getOutEdges(Vertex x) {
		 List<Edge<Vertex, WeightType>> out = new ArrayList<Edge<Vertex, WeightType>>();
		for(Edge<Vertex, WeightType> e: edges) {
			if(e.source == x) {
				out.add(e);
			}
		}
		return out;
	}
	
	
	
	
	Map<Vertex, Set<Vertex>> directConnectedLookup, connectedClosureLookup;
	
	void buildDirectedConnected() {
		Map<Vertex, Set<Vertex>> m = new HashMap<Vertex, Set<Vertex>>();
		for(Edge<Vertex, WeightType> e: edges) {
			Vertex source = e.getSource(), target = e.getTarget();
			Set<Vertex> c;
			if(! m.containsKey(source)) {
				c = new HashSet<Vertex>();
				m.put(source, c);
			} else {
				c = m.get(source);
			}
			c.add(target);
		}
		directConnectedLookup = m;
	}
	
	void buildConnectedClosures() {
		Map<Vertex, Set<Vertex>> d = directConnectedLookup;
		Map<Vertex, Set<Vertex>> m = new HashMap<Vertex, Set<Vertex>>();
		for(Map.Entry<Vertex, Set<Vertex>> entry: d.entrySet()) {
			Vertex from = entry.getKey();
			Set<Vertex> base = entry.getValue();
			Set<Vertex> closure = new HashSet<Vertex>(base);
			for(Vertex to: base) {
				closure.addAll(getDirectedConnected(to));
			}
			m.put(from, closure);
		}
		connectedClosureLookup = m;
	}
	
	public Set<Vertex> getDirectedConnected(Vertex x) {
		Set<Vertex> r;
		if( !directConnectedLookup.containsKey(x) ) {
			r = Collections.emptySet();
		} else {
			r = directConnectedLookup.get(x);
		}
		return r;
	}
	
	public Set<Vertex> getConnected(Vertex x) {
		Set<Vertex> r;
		if( !connectedClosureLookup.containsKey(x) ) {
			r = Collections.emptySet();
		} else {
			r = connectedClosureLookup.get(x);
		}
		return r;
	}
}
