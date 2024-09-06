package d9;


import java.io.*;
import java.util.*;

public class Swea2115 {
	static int N;
	static int[][] map;
	static int[][] map2;
	static int[][] map3;
	static int maxCore;
	static int minWireTotal;
	static List<int[]> coreList;
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./res/input2115.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			map2 = new int[N+M][N+M];
			map3 = new int[N+M][N+M];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					int k  = Integer.parseInt(st.nextToken());
					map[i][j] = k;
//					if(j<=N-M) {
						System.out.println(j);
						for(int l = 0 ; l <M;l++) {
							if(j+l<M-1 || N<= j+l) continue;
							if(map2[i][j+l] == -1) continue;
							map3[i][j+l] += k;
							if(map3[i][j+l]>C) {
								map2[i][j+l] = -1;
							}else {
								
								map2[i][j+l] += k*k;
							}
						}
//					}
				}
			}

//			for(int [] c : map3) System.out.println(Arrays.toString(c)); 
			for(int [] c : map2) System.out.println(Arrays.toString(c)); 
			/*
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxCore = 0;
			minWireTotal = Integer.MAX_VALUE;

			subs(0, coreList.size(), 0, 0, map);
			sb.append('#').append(tc).append(' ').append(minWireTotal).append('\n');
		*/
		}

		System.out.print(sb);
		br.close();
	}


}
