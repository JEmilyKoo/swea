package d3;

import java.io.*;
import java.util.*;

public class Swea2805 {
	static int N, sum;
	static int[][] arr;
	static boolean[][] v;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("./res/input2805.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			v = new boolean[N][N];
			
				sum = 0;
				for (int i = 0; i < N; i++) {
					String line = br.readLine();
					for (int j = 0; j < N; j++) {
						arr[i][j] = line.charAt(j) - '0';
					}
				}
				bfs(N / 2);
			
			sb.append('#').append(tc).append(' ').append(sum).append('\n');
		}
		br.close();
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}


	static void bfs(int r) {
		Deque<int[]> deque = new ArrayDeque<int[]>();
		deque.add(new int[] { r, r, 0 });
		while (!deque.isEmpty()) {
			int[] temp = deque.poll();
			
			if(!v[temp[0]][temp[1]]) {
				sum+=arr[temp[0]][temp[1]];
				v[temp[0]][temp[1]] = true;
			}
			if (temp[2] == r)
				continue;

			int lr;
			int lc;
			for (int d = 0; d < 4; d++) {
				lr = temp[0] + dr[d];
				lc = temp[1] + dc[d];
				if (0 <= lr && lr < N && 0 <= lc && lc < N && !v[lr][lc]) {
					v[lr][lc] = true;
					sum += arr[lr][lc];
					deque.offer(new int[] { lr, lc, temp[2] + 1 });
				}
			}

		}
	}
}