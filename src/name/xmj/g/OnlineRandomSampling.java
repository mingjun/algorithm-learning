package name.xmj.g;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OnlineRandomSampling <T> {

	Random r = new Random();

	OnlineStream<T> stream;
	int size;
	TaillessList<T> samples;

	public OnlineRandomSampling(OnlineStream<T> stream) {
		this(stream, 10);
	}

	public OnlineRandomSampling(OnlineStream<T> stream, int sampleSize) {
		this.stream = stream;
		size = sampleSize;
		samples = new TaillessList<T>(size);
	}

	public List<T> sample() {
		if(samples.size() < size) {
			T item;
			while(null != (item = stream.next())) {
				randomInsert(item, samples);
			}
		}
		return samples.getHeads();
	}

	void randomInsert(T item, TaillessList<T> samples) {
		int size = samples.size();
		// when size == 4, there are 5 place can be insert
		// ^ [] ^ [] ^ [] ^ [] ^
		int index = r.nextInt(size + 1);
		samples.add(index, item);

//debug
//		System.out.println("insert "+item +" at [" + index + "], result " + samples);
//		if(samples.size() % 5000000 == 0) {
//			System.out.println(samples);
//		}
	}

}

class TaillessList<T> {
	private List<T> heads;
	final int headSize;
	private int size;

	public TaillessList(int headSize) {
		this.headSize = headSize;
		this.heads = new ArrayList<T>(headSize+1);

	}
	public void add(int index, T item) {
		if(index >= headSize) {
			// do nothing
		} else {
			if(index >= heads.size()) {
				heads.add(item);
			} else {
				heads.add(index, item);
			}
			// if heads is out-of-size, remove the last one
			int actualSize;
			while( (actualSize = heads.size()) > headSize ) {
				heads.remove(actualSize -1);
			}
		}
		size ++;
	}

	public int size() {
		return size;
	}

	public List<T> getHeads() {
		return heads;
	}
	public String toString() {
		return heads.toString();
	}


}

interface OnlineStream<T> {
	/**
	 * iterate the Stream
	 * @return
	 * 	an instance of T, if next item exists
	 *  null, if meet stream end
	 */
	public T next();
}

