package d4;

import java.io.*;
import java.util.*;

public class Swea3289 {
	static int N;
	static int[] parents;
	static int[] rank;

	static void make() {
		for (int i = 1; i < N + 1; i++) {
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
		
		if(rank[a] > rank[b]) {
			parents[bRoot] = aRoot;		
		}else if (rank[a] < rank[b]) {
			parents[aRoot] = bRoot;		
		}else {
			parents[bRoot] = aRoot;
			rank[a]++;
		}
		
		return true;
	}

	static int isUnion(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		return aRoot == bRoot ? 1 : 0;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./res/input3289.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			parents = new int[N + 1];
			rank = new int[N + 1];
			make();
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int o = Integer.parseInt(st.nextToken());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());

				if (o == 1) {
					if(n1==n2) {
						sb.append(1);
					}else {
						sb.append(isUnion(n1, n2));
					}
				} else if (n1 != n2) {
					union(n1, n2);
				}
			}
			sb.append('\n');
		}
		br.close();
		System.out.println(sb.toString());
	}

}
