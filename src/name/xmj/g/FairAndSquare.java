package name.xmj.g;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class FairAndSquare {
	/**
Little John likes palindromes, and thinks them to be fair (which is a fancy word for nice). A palindrome is just an integer that reads the same backwards and forwards - so 6, 11 and 121 are all palindromes, while 10, 12, 223 and 2244 are not (even though 010=10, we don't consider leading zeroes when determining whether a number is a palindrome).

He recently became interested in squares as well, and formed the definition of a fair and square number - it is a number that is a palindrome and the square of a palindrome at the same time. For instance, 1, 9 and 121 are fair and square (being palindromes and squares, respectively, of 1, 3 and 11), while 16, 22 and 676 are not fair and square: 16 is not a palindrome, 22 is not a square, and while 676 is a palindrome and a square number, it is the square of 26, which is not a palindrome.

Now he wants to search for bigger fair and square numbers. Your task is, given an interval Little John is searching through, to tell him how many fair and square numbers are there in the interval, so he knows when he has found them all.
Solving this problem

Usually, Google Code Jam problems have 1 Small input and 1 Large input. This problem has 1 Small input and 2 Large inputs. Once you have solved the Small input, you will be able to download any of the two Large inputs. As usual, you will be able to retry the Small input (with a time penalty), while you will get only one chance at each of the Large inputs.
Input

The first line of the input gives the number of test cases, T. T lines follow. Each line contains two integers, A and B - the endpoints of the interval Little John is looking at.
Output

For each test case, output one line containing "Case #x: y", where x is the case number (starting from 1) and y is the number of fair and square numbers greater than or equal to A and smaller than or equal to B. 
	 */
	
	/*
	 Fair : palindrome
	 Fair and Square: Fair, and is a Square of some Fair
	 */
	
	boolean isFair(BigDecimal number ){
		String decimal = number.toPlainString();
		int p = 0, q = decimal.length()-1;
		while(p<q){
			if ( decimal.charAt(p) != decimal.charAt(q) ) {
				return false;
			}
			p++;
			q--;
		}
		return true;
	}
	
//	boolean isFairAndSquare(int number) {
//		if(!isFair(number))
//			return false;
//		int sr = squareRoot(number);
//		if(sr < 0) {
//			return false;
//		}
//		return isFair(sr);
//	}
//	boolean isSquare(int number) {
//		return squareRoot(number) >= 0;
//	}
//	
//	int squareRoot(int n) {
//		double r = Math.sqrt(n);
//		int sr = (int)r;
//		if(sr*sr == n) {
//			return sr;
//		} else {
//			return -1;
//		}
//	}
	int count = 0;
	
	void checkAndCount(BigDecimal i) {
		BigDecimal n = i.multiply(i);
		if(isFair(n)) {
//			System.out.println("-- "+n);
			count++;
		}
	}

	void buildFairs(int len, Function<BigDecimal, Void> f) {
		//"12345:54321"
		int half = len/2;
		boolean oddLen = len%2 == 1;
		if(oddLen) half ++;
		
		StringBuilder start = new StringBuilder();
		start.append(1);
		for(int i=1;i<half;i++) {
			start.append(0);
		}
		
		BigDecimal i = new BigDecimal(start.toString());
		for(;;) {
			String strPar = i.toPlainString();
			if(strPar.length() > half) break;
			StringBuilder str = new StringBuilder();
			str.append(strPar);
			int index = strPar.length() - 1;
			if(oddLen) index --;
			for(;index>=0;index--) {
				str.append(strPar.charAt(index));
			}
//			System.out.println("-- "+str.toString());
			f.run(new BigDecimal(str.toString()));
			
			
			i = i.add(BigDecimal.ONE);
		}
		
	}
	

	
	int search(final BigDecimal start, final BigDecimal end) {
		// square are less than fairs
		// improvement 1: quick search squares then check if it's fair
		// improvement 2: step sqrt(start) to sqrt(end) search fairs, then check if its square is fair
		
		// improvement 3: skip some number that are not fairs
		// build fair number rather that check one by one
		double s = Math.sqrt(start.doubleValue());
		double e = Math.sqrt(end.doubleValue());
		final BigDecimal ss = new BigDecimal(Math.floor(s));
		final BigDecimal ee = new BigDecimal(Math.ceil(e));
		
		
		int lenS = ss.toPlainString().length();
		int lenE = ee.toPlainString().length();
		
		Function<BigDecimal, Void> cbStart = new Function<BigDecimal, Void>(){
			@Override
			public Void run(BigDecimal x) {
				if(x.multiply(x).compareTo(start) >= 0) {
					checkAndCount(x);
				}
				return null;
			}
		};
		
		Function<BigDecimal, Void> cbEnd = new Function<BigDecimal, Void>(){
			@Override
			public Void run(BigDecimal x) {
				if(x.multiply(x).compareTo(end) <= 0) {
					checkAndCount(x);
				}
				return null;
			}
		};
		
		Function<BigDecimal, Void> cbStartEnd = new Function<BigDecimal, Void>(){
			@Override
			public Void run(BigDecimal x) {
				BigDecimal sq = x.multiply(x);
				if(sq.compareTo(start) >= 0 && sq.compareTo(end) <= 0)
					checkAndCount(x);
				return null;
			}
		};
		
		this.count = 0;
		if(lenS == lenE) {
			buildFairs(lenS, cbStartEnd);
		} else {
			buildFairs(lenS, cbStart);
			buildFairs(lenE, cbEnd);
		}
		
		for(int l = lenS+1; l<lenE; l++) {
			buildFairs(l, new Function<BigDecimal, Void>(){
				@Override
				public Void run(BigDecimal x) {
					checkAndCount(x);
					return null;
				}
			});
		}
		
		return count;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FairAndSquare test = new FairAndSquare();
//		int count = test.search(new BigDecimal(10), new BigDecimal(120));
//		System.out.println("Case #"+":"+count);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int lineCount = Integer.parseInt(br.readLine());
			for(int i=1;i<=lineCount;i++) {
				String ln = br.readLine();
				String [] a = ln.split(" ");
				int count = test.search(new BigDecimal(a[0]), new BigDecimal(a[1]));
				System.out.println("Case #"+i+":"+count);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {}
		}
		
		
	}
	
	interface Function<A,B> {
		B run(A x);
	}
	

}
