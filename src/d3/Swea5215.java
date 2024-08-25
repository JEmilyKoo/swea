package d3;

import java.io.*;
import java.util.*;

public class Swea5215 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("./res/input5215.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int N;
		int L;
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			int maxTatesTotal = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			int[][] arr = new int[N][2];
			int[] indexArr = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());

				arr[i][0] = n1;
				arr[i][1] = n2;
			}

			Arrays.fill(indexArr, 1);
			for (int j = 0; j < N; j++) {
				Arrays.sort(indexArr);
				if(j>0) indexArr[j-1] = 0;
				do {
					int tastesSum = 0;
					int calSum = 0;
					for (int i = 0; i < N; i++) {
						if (indexArr[i] == 0)
							continue;

						calSum += arr[i][1];
						if (calSum > L)
							break;
						tastesSum += arr[i][0];
					}
					maxTatesTotal = Math.max(maxTatesTotal, tastesSum);

				} while (np(indexArr));
			}

			sb.append('#').append(tc).append(' ').append(maxTatesTotal).append('\n');
		}
		br.close();
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static boolean np(int[] p) {
		int N = p.length;
		int i = N - 1;
		while (i > 0 && p[i - 1] >= p[i])
			--i;

		if (i == 0)
			return false;
		int j = N - 1;
		while (p[i - 1] >= p[j])
			--j;
		swap(p, i - 1, j);

		int k = N - 1;
		while (i < k) {
			swap(p, i++, k--);
		}
		return true;
	}

	static void swap(int[] p, int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}
}