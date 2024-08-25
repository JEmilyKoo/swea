package d9;

import java.io.*;
import java.util.*;

public class Swea4012 {
	static int N;
	static boolean[] v;
	static boolean[] v1;
	static int[] aArr;
	static int[] bArr;
	static int[] s1;
	static int[] s2;
	static int diffASum;
	static int diffBSum;
	static int[][] arr;

	static int minDiff ;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("./res/input4012.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(i>j) {
						System.out.println(i + " " + j);
						arr[i][j] += arr[i][j];
					}
				}
			}
			minDiff = Integer.MAX_VALUE;
			aArr = new int[N / 2];
			bArr = new int[N / 2];
			v1 = new boolean[N];
			diffASum = 0;
			diffBSum = 0;
			s1 = new int[2];
			s2 = new int[2];
			comb1(0, 0);
			sb.append('#').append(tc).append(' ').append(minDiff).append('\n');
		}
		System.out.println(sb.toString());
		br.close();
	}


	static void comb(int cnt, int start) {
		if (cnt == 2) {
		//	System.out.println(Arrays.toString(s1));
			diffASum += arr[s1[0]][s1[1]] + arr[s1[1]][s1[0]];
			diffBSum += arr[s2[0]][s2[1]] + arr[s2[1]][s2[0]];
			return;
		}
		for (int i = start; i < N / 2; i++) {
			if (v[i]) continue;
			v[i] = true;
			s1[cnt] = aArr[i];
			s2[cnt] = bArr[i];
			comb(cnt + 1, i + 1);
			v[i] = false;
		}
	}

	static void comb1(int cnt, int start) {
		if (cnt == N / 2) {
			int aCnt = 0;
			int bCnt = 0;
			for (int i = 0; i < N; i++) {
				if (v1[i]) {
					aArr[aCnt++] = i;
				} else {
					bArr[bCnt++] = i;
				}
			}
			v = new boolean[N / 2]; 
			diffASum = 0;
			diffBSum = 0;
			comb(0, 0);
			minDiff = Math.min(Math.abs(diffASum - diffBSum), minDiff);
			return;
		}
		for (int i = start; i < N; i++) {
			if (v1[i]) continue;
			v1[i] = true;
			comb1(cnt + 1, i + 1);
			v1[i] = false;
		}
	}
}