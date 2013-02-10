package name.xmj.noi;
/**
 * http://judge.noi.cn/problem?id=1207
 * @author mingjun
 *
 * Jump path:
 * 0 .. stones[i] .. L
 * where : L >= riverWidth
 */
public class SmallestJump {
	int minJumpDistance, maxJumpDistance;
	int [] stones;
	int riverWidth;
	int smallestJump;
	public SmallestJump(int riverWidth, int min, int max, int [] st ){
		this.riverWidth = riverWidth;
		this.minJumpDistance = min;
		this.maxJumpDistance = max;

		this.stones = new int[st.length+1];
		this.stones[0] = 0;
		for(int i=1;i<stones.length; i++) {
			stones[i] = st[i-1];
		}
	}

	public int run() {
		smallestJump = Integer.MAX_VALUE;
		int jumpCount = 0;
		int jumpIndex = 0;
		jumpFrom(jumpIndex, jumpCount);
		System.out.println("smallest Jump is "+ smallestJump);
		return smallestJump;
	}

	public void jumpFrom(int stoneIndex, int count) {
		int position = stones[stoneIndex];
		System.out.println(count+ " on stone["+stoneIndex+"]");
		int lowIndex = findLowInStones(stoneIndex,
				position+minJumpDistance);
		int upIndex = findUpInStones(stoneIndex,
				position+maxJumpDistance);
		// Stone[lowIndex] ~ Stone[upIndex] can be done

		if(lowIndex >= stones.length) {
			// find one
			count ++;
			System.out.println("jump success " + count);
			if(count < smallestJump) {
				smallestJump = count;
			}
			return;
		}
		if(upIndex < lowIndex) {
			// can not go on
			System.out.println("jump fail ");
			return;
		}
		count ++;
		System.out.println("next jumpable stone "+ lowIndex + "~" + upIndex);
		for(int j = lowIndex; j <= upIndex ; j++) {
			//try next jump
			//TODO dynamic programming
			jumpFrom(j, count);
		}
	}

	// Stone[x-1] < low <= Stone[x]
	// x = length, if not exists
	// TODO binary search
	public int findLowInStones(int startIndex, int low) {
		for(int i=startIndex;i<this.stones.length;i++) {
			if(stones[i] >= low) {
//				System.out.println(low + "\t" + stones[i]);
				return i;
			}
		}
		return stones.length;
	}

	//Stone[x-1] <= up < Stone[x]
	// x = start-1, if not exists
	// TODO binary search
	public int findUpInStones(int startIndex, int up) {
		for(int i=startIndex;i<stones.length;i++) {
			if(this.stones[i] > up) {
//				System.out.println(stones[i-1]+"\t"+up + "\t" + stones[i]);
				return i-1;
			}
		}
		return stones.length-1;
	}
}
