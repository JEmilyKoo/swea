package sample;

import java.io.*;
import java.util.*;

public class Swea2112 {
	static int[][] arr;
	static int d;
	static int w;
	static int k;
	static int minCount;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("./res/input2112.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			arr = new int[d][w];
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			minCount = Integer.MAX_VALUE;
			subs(0,0,  d, "");

			sb.append('#').append(tc).append(' ').append(minCount).append('\n');
		}
		System.out.println(sb.toString());
		br.close();
	}

	static int[][] inject(String str) {
		int[][] tempArr = new int[d][w];
		for (int i = 0; i < d; i++) {
			char nowChar = str.charAt(i);
			if (nowChar == '0') {
				tempArr[i] = Arrays.copyOf(arr[i], w);
			} else {
				int paint = nowChar == 'A' ? 0 : 1;
				for (int j = 0; j < w; j++) {
					tempArr[i][j] = paint;
				}
			}
		}
		return tempArr;
	}

	static void subs(int cnt, int ab, int r, String str) {
		if(ab >minCount) {
			return;
		}
		if (cnt == r) {
			int [][]tempArr = inject(str);
			if(test(tempArr)) {
				minCount = Math.min(minCount, ab);
			}
			return;
		}

		subs(cnt + 1,ab,  r, str + '0');
		subs(cnt + 1,ab + 1, r, str + 'A');
		subs(cnt + 1,ab + 1, r, str + 'B');
	}

	static boolean test(int[][] tempArr) {
		for (int j = 0; j < w; j++) {
			int tmp = tempArr[0][j];
			int cnt = 1;
			for (int i = 1; i < d; i++) {
				if (tempArr[i][j] == tmp) {
					cnt++;
				} else {
					tmp = tempArr[i][j];
					cnt = 1;
				}
				if (cnt == k) {
					break;
				}
			}
			if (cnt < k) {
				return false;
			}
		}
		return true;
	}
}