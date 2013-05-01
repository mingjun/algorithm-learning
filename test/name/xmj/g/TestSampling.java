package name.xmj.g;

import org.junit.Test;

public class TestSampling {

//	@Test
	public void IteratorStream() {
		OnlineStream<Integer> stream = new OrderedNumberStream(20);
		Integer item = 0;
		while(null != (item = stream.next())) {
			System.out.println(item);
		}
	}
//	@Test
	public void TestSample() {
		OnlineRandomSampling<Integer> s =
				new OnlineRandomSampling<Integer>(new OrderedNumberStream(9), 3);
		s.sample();
	}

	@Test
	public void TestBigSample() {
		OnlineRandomSampling<Integer> s =
				new OnlineRandomSampling<Integer>(new OrderedNumberStream(99999999), 10);
		System.out.println(s.sample());
	}
}

class OrderedNumberStream implements OnlineStream<Integer> {
	int num = 0;
	int end;
	public OrderedNumberStream() {
		this(10000);
	}
	public OrderedNumberStream(int length) {
		end = length;
	}
	@Override
	public Integer next() {
		if(num < end) {
			num ++;
			return num;
		} else {
			return null;
		}
	}
}