package d4;

import java.io.*;
import java.util.*;

public class Swea1238 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./res/input1238.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		for (int tc = 1; tc <= 10; tc++) {
			int [][] arr = new int[101][101];
			st = new StringTokenizer(br.readLine(), " ");
			int E = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			int[] V = new int[101];
			boolean[] v = new boolean[101];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < E / 2; i++) {
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				arr[n1][n2] = 1;
			}
			ArrayDeque<Integer> deque = new ArrayDeque<>();

			for (int i = 0; i < 101; i++) {
				if (arr[S][i] > 0) {
					deque.offer(i);
				}
			}
			int maxNumber = 0;
			while (!deque.isEmpty()) {
				int size = deque.size();
				maxNumber = 0;
				while (--size >= 0) {
					int t = deque.poll();
					v[t] = true;
					maxNumber = Math.max(t, maxNumber);

					for (int j = 0; j < 101; j++) {
						if(v[j]) continue;
						if (arr[t][j] > 0) {
							arr[t][j]--;
							deque.offer(j);
						}
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(maxNumber).append('\n');
		}
		br.close();
		System.out.println(sb.toString());
	}
}
