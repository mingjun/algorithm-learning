package name.xmj.g;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class TestMergeSort {
	MergeSort target = new MergeSort();
	@Test
	public void testMain(){
		checkCorrectOrder(target.sort(Arrays.asList(1,2,5,3,6,4)));
		
		checkCorrectOrder(target.sort(rList(100)));//performance problem...
	}
	
	public List<Integer> rList(int size) {
		Random r = new Random();
		List<Integer> res = new ArrayList<Integer>(size);
		for(int i=0;i<size;i++) {
			res.add(r.nextInt(100));
		}
		return res;
	}
	
	public void checkCorrectOrder(List<Integer> list){
		int pre = Integer.MIN_VALUE;
		for(Integer num: list) {
			assertTrue(num >= pre);
			pre = num;
		}
	}
}
