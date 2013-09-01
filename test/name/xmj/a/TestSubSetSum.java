package name.xmj.a;


import org.junit.Test;

public class TestSubSetSum {

	@Test
	public void t() {
		int [] test = {1,3,3,4};
		SubSetSum sss = new SubSetSum(test);
		sss.dump();
		
		int [] v = {0,1,2,3,4,5,6,7,8,9, 10 , 11};
		for(int value : v)
		System.out.printf("sum %d exists in set ? %s \n", value, 
				sss.containsSubSetWithSum(value)? sss.findSubSet(value): "No"
					);
	}
}
