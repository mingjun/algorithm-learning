package name.xmj.wild;

import java.util.Random;

public class HelloWorld {



	public static void main(String [] args) {
		System.out.println(build(4036565L, 'b', 5));
		System.out.println(build(1598025L, 'a', 5));
	}

	public static String build(long seed, char base,  int length) {
		StringBuffer sb = new StringBuffer();
		Random r = new Random(seed);

		for(int i=0;i<length;i++) {
			sb.append((char)(r.nextInt(26)+base));
		}
		return sb.toString();
	}

	public static void findMe() {
		Result r = findSeed("Hello", 0, Long.MAX_VALUE);
		System.out.println(r.seed + "\t" + r.base);
		System.out.println(build(r.seed, r.base, 5));

		r = findSeed("World", 0, Long.MAX_VALUE);
		System.out.println(r.seed + "\t" + r.base);
		System.out.println(build(r.seed, r.base, 5));
	}

	public static Result findSeed(String goal, long s, long e) {
		goal = goal.toLowerCase();
		for(long seed = s; seed < e; seed ++) {
			Random r = new Random(seed);
			int num = r.nextInt(26);
			char base = (char)(goal.charAt(0) - num);

			if(base > 'z' || base < 'a') {
				continue;
			}

			boolean allMatch = true;
			for(int i=1;i<goal.length();i++) {
				char rChar = (char)(base + r.nextInt(26));
				if(rChar != goal.charAt(i)) {
					allMatch = false;
					break;
				}
			}
			if(allMatch) {
				return new Result(seed, base);
			}
 		}
		throw new Error("not exist!");
	}


	static class Result {
		long seed;
		char base;
		public Result(long s, char b) {
			seed = s;
			base = b;
		}
	}

}
