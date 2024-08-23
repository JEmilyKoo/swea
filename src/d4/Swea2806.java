package d4;

import java.io.*;
import java.util.*;

public class Swea2806 {
	static int[] col;
	static boolean[] v1; // ¢Ö
	static boolean[] v2; // ¢Ù
	static int N;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("./res/input2806.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			col = new int[N+1];
			v1 = new boolean[N+1];
			v2 = new boolean[N+1];
			ans = 0;
			setQueens(1);
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		br.close();
		System.out.println(sb.toString());
	}

	static void setQueens(int rowNo) {
		if(!isAvailable(rowNo-1)) return;
		
		if (rowNo > N) {
			ans++;
			return;
		}

		for (int c = 1; c <= N; c++) {
			col[rowNo]=c;
			v1[c] = true;
			v2[c] = true;
			setQueens(rowNo+1);
		}
	}
	       
	
	static boolean isAvailable(int rowNo) {
		for(int k = 1; k <rowNo; k++) {
			if(col[rowNo] == col[k] || rowNo-k == Math.abs(col[rowNo] - col[k])) return false;
		}
		
		return true;
	}
}