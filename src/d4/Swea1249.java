package d4;

import java.io.*;
import java.util.*;

public class Swea1249 {
	static int N;
	static int[][] map;
	static int[][] distance;
	static boolean[][] v;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./res/input1249.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			distance = new int[N][N];
			v = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				Arrays.fill(distance[i], Integer.MAX_VALUE);
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}

			sb.append('#').append(tc).append(' ').append(dijkstra()).append('\n');
		}
		br.close();
		System.out.println(sb.toString());
	}

	static int dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		pq.offer(new int[] { 0, 0, 0 });
		distance[0][0] = map[0][0];
		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			v[now[1]][now[2]] = true;
			for(int d = 0 ; d < 4 ; d++) {
				int lr = now[1] + dr[d];
				int lc = now[2] + dc[d];
				if(0 <= lr && lr < N && 0 <= lc && lc < N && !v[lr][lc]) {
					int newDist = now[0] + map[lr][lc];
					if(newDist < distance[lr][lc]) {
						distance[lr][lc] = newDist;
						pq.offer(new int[] {newDist, lr,lc});
					}
				}
			}
		}
		return distance[N - 1][N - 1];
	}
}
