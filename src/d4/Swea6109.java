package d4;

import java.io.*;
import java.util.*;

public class Swea6109 {
	static int minTotal, B, N;
	static int[][] arr;
	static boolean[][] v;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("./res/input6109.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append('\n');
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			String command = st.nextToken();
			
			int[][] arr1 = new int[N][N];
			
			boolean[][] v = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for( int j = 0 ; j < N; j++) {
					arr1[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			arr = new int[N][N];
			switch (command) {
			case "left":
				for (int i = 0; i < N; i++) {
					for( int j = 0 ; j < N; j++) {
						arr[i][j] = arr1[i][N-j-1];
					}
				}
				break;

			case "up":
				for (int j = 0; j < N; j++) {
					for( int i = 0 ; i < N; i++) {
						arr[j][i] = arr1[N-i-1][j];
					}
				}
				break;

			case "down":
				for (int j = 0; j < N; j++) {
					for( int i = 0 ; i < N; i++) {
						arr[i][j] = arr1[j][i];
					}
				}
				break;

			default:
				arr = arr1;
				break;
			}
			
			
			for (int i = 0; i < N; i++) {
				int p = N;
				int zeroCnt = 0;
				int limitP = N-1;
				while(p> 1) {
					p--;
					p = Math.min(limitP, p);
					int p1 = arr[i][p];
					int p2 = arr[i][p-1];
					if(p1 == 0 && p2 != 0) {
						arr[i][p+zeroCnt] = p2;
						arr[i][p-1] = 0;
						
						boolean temp = v[i][p+zeroCnt];
						v[i][p+zeroCnt] = v[i][p-1];
						v[i][p-1] = temp;
						
						if(p>1) {
							
						}
						if(p>1 &&  arr[i][p] == arr[i][p-2]) {
							if(v[i][p]) continue;
							arr[i][p] *= 2;
							arr[i][p-2] = 0;
							v[i][p] = true;
							v[i][p-2] = false;
						}
						
					}
					else if(p1 == 0 && p2 == 0) {
						zeroCnt++; 
						p--;
					}
					else if(p1==p2) { 
						if(v[i][p]) continue;
						arr[i][p] *= 2;
						//p++;
						if(p>2) {
							arr[i][p -1] = arr[i][p-2];
							arr[i][p-2] = 0;
							p--;
						}else {
							arr[i][p-1] = 0;
						}
						v[i][p]=true;
						limitP = p-1;
					}
					else {
					}
				}
			}
			
			switch (command) {
			case "left":
				for (int i = 0; i < N; i++) {
					for( int j = 0 ; j < N; j++) {
						arr1[i][j] = arr[i][N-j-1];
					}
				}
				break;

			case "up":
				for (int j = 0; j < N; j++) {
					for( int i = 0 ; i < N; i++) {
						arr1[j][i] = arr[i][N-j-1];
					}
				}
				
				break;

			case "down":
				for (int j = 0; j < N; j++) {
					for( int i = 0 ; i < N; i++) {
						arr1[j][i] = arr[i][j];
					}
				}
				break;

			default:
				arr1 = arr;
				break;
			}
			
			for (int i = 0; i < N; i++) {
				for( int j = 0 ; j < N; j++) {
					sb.append(arr1[i][j]); 
					if(j!=N-1) {
						sb.append(' ') ;
					}
				}
				sb.append('\n');
			}
			
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
}