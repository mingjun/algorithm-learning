package name.xmj.util;

public class PerformanceTracker {
	public static PerformanceTracker create(String name) {
		return new PerformanceTracker(name);
	}
	long time;

	String name;
	int count = 0;
	int duration_x_count = 0;

	private PerformanceTracker(String name) {
		this.name = name;
	}

	public void start() {
		time = System.currentTimeMillis();
		count ++;
	}
	public void end() {
		long t = System.currentTimeMillis();
		int duration = (int)(t-time);
		duration_x_count += duration;
//		System.out.println("execute for ms: " + duration);
	}
	public void statistic() {
		double avg = count == 0 ? 0.0d : (duration_x_count/(double)count);
		System.out.println("track "+ name +" execute for " + count + " times, avg duration = "+ avg);
	}
}
