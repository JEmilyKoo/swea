package d9;
import java.io.*;
import java.util.*;

public class Swea1949 {
	static int maxLength;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int N;
	static int K;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./res/input1949.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			int max = 0;
			List<Point> dq = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(max< map[i][j]) {
						dq.clear();
						dq.add(new Point(i, j));
						max = map[i][j];
					}else if (max== map[i][j]){
						dq.add(new Point(i, j));
					}
				}
			}

			maxLength = 0;
			for(Point point : dq) {
				boolean [][] v = new boolean[N][N];
				v[point.r][point.c] = true;
				dfs(point, v);
			}
			sb.append('#').append(tc).append(' ').append(maxLength).append('\n');
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void dfs(Point cur, boolean[][] v) {
		int location = map[cur.r][cur.c];
		maxLength = Math.max(cur.d, maxLength);
		if(cur.jump == 0) {
			location = (cur.before - 1);
			cur.jump = -1;
		}
		if(cur.jump == -1 && (cur.before == 1)) {
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int lr = cur.r + dr[d];
			int lc = cur.c + dc[d];
			if (0 <= lr && lr < N && 0 <= lc && lc < N) {
				if(v[lr][lc]) continue;
				v[lr][lc] = true;
				if (location > map[lr][lc]) {  
					dfs(new Point(lr, lc, (cur.d + 1), cur.jump, location),v);
				}

				else if(cur.jump == 1 && location > (map[lr][lc] - K)) {
					dfs(new Point(lr, lc, (cur.d + 1), 0, location),v);
				}
				v[lr][lc] = false;	
			}
		}
	}

	static class Point {
		int r, c, d, before,jump;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
			this.d = 1;
			this.jump = 1;
			this.before = -1;
		}

		public Point(int r, int c, int d, int jump, int before) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.jump = jump;
			this.before = before;
		}
	}

}
