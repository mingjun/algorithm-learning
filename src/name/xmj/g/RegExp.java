package name.xmj.g;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import name.xmj.util.automaton.NFA;
import name.xmj.util.automaton.NFA.State;
import name.xmj.util.automaton.NFA.Transfer;


public class RegExp {
	

	/**
	 * support char a..z,  A..z, * and ?
	 * 
	 * e.g. a*b
	 * @param exp
	 */
	public RegExp(String exp) {
		NFA nfa = RegExp2NFA(exp);
		nfa.buildGraph();
	}

	public boolean match(String str) {
		return false;
	}
	
	static NFA RegExp2NFA(String regex) {
		// alphabet
		Set<Character> letters = new LinkedHashSet<Character>();
		for(char c='a'; c <= 'a'; c++) {
			letters.add(c);
//			letters.add((char)(c-'a'+'A'));
		}
		
		// states
		List<State> states = new ArrayList<State>();
		int len = regex.length();
		for(int i=0; i <= len;i++) {
			String sub = regex.substring(0, i);
			states.add(new DebugState(sub));
		}
		State start = states.get(0);
		Set<State> ends = Collections.singleton(states.get(len));
		
		
		// transfers
		List<Transfer> transfers = new ArrayList<Transfer>();
		if(len >= 1) { 
			int preIndex = 0;
			int curIndex = 1;
			for(; curIndex <= len; preIndex = curIndex++) {
				State pre = states.get(preIndex), cur = states.get(curIndex);
				char t = regex.charAt(preIndex);
				switch(t) {
				case '?':
					transfers.add(new DebugTransfer(pre, cur, letters));
					break;
				case '*':
					transfers.add(new DebugTransfer(pre, cur, NFA.NONE));
					transfers.add(new DebugTransfer(cur, cur, letters));
					break;
				default:
					transfers.add(new DebugTransfer(pre, cur, t));
				}
			}
		}
		
		//debug
		for(Transfer t: transfers) {
			System.out.println(t);
		}
		
		return new NFA(letters, states, start, ends, transfers);
		
	}
	
	static class DebugState extends State {
		String exp;
		DebugState(String exp) {
			this.exp = exp;
		}
		
		@Override
		public String toString() {
			return exp;
		}
	}
	
	static class DebugTransfer extends Transfer {

		public DebugTransfer(State f, State t, char accept) {
			super(f, t, accept);
		}
		
		public DebugTransfer(State f, State t, Collection<Character> accepts) {
			super(f, t, accepts);
		}
		
		@Override
		public String toString() {
			return String.format(" %s >>-%s->> %s ", from, accepts ,to);
		}
		
	}
	
}


//	Map<NfaNode, Set<Character>> findNeighbours(NfaNode n) {
//		Map<NfaNode, Set<Character>> neighbour2Chars = new HashMap<NfaNode, Set<Character>>();
//		
//		List<NfaTrans> outs = nfa.outTrans(n);
//		for(NfaTrans t: outs) {
//			NfaNode neib = t.to;
//			if(!neighbour2Chars.containsKey(neib)) {
//				neighbour2Chars.put(neib, new HashSet<Character>());
//			}
//			Set<Character> chars = neighbour2Chars.get(neib);
//			
//			Set<Character> cBefore = new HashSet<Character>(t.acceptChars);
//			if(cBefore.contains(NONE)) {
//				cBefore.remove(NONE);
//			}
//			chars.addAll(cBefore);
//		}
//		return neighbour2Chars;
//	}
//	
//	
//	
//	static class DfaNode {
//		
//	}
//	