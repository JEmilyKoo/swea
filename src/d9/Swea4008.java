package d9;

import java.io.*;
import java.util.*;

public class Swea4008 {
	static int N;
	static int Min;
	static int Max;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("./res/input4008.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int tc = 1; tc <= T; tc++) {
			Min = Integer.MAX_VALUE;
			Max = Integer.MIN_VALUE;
			N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			int[] oper = new int[4];
			st = new StringTokenizer(br.readLine(), " ");

			for (int i = 0; i < 4; i++) {
				oper[i] = Integer.parseInt(st.nextToken());
			}

			int[] operArr = new int[N - 1];
			int cnt2 = 0;
			for(int i = 0 ; i < 4 ; i++) {
				for( int j = 0 ; j < oper[i];j++ ) {
					operArr[cnt2++] = i ;
				}
			}

			Arrays.sort(operArr);
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			if (tc < 4 || tc> 4 )  continue;
			System.out.println(Arrays.toString(arr));
			System.out.println(Arrays.toString(operArr));
			System.out.println(Arrays.toString(oper));
			do {
				int cnt = calculate(arr, operArr);
				
				System.out.println(cnt);
				Min = Math.min(cnt, Min);
				Max = Math.max(cnt, Max);

			} while (np(operArr));
			sb.append('#').append(tc).append(' ').append(Max-Min).append('\n');
		}
		System.out.println(sb.toString());
		br.close();
	}

	static int calculate(int[] arr, int[] operArr) {
		int result = arr[0];
		for (int i = 0; i < N - 1; i++) {
			int op = operArr[i];
			int n2 = arr[i + 1];

			result = operate(result, op, n2);
		}
		return result;
	}

	static int operate(int n1, int op, int n2) {
		switch (op) {
		case 0:
			return n1 + n2;
		case 1:
			return n1 - n2;
		case 2:
			return n1 * n2;
		default:
			return  n1 / n2;
		}
	}

	
	static boolean np(int[] p) {
		int N = p.length;
		int i = N - 1;
		while (i > 0 && p[i - 1] >= p[i]) --i;

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