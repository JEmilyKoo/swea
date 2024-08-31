package d9;

import java.io.*;
import java.util.*;

public class Swea1767 {
	static int N;
	static int[][] map;
	static int maxCore;
	static int minWireTotal;
	static List<int[]> coreList;
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./res/input1767.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			coreList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1 && (i != N - 1 && i != 0 && j != 0 && j != N - 1)) {
						coreList.add(new int[] { i, j });
					}
				}
			}
			maxCore = 0;
			minWireTotal = Integer.MAX_VALUE;

			subs(0, coreList.size(), 0, 0, map);
			sb.append('#').append(tc).append(' ').append(minWireTotal).append('\n');
		}

		System.out.print(sb);
		br.close();
	}

	static void subs(int cnt, int r, int core, int wireTotal, int[][] map) {
		if (cnt == r) {
			if (maxCore < core) {
				minWireTotal = wireTotal;
				maxCore = core;
			} else if (maxCore == core) {
				minWireTotal = Math.min(wireTotal, minWireTotal);
			}
			return;
		} else if (r - cnt + core < maxCore) {
			return;
		}

		for (int d = 4; d >= 1; d--) {
			int wire = valid(cnt, d, map);
			if (wire == -1)
				continue;
			connect(cnt, d, map, 1, wire);
			subs(cnt + 1, r, core + 1, wireTotal + wire, map);
			connect(cnt, d, map, 0, wire);
		}
		subs(cnt + 1, r, core, wireTotal, map);
	}

	static int valid(int cnt, int d, int[][] map) {
		int lr = coreList.get(cnt)[0];
		int lc = coreList.get(cnt)[1];
		int wire = 0;
		while (true) {
			lr += dr[d];
			lc += dc[d];
			if (0 > lr || lr >= N || 0 > lc || lc >= N)
				return wire;
			wire++;
			if (map[lr][lc] == 1)
				return -1;
		}
	}

	static void connect(int cnt, int d, int[][] map, int value, int wire) {
		int lr = coreList.get(cnt)[0];
		int lc = coreList.get(cnt)[1];
		for (int i = 0; i < wire; i++) {
			lr += dr[d];
			lc += dc[d];
			map[lr][lc] = value;
		}
	}
}
