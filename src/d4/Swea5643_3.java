package d4;

import java.io.*;
import java.util.*;

public class Swea5643_3 { // BFS
	
	static int N, adjMatrix [][];
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./res/input5643.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			StringTokenizer st = null;
			adjMatrix = new int[N + 1][N + 1];
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMatrix[a][b]= 1;
			}
			
			int ans = 0;
			
			for(int i = 1; i <= N; i++) {
				if(gtBFS(i) + ltBFS(i) == N-1) ans++;
			}

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		br.close();
		System.out.println(sb);
	}
	

	private static int gtBFS(int start) { // 나보다 큰 학생 따라 탐색
		int cnt = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		boolean [] visited = new boolean[N+1];
		
		queue.offer(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for(int i = 1 ; i <= N; i++) {
				if(!visited[i] && adjMatrix[cur][i] != 0) {
					queue.offer(i);
					visited[i] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	private static int ltBFS(int start) { // 나보다 작은 학생 따라 탐색
		int cnt = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		boolean [] visited = new boolean[N+1];
		
		queue.offer(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for(int i = 1 ; i <= N; i++) {
				if(!visited[i] && adjMatrix[i][cur] != 0) {
					queue.offer(i);
					visited[i] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}
}
