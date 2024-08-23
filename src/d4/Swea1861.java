package d4;

import java.io.*;
import java.util.*;

public class Swea1861 {
	static int N, MaxRoomCnt, MinRoomNumber;
	static int[][] arr;
	static boolean[][] v;
	static List<int[]> roomList;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("./res/input1861.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= 1; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			v = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			roomList = new ArrayList<>();
			Deque<int[]> deque = new ArrayDeque<>();
			
			deque.add(new int[] {N/2,N/2});
			
			while(!deque.isEmpty()) {
				int[] t = deque.poll();
				v[t[0]][t[1]] = true;
				
				
				
			}
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

}