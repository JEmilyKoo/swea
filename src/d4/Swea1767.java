package d4;

import java.io.*;
import java.util.*;

public class Swea1767 {
	static int maxCoreCnt;
	static int minWireTotal;
	static int [][] arr;
	static int [] dr = {-1, 1, 0, 0};
	static int [] dc = {0, 0, -1 , 1};
	static List <int[]>coreList;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("./res/input1767.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int N;
		StringTokenizer st;
		for(int tc = 1; tc <= 1; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			maxCoreCnt = 0;
			minWireTotal = Integer.MAX_VALUE;
			coreList = new ArrayList<int[]>();
			
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0 ; j < N ; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j] == 1 && (i!=0 && i!= (N-1) && j!=0 && j!=(N-1))) {
						coreList.add(new int[] {i, j});
					}
				}
			}
			subs(0,0,0,coreList.size());
			sb.append('#').append(tc).append(' ').append(minWireTotal).append('\n');
		}
		br.close();
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	
	static void paint(int lr, int lc, int paintCnt, int d, int value) {
		int r = lr;
		int c = lc;
		for(int i = 0 ; i < paintCnt; i++) {
			r += dr[d];
			c += dc[d];
			arr[r][c] = value;
		}
	}
	
	static int getWireCnt(int lr, int lc, int d) {
		int cnt=0;
		int r = lr;
		int c = lc;
		while(true) {
			r += dr[d];
			c += dc[d];
			if(0 > r || r >= arr.length || 0 > c || c >= arr.length) break;
			if(arr[r][c] ==1) return 0;
			cnt++;
		}
		return cnt;
	}
	
	static void subs(int cnt, int coreCnt, int wireTotal, int r) {
		if(cnt == r) {
			if (coreCnt > maxCoreCnt) {
				maxCoreCnt = coreCnt;
				minWireTotal = wireTotal;
			} else if(coreCnt == maxCoreCnt) {
				minWireTotal = Math.min(wireTotal, minWireTotal);
			}
			return;
		}
		for(int d = 0 ; d < 4; d++) {
			int lr = coreList.get(cnt)[0];
			int lc = coreList.get(cnt)[1];
			int wire = getWireCnt(lr, lc, d);
			if(wire>0) {
				paint(lr, lc, wire, d, 1);
				subs(cnt+1, coreCnt+1, wireTotal+wire, r);
				paint(lr, lc,wire, d, 0);
			}
		}
		subs(cnt+1, coreCnt, wireTotal, r);
	}
}