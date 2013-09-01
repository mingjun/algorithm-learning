package name.xmj.util.automaton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import name.xmj.util.automaton.DFA.DState;
import name.xmj.util.graph.DirectedGraph;
import name.xmj.util.graph.Edge;



public class NFA {
	public static final char NONE = '\0';
	
	Collection<Character> alphabet;
	
	List<State> states;
	State start;
	Set<State> ends;

	List<Transfer> transfers;
	
	public NFA(Collection<Character> alphabet, 
			List<State> states, State start, Set<State> ends, 
			List<Transfer> transfers) {
		
		this.alphabet = alphabet;
		this.states = states;
		this.start = start;
		this.ends = ends;
		this.transfers = transfers;
	}
	
	DirectedGraph<State, Character> graph;
	
	public void buildGraph() {
		
		List<Edge<State, Character>> zeroEdges = new ArrayList<Edge<State, Character>>();
		for(Transfer t : transfers) {
			if(t.accepts.contains(NONE)) {
				zeroEdges.add(new Edge<State, Character>(t.from, t.to, NONE));
			}
		}
		DirectedGraph<State, Character> g0 = new DirectedGraph<State, Character>(states, zeroEdges);
		
		for(State s: states) {
			System.out.println(s + "\t -0-> \t" + g0.getConnected(s));
		}
		
		List<Edge<State, Character>> oneEdges = new ArrayList<Edge<State, Character>>();
		for(Transfer t : transfers) {
			Set<Character> oneEdgeChar = new HashSet<Character>(t.accepts);
			if(t.accepts.contains(NONE)) {
				oneEdgeChar.remove(NONE);
			}
			for(char c : oneEdgeChar) {
				oneEdges.add(new Edge<State, Character>(t.from, t.to, c));
			}
		}
		
		DirectedGraph<State, Character> g1 = new DirectedGraph<State, Character>(states, oneEdges);
		
		List<Edge<State, Character>> fakeOneEdges = new ArrayList<Edge<State, Character>>();
		for(State s: states) {
			for(State c : g0.getConnected(s)) {
				List<Edge<State, Character>> out = g1.getOutEdges(c);
				List<Edge<State, Character>> sOut = new ArrayList<Edge<State, Character>>(out.size());
				for(Edge<State, Character> o : out) {
					sOut.add(new Edge<State, Character>(s, o.getTarget(), o.getWeight()));
				}
				fakeOneEdges.addAll(sOut);
			}
		}
		System.out.println("before add");
		for(State s: states) {
			System.out.println(s + "\t ---> \t" + g1.getDirectedConnected(s));
		}
		
		g1.addEdges(fakeOneEdges);
		System.out.println("after add");
		for(State s: states) {
			System.out.println(s + "\t ---> \t" + g1.getDirectedConnected(s));
		}
		graph = g1;
	}
	
	public DFA toDFA() {
//		List<DState> d_states = new ArrayList<DState>();
//		List<DTransfer> d_transfers = new ArrayList<DTransfer>();
		CompositeState d_start = new CompositeState(start);
		
		
		Queue<CompositeState> q = new LinkedList<CompositeState>();
		q.add(d_start);
		
		while(!q.isEmpty()) {
			CompositeState cs = q.remove();
			Set<Character> outChars = new HashSet<Character>();
			for(State s: cs.innerStates) {
				for(Edge<State, Character> e : graph.getOutEdges(s)) {
					outChars.add(e.getWeight());
				}
			}
			
			for(char c: outChars) {
				Set<State> nextStates = new HashSet<State>();
				for(State s: cs.innerStates) {
					List<Edge<State, Character>> outs = graph.getOutEdges(s);
					for(Edge<State, Character> e : outs) {
						if(e.getWeight() == c) {
							nextStates.add(e.getTarget());
						}
					}
				}
				break;
				// next composite state
//				if(!nextStates.isEmpty()) {
//					//register new NOTTODO
//					CompositeState next = new CompositeState(nextStates);
//					
//					q.add(next);
//					d_transfers.add(new DTransfer(cs, next, c));
//				}
			}
				
			
		}
		
		return null;
	}
	
	public static class State {}
	
	public static class Transfer {
		protected State from, to;
		protected Set<Character> accepts;
		
		public Transfer(State f, State t, Collection<Character> accepts) {
			from = f;
			to = t;
			this.accepts = new LinkedHashSet<Character>(accepts);
		}
		
		public Transfer(State f, State t, char accept) {
			from = f;
			to = t;
			this.accepts = Collections.singleton(accept);
		}
	}
	
	public static class CompositeState extends DState {
		Set<State> innerStates;
		CompositeState(State s) {
			innerStates = Collections.singleton(s);
		}
		CompositeState(Set<State> states) {
			innerStates = states;
		}
	}
}
