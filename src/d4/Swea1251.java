package d4;

import java.io.*;
import java.util.*;

public class Swea1251 {
	static int V;

	static class Vertex implements Comparable<Vertex> {
		int from;
		double weight;

		public Vertex(int from, double weight) {
			this.from = from;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return Double.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./res/input1251.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			V = Integer.parseInt(br.readLine());
			double[][] arr = new double[V][2];
			StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < V; i++) {
				arr[i][0] = Double.parseDouble(st1.nextToken());
				arr[i][1] = Double.parseDouble(st2.nextToken());
			}
			double E = Double.parseDouble(br.readLine());
			double [][] wArr = new double[V][V];
			double [] minWArr = new double [V];
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					if (i == j) continue;
					if(i>j) {
						wArr[i][j] = wArr[j][i];
					}else {
						double w = Math.pow(arr[i][0] - arr[j][0], 2) + Math.pow(arr[i][1] - arr[j][1], 2);
						wArr[i][j] = w;
					}
				}
				minWArr[i] = Double.MAX_VALUE;
			}
			boolean [] v = new boolean[V];
			PriorityQueue<Vertex> pq = new PriorityQueue<>();
			pq.offer(new Vertex(0,0));
			minWArr[0] = 0;
			long totalWeight = 0;
			while(!pq.isEmpty()) {
				Vertex ver = pq.poll();
				if(v[ver.from]) continue;
				v[ver.from] = true;
				totalWeight+=ver.weight;
				for(int i = 0 ; i < V; i++) {
					if(!v[i] && wArr[ver.from][i] !=0 && (minWArr[i] > wArr[ver.from][i])) {
						minWArr[i] = wArr[ver.from][i];
						pq.offer(new Vertex(i, minWArr[i]));
					} 
				}
			}
			sb.append('#').append(tc).append(' ').append(Math.round(E * totalWeight)).append('\n');
		}

		br.close();
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
