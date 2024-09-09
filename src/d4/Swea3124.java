package d4;

import java.io.*;
import java.util.*;

public class Swea3124 {
	static class Edge implements Comparable<Edge> {
		int start, end; 
		long weight;

		public Edge(int start, int end, long weight) {
			this.weight = weight;
			this.end = end;
			this.start = start;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	static int V;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("./res/input3124.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			make();
			Edge[] edges = new Edge[E];
			for (int i = 0; i < E; i++) {
				
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				long weight = Long.parseLong(st.nextToken());
				edges[i] = new Edge(start, end, weight);
			}
			Arrays.sort(edges);
			int cnt = V - 1;
			long totalWeight = 0;
			for (Edge edge : edges) {
				if (findSet(edge.start) == findSet(edge.end))
					continue;
				unionSet(edge.start, edge.end);
				cnt--;
				totalWeight += edge.weight;
				if (cnt == 0) {
					break;
				}
			}
			sb.append('#').append(tc).append(' ').append(totalWeight).append('\n');
		}
		br.close();
		System.out.print(sb.toString());
	}

	static int findSet(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	static boolean unionSet(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;

		if (aRoot >= bRoot) {
			parents[aRoot] = bRoot;
		} else {
			parents[bRoot] = aRoot;
		}
		return true;
	}

	static void make() {
		parents = new  int[V + 1];
		for (int i = 0; i < V + 1; i++) {
			parents[i] = i;
		}
	}
}