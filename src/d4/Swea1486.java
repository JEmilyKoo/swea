package d4;

import java.io.*;
import java.util.*;

public class Swea1486 {
	static int minTotal, B, N;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("./res/input1486.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			minTotal = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			subs(0, 0);
			sb.append('#').append(tc).append(' ').append(minTotal - B).append('\n');
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void subs(int cnt, int v) {
		if (cnt == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if ((v & (1 << i)) == 0) continue;
				sum += arr[i];
				if (sum >= B) {
					minTotal = Math.min(minTotal, sum);
				}
			}
			return;
		}
		subs(cnt + 1, v | (1 << cnt));
		subs(cnt + 1, v);
	}
}