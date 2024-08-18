package d4;

import java.io.*;
import java.util.*;

public class Swea3234 {
	static int count;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("./res/input3234.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int N;
		StringTokenizer st;

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			count = 0;
			int [] arr = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			subs(arr, 0, 0, 0, 0, N);
			sb.append('#').append(tc).append(' ').append(count).append('\n');

		}
		br.close();
		System.out.print(sb.toString());
	}

	static void subs(int [] arr, int v, int left, int right, int d, int r) {
		if (left < right)
			return;
		if (d == r) {
			count += 1;
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if ((v & (1 << i)) != 0) continue;
			int vv = v ^ (1 << i);
			subs(arr, vv, left + arr[i], right, d + 1, r);
			subs(arr, vv, left, right + arr[i], d + 1, r);
		}
	}
}