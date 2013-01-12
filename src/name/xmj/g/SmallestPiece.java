package name.xmj.g;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * http://digi.cn.yahoo.com/newspic/digi/9400/#8
 *
 * find the smallest window from a long string SOURCE, which contains all chars in alphabet sigma
 * f(S="aaabdccd", Sigma={a,b,c})   -> "abdc"
 *
 * @author mingjun
 *
 * complexity:
 *  n = the length of the source String,
 *  m = size of sigma
 *
 * Time on two scans:
 *   wStart: 0 -> n
 *   wEnd: 0 -> n
 * T(n) = O(n)
 *
 * Extra space for the Map accumulator (can improved as an Array)
 * S(n) = O(m)
 */
public class SmallestPiece {
	final Set<Character> sigma;
	final boolean ignoreCase;

	String source;

	public SmallestPiece(char [] sigma, boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
		this.sigma = new HashSet<Character>();
		for(char s : sigma) {
			this.sigma.add(unifyChar(s));
		}
	}

	public SmallestPiece(char [] sigma) {
		this(sigma, false);
	}

	static char [] buildSigma4English() {
		final int size = 26;
		char [] sigma = new char[size];
		char c = 'a';
		for(int i=0;i<size;i++) {
			sigma[i] = c;
			c++;
		}
		return sigma;
	}

	public SmallestPiece() {
		this(buildSigma4English(), true);
	}

	char unifyChar(char in) {
		return ignoreCase ? Character.toLowerCase(in) : in;
	}

	public void find(String source) {
		this.source = source;
		System.out.println("source="+source);

		Map<Character, Integer> accumulator = new HashMap<Character, Integer>();
		final int fpEnd = findFirstPiece(accumulator);
		if(fpEnd < 0) {
			return;
		}

		final int sourceLen = source.length();
		//move window, and find the minium window-Size
		int wSize  = minimizeWindow(0, fpEnd, accumulator);
		int wStart = fpEnd - wSize + 1;

		int wMinSize = wSize;
		int wEndOnMinSize = fpEnd;
		for(int wEnd = fpEnd+1; wEnd<sourceLen; wEnd++) {
			//add a new char --- move the window-End
			char newChar = unifyChar(source.charAt(wEnd));
			accumulator.put(newChar, accumulator.get(newChar)+1);
			//find the new start point  --- move the window-Start
			wSize = minimizeWindow(wStart, wEnd, accumulator);
			wStart = wEnd - wSize + 1;

			if(wMinSize > wSize) {
				wMinSize = wSize;
				wEndOnMinSize = wEnd;
			}
//			System.out.println("for end "+wEnd + ", size="+wSize+", string="+ source.substring(wStart, wEnd+1));
		}

		System.out.print("Smallest Piece length = " + wMinSize +", ");
		System.out.println("one Smallest Piece = " + source.substring(wEndOnMinSize - wMinSize + 1, wEndOnMinSize+1));
	}
	/**
	 * the first piece in source from 0 to n,  that contains all chars in sigma
	 *
	 * @param accumulator
	 * @return
	 *  n: the end of a Piece,
	 */
	int findFirstPiece(Map<Character, Integer> accumulator) {
		final int sourceLen = source.length();
		int fpEnd = -1;
		for(int i = 0;i<sourceLen;i++) {
			char c = unifyChar(source.charAt(i));
			// accumulate
			if(sigma.contains(c)) {
				int count;
				if(accumulator.containsKey(c)) {
					count = accumulator.get(c);
				} else {
					count = 0;
				}
				accumulator.put(c, count+1);
			}
			if(accumulator.keySet().size() >= sigma.size()) {
				fpEnd = i;
				break;
			}
		}
		if(fpEnd < 0) {
			System.out.print("Piece not exist, ");
			Set<Character> showingChars = accumulator.keySet();
			List<Character> missChars = new ArrayList<Character>(sigma.size() - showingChars.size());
			for(char c : sigma) {
				if(!showingChars.contains(c)) {
					missChars.add(c);
				}
			}
			System.out.println("missing chars: "+ missChars);
		}
		return fpEnd;
	}
	/**
	 *
	 * @param wEnd
	 * @param wSize
	 * @param accumulator
	 * @return
	 *  windowSize that minimized
	 */
	int minimizeWindow(int wStart, int wEnd, Map<Character, Integer> accumulator) {
		for(int i= wStart;i < wEnd; i++) {
			char c = unifyChar(source.charAt(i));
			if(sigma.contains(c)) {
				int count = accumulator.get(c) - 1; //decrease
				if(count > 0) {
					accumulator.put(c, count);
				} else { // count == 0, this is new window start
					wStart = i;
					break;
				}
			}
		}
		return wEnd - wStart + 1;
	}
}
