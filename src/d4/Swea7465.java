package d4;

import java.io.*;
import java.util.*;

public class Swea7465 {
	static int[] parents;
	static int[] rank;
	static int cnt;

	static void make(int n) {
		for (int i = 1; i < n + 1; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int a) {
		if (a != parents[a]) {
			parents[a] = findSet(parents[a]);
		}
		return parents[a];
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;
		cnt--;

		if (rank[a] > rank[b]) {
			parents[bRoot] = aRoot;
		} else if (rank[a] < rank[b]) {
			parents[aRoot] = bRoot;
		} else {
			parents[bRoot] = aRoot;
			rank[a]++;
		}

		return true;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./res/input7465.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			cnt = N;
			parents = new int[N + 1];
			rank = new int[N + 1];
			make(N);
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				if (n1 == n2)
					continue;
				union(n1, n2);
			}
			sb.append('#').append(tc).append(' ').append(cnt).append('\n');
		}
		br.close();
		System.out.println(sb.toString());
	}

}
