package pmf.math.oaa.hw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ClosestPair {
	
	/**
	 * Method calculating closest pair of points using Divide and Conquer approach
	 * Passes the work zo closestPairDIC function.
	 * @param points set of points
	 * @return closest points
	 */
	public static Pair closestPairDIC(Point[] points) {
		int last = points.length - 1;
		Arrays.sort(points, Point.BY_XY);
		return calcClosestPairRec(points, 0, last);
	}
	
	/**
	 * Recursive function calculating closest pair of points using Divide and conquer approach.
	 * @param points set of points 
	 * @param lInd left index to process
	 * @param rInd rigth index to process
	 * @return closest pair of points using points between lInd and rInd, both included
	 */
	private static Pair calcClosestPairRec(Point[] points, int lInd, int rInd) {
		if(rInd - lInd < 3) {
			return processBase(points, lInd, rInd);
		}
		int midInd = (lInd + rInd) / 2;
		double midX = points[midInd].getX();
		Pair rBest = calcClosestPairRec(points, midInd + 1, rInd);
		Pair lBest = calcClosestPairRec(points, lInd, midInd);
		Pair best = rBest.distance() < lBest.distance() ? rBest : lBest;
		mergePoints(points, lInd, midInd, rInd);
		int len = 0;
		Point[] v = new Point[rInd - lInd + 1];
		for(int i = lInd; i <= rInd; ++i) {
			if(points[i].getX() > midX - best.distance() && 
					points[i].getX() < midX + best.distance()) {
				v[len++] = points[i];
			}
		}
		for(int k = 0; k <= len - 1; ++k) {
			for(int s = k + 1; s < Math.min(len, k + 7); ++s) {
				Pair tmp  = new Pair(v[k], v[s]);
				best = best.distance() < tmp.distance() ? best : tmp;
			}
		}
		return best;
	}

	/**
	 * Method merging sublists between lInd and midInd, midInd and rInd
	 * @param points points
	 * @param lInd left index
	 * @param midInd mid index
	 * @param rInd right index
	 */
	private static void mergePoints(Point[] points, int lInd, int midInd, int rInd) {
		Point[] tmp = new Point[rInd - lInd + 1];
		int tail = 0;
		int lStart = lInd;
		int rStart = midInd + 1;
		while(lStart <= midInd && rStart <= rInd) {
			if(points[lStart].getY() <= points[rStart].getY()) {
				tmp[tail++] = points[lStart++];
			} else {
				tmp[tail++] = points[rStart++];
			}
		}
		if(rStart > rInd && lStart <= midInd) {
			while(lStart <= midInd) {
				tmp[tail++] = points[lStart++];
			}
		} else if(lStart > midInd && rStart <= rInd) {
			while(rStart <= rInd) {
				tmp[tail++] = points[rStart++];
			}
		}
		for(int i = lInd; i <= rInd; ++i) {
			points[i] = tmp[i - lInd];
 		}
	}

	private static Pair processBase(Point[] points, int l, int r) {
		if(points.length == 1) return new Pair(points[0], points[0]);
		Arrays.sort(points, l, r + 1, Point.BY_YX);
		Pair min = new Pair(points[l], points[l + 1]);
		double delta = min.distance();
		if(r - l == 1) {
			return min;
		}
		Pair tmp = new Pair(points[l + 1], points[l + 2]);
		if(tmp.distance() < delta) {
			min = tmp;
			delta = tmp.distance();
			return tmp;
		}
		tmp = new Pair(points[l], points[l + 2]);
		return tmp.distance() < delta ? tmp : min;
	}
	
	public static Pair calcClosestNaive(Point[] points) {
		if(points.length == 1) return new Pair(points[0], points[0]);
		Pair best = null;
		double bestDist = -1;
		for(int i = 0; i < points.length; ++i) {
			for(int j = i + 1; j < points.length; ++j) {
				Pair p = new Pair(points[i], points[j]);
				if(bestDist == -1 || p.distance() < bestDist) {
					best = p;
					bestDist = p.distance();
				}
			}
 		}
		return best;
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));			
			while(true) {
				int nOfPoints = Integer.valueOf(br.readLine());
				if(nOfPoints == 0) break;
				Point[] ps = new Point[nOfPoints];
				for(int j = 0; j < nOfPoints; ++j) {
					String[] point = br.readLine().split("\\s");
					Point p = new Point(Double.valueOf(point[0]), Double.valueOf(point[1]));
					ps[j] = p;
				}
				long start = System.currentTimeMillis();
				Pair cl = ClosestPair.closestPairDIC(ps);
				long lasts = System.currentTimeMillis() - start;
				System.out.println("Trajanje divide and conquer: " + lasts);
				System.out.println(cl);
			}	
		}catch(Exception io){
			System.err.println("Wrong input format");
		}	
		
		Point[] points = new Point[4000]; 
		for(int i = 0; i < 4000; ++i) {
			double x = Math.random();
			double y = Math.random();
			points[i] = new Point(x, y);
		}
		long start = System.currentTimeMillis();
		Pair cl = ClosestPair.closestPairDIC(points);
		long lasts = System.currentTimeMillis() - start;
		System.out.println("Trajanje divide and conquer: " + lasts);
		System.out.println(cl);
		long start2 = System.currentTimeMillis();
		cl = ClosestPair.calcClosestNaive(points);
		long lasts2 = System.currentTimeMillis() - start2;
		System.out.println("Trajanje naive approach: " + lasts2);
		System.out.println(cl);
		
	}
	
}
