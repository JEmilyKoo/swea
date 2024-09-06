package d4;

import java.io.*;
import java.util.*;

public class Swea5643 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./res/input5643.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			System.out.println("Tc----------" + tc);
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			boolean[][] arr = new boolean[N + 1][N + 1];
			int[] from = new int[N + 1];
			int[] to = new int[N + 1];
			int[] roots = new int [N+1];
			for(int i = 0 ; i < N+1; i++) {
				roots[i] =-1;
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				from[a]++; // a자리에 수가 있는지
				to[b]++; // b자리에 수가 있는지
				arr[a][b]= true;
				roots[a] += roots[b];
			}
			
			int cnt1 = 0;

			int resultCnt = 0;
			ArrayDeque<Integer> dq = new ArrayDeque<>();
			for (int i = 1; i < N + 1; i++) {
				if (from[i] == 0 && to[i] > 0) {
					resultCnt++;
				}
			}
			
			
			while (true) {
				// 베스트를 구한다.

				System.out.println(Arrays.toString(roots));
				System.out.println(cnt1 + " 번째 시도");
				for (int i = 1; i < N + 1; i++) {
					if (from[i] == 0 && to[i] > 0) {
						dq.offer(i);
						System.out.print(" dq. " + i);
					}
				}
				System.out.println();
				if(dq.isEmpty() || cnt1>=2) break;
				
				if(resultCnt != dq.size()) {
					break;
				} // 만약에 베스트의 갯수와 dq사이즈가 다르면
				int dqSize = dq.size();
				if(dq.size() == 1) {
					cnt1++;
				}
				
				boolean [] result = new boolean[N+1];
				int cur = dq.peek();
				for( int i = 1 ; i < N + 1; i++) {
					result[i] = arr[i][cur]; 
				}
				
				while(!dq.isEmpty()) {
					cur = dq.poll();
					for( int i = 1 ; i < N + 1; i++) {
						result[i] &= arr[i][cur]; 
						if(result[i]) {
							arr[i][cur] = false; // 더해놨으면 이제 필요 없어 
							from[i]--;
							to[cur]--;
							System.out.println ("roots[i]" + roots[i] + " "   + i);
							//roots[i] -= roots[cur];
							resultCnt++;	
						}
					}
				}
				
				for(boolean b : result)System.out.print( b? "1 " : "0 " );
				resultCnt = 0;
				for(boolean b : result) if(b) resultCnt++;
				System.out.println(resultCnt);
				
				//1) 1 best가 1 
				
				if(resultCnt == 1) cnt1++;
				
				//2) result 갯수가 여러개
				//result 갯수를 기억해둔다
				
				
				// 다시 베스트를 구한다
				
			}
			System.out.println(tc + "결론  " + cnt1);
			sb.append('#').append(tc).append(' ').append(cnt1).append('\n');
		}
		br.close();
		System.out.println(sb);
	}
	
	static void solve(int[] from, int [] to, boolean[][]arr, int N, int M) {
		//베스트를 구한다.
		//베스트가 1 : 더한다. 베스트의 진입 차수를 다 구한다.
		//베스트가 2 : 베스트의 진입차수를 다 구한다.
		//
		//베스트의 진입차수를 다 뺀다.
		// 

		ArrayDeque<Integer> dq = new ArrayDeque<>();
		int cnt = 0;
		while(true) {
			System.out.println("from");
			System.out.println(Arrays.toString(from));
			System.out.println("to");
			System.out.println(Arrays.toString(to));
			for(int i = 1 ; i < N + 1; i++) {
				if(from[i]==0 && to[i]>0) { // i -> 경우는 없고 ->i 경우는 많다 
					dq.offer(i);
					System.out.println("dq. i" + i);
					//2-1 4-1 3-1 삭제
			//		for(int j = 1 ; j < N+1 ; j++) {
			//			if(!arr[j][i]) continue;
			//			arr[j][i] = false; 
			//			from[j]--;
			//			to[i]--;
			//		}
					
				}
			} // 베스트를 구한다.1
			System.out.println("dqsize" + dq.size());
			if(dq.isEmpty()) break;
			int dqSize = dq.size();
			if(dq.size() == 1) {
				cnt++;
			}
			
			boolean [] result = new boolean[N+1];
			int cur = dq.peek();
			for( int i = 1 ; i < N + 1; i++) {
				result[i] = arr[i][cur]; 
			}
			
			while(!dq.isEmpty()) {
				cur = dq.poll();
				for( int i = 1 ; i < N + 1; i++) {
					result[i] &= arr[i][cur]; 
					
				}
			}
			int resultCnt = 0;
			for( int i = 1 ; i < N + 1; i++) {
				if(result[i]) {
					arr[i][cur] = false; // 더해놨으면 이제 필요 없어 
					from[i]--;
					to[cur]--;
					resultCnt++;	
				}
			}
			if(resultCnt ==1 && dqSize>1) cnt++;
			System.out.println(Arrays.toString(result));
		}
	}
}
