package name.xmj.a;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * an array of integers ar, and num s
 * list all pair (x, y) in ar, where x+y = s 
 * @author mingjun
 *
 */
public class FindAllPairs {

	static void findAllPairs(int [] ar, int s) {
		Map<Integer, List<Integer>> value2index = new HashMap<Integer, List<Integer>>();
		for(int i = 0; i < ar.length; i++) {
			int x = ar[i];
			int y = s - x;
			if(value2index.containsKey(y)) {
				List<Integer> listY = value2index.get(y);
				int j = listY.get(0);
				if(i != j) System.out.printf("ar[%d]=%d + ar%s=%d -> %d\n", i, x, listY, y, s);
			}
			
			
			List<Integer> listX;
			if(!value2index.containsKey(x)) {
				listX = new ArrayList<Integer>();
				value2index.put(x, listX);
			} else {
				listX = value2index.get(x);
			}
			listX.add(i);
		}
	}
	public static void main(String [] args){
		int [] ar = {1,3,5,4,2,3,5,3,8};
		findAllPairs(ar, 6);
	}
	
}
