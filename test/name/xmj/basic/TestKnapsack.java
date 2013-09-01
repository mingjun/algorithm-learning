package name.xmj.basic;

import org.junit.Test;

public class TestKnapsack {

	@Test
	public void testT(){
		  int[] w ={1,3,4,5};//物品重量数组
		  int[] v ={2,30,44,20};//物品价值数组
		  Knapsack k = new Knapsack(w, v);
		  for(int i=0;i<20;i++) {
			  System.out.println(k.findR(i));
		  }
	}
	
	@Test
	public void test2(){
		  int[] w ={1,3};//物品重量数组
		  int[] v ={2,30};//物品价值数组
		  Knapsack k = new Knapsack(w, v);
		  for(int i=0;i<10;i++) {
			  k.findR(i);
		  }
	}
}
