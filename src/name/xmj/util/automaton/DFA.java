package name.xmj.util.automaton;

import java.util.Collection;
import java.util.List;
import java.util.Set;


public class DFA {
	Collection<Character> alphabet;
	List<DState> states;
	DState start;
	Set<DState> ends;

	List<DTransfer> transfers;
	
	public static class DState {}
	
	public static class DTransfer {
		protected DState from, to;
		protected char accept;
		
		public DTransfer(DState f, DState t, char accept) {
			from = f;
			to = t;
			this.accept = accept;
		}
	}
}
