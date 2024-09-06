package d9;


import java.io.*;
import java.util.*;

public class Swea2117 {
	static class Rt {
		int size;
		int r0, r1, r2, r3, c0, c1, c2, c3;
		int r;
		int c;
		int d;

		public Rt(Rt rt) {
			this.size = rt.size;
			this.r0 = rt.r0;
			this.r1 = rt.r1;
			this.r2 = rt.r2;
			this.r3 = rt.r3;
			this.c0 = rt.c0;
			this.c1 = rt.c1;
			this.c2 = rt.c2;
			this.c3 = rt.c3;
			this.r = rt.r;
			this.c = rt.c;
			this.d = rt.d;
		}

		int right() {
			if (d == 0) {
				return c - this.c0;
			}
			return this.c1 - this.c0;
		}

		int left() {
			if (d == 1) {
				return c1 - this.c;
			}
			return this.c1 - this.c2;
		}

		Rt(int i, int j) {
			this.size = 1;
			this.r = this.r0 = this.r1 = this.r2 = this.r3 = i;
			this.c = this.c0 = this.c1 = this.c2 = this.c3 = j;
			d = 0;
		}
	}

	static int[] dr = { 1, 1, -1, -1 };
	static int[] dc = { 1, -1, -1, 1 };
	static int[][] map;
	static int N;
	static int M;
	static int maxSize;
	static boolean pass;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./res/input2117.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= 1; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			map = new int[N][N];
			List<int[]> homeList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] ==1) {
						homeList.add(new int[] {i, j});
					}
				}
			}
			
			for(int k = N; k>=0; k--) {
				System.out.println(k);
			}
			
//			sb.append('#').append(tc).append(' ').append(maxSize).append('\n');
		}
//		System.out.print(sb.toString());
		br.close();
	}

	static void makeRt(Rt rt, boolean[] v) {
		for(int i = 0 ; i < 101; i++) {
			if(v[i]) System.out.print( " " + i);
		}
		System.out.println();
		System.out.println(" r " + rt.r + " c " + rt.c);
		System.out.println("r3 " + rt.r3 + "c3 " + rt.c3);
		if(rt.d==3&& rt.r3==rt.r && rt.r3 == rt.c) {
			System.out.println("µµÂø");
			maxSize = Math.max(maxSize, rt.size);
			return;
		}
		
		int lr = rt.r + dr[rt.d];
		int lc = rt.c + dc[rt.d];
		System.out.println("lr" +lr + "lc" + lc);
		
		
		if (0 > lr || lr >= N || 0 > lc || lc >= N)
			return;
		if (v[map[lr][lc]])
			return;
		v[map[lr][lc]] = true;
		rt.size++;
		rt.r = lr;
		rt.c = lc;
		int ld = rt.d;
		if (ld == 0) {
			makeRt(new Rt(rt), v);
			if (rt.right() > 0) {
				rt.d = 1;
				rt.r1 = lr;
				rt.c1 = lc;
				makeRt(new Rt(rt), v);
			}
		} else if (ld == 1) {
			makeRt(new Rt(rt), v);
			if (rt.left() > 0) {
				if (rt.right() + rt.left() < maxSize / 2)
					return;
				rt.d = 2;
				rt.r2 = lr;
				rt.c2 = lc;
				int r3 = rt.r0 + rt.left() -1;
				int c3 = rt.c0 - rt.left() + 1;
				System.out.println("¾Æ¿À r3" + r3 + " c3" +c3);
				if (0 > r3 || r3 >= N || 0 > c3 || c3 >= N)
					return;
				rt.r3 = r3;
				rt.c3 = c3;
				makeRt(new Rt(rt), v);
			}
		}else if (lr == rt.r3 &&  lc == rt.c3) {
			rt.d = 3;
			makeRt(new Rt(rt), v);
		}
		v[map[lr][lc]] = false;
	}
}
