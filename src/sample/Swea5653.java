package sample;

import java.io.*;
import java.util.*;

class Cell implements Comparable<Cell> {
	int r;
	int c;
	int life;
	int birthTime;
	s status;

	Cell(int r, int c, int life, int birthTime, s status) {
		this.r = r;
		this.c = c;
		this.life = life;
		this.birthTime = birthTime;
		this.status = status;
	}

	Cell(int r, int c, int life) {
		this(r, c, life, 0, s.DEACTIVE);
	}

	@Override
	public int compareTo(Cell o) {
		if (this.r == o.r) {
			if (this.c == o.c) {
				if (this.status != s.DIE) {
					if (this.status != s.ACTIVE) {
						if (this.birthTime == o.birthTime) {
							return o.life - this.life;
						}
						return this.birthTime - o.birthTime;
					}
					return -1;
				}
				return -1;
			}
			return this.c - o.c;
		}
		return this.r - o.r;
	}

	@Override
	public String toString() {
		return "Cell [r=" + r + ", c=" + c + ", life=" + life + ", birthTime=" + birthTime + ", status=" + status
				+ "]";
	}
	
	
}

enum s {
	DEACTIVE, BREED, ACTIVE, DIE
}


public class Swea5653 {
	

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("./res/input5653.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int n;
		int m;
		int k;
		int b;
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			b = k / 2 + 1;

			int[][] map = new int[k + 4 + n][k + 4 + m];


			Deque<Cell> cellQ = new ArrayDeque<Cell>();
			Deque<Cell> nowQ = new ArrayDeque<Cell>();
			int lr;
			int lc;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < m; j++) {
					lr = i + b;
					lc = j + b;
					map[lr][lc] = Integer.parseInt(st.nextToken());
					if (map[lr][lc] != 0) {
						cellQ.add(new Cell(lr, lc, map[lr][lc]));
					}
				}
			}

			for (int time = 1; time <= k; time++) {
				nowQ = new ArrayDeque<Cell>(cellQ);
				PriorityQueue<Cell>newQ  = new PriorityQueue<Cell>();
				cellQ.clear();
				
				while (!nowQ.isEmpty()) {
					Cell cell = nowQ.poll();
					if (cell.status == s.DIE || map[cell.r][cell.c] == -1) continue;
					
					switch (cell.status) {
					case ACTIVE:
						if ((time - cell.birthTime) >= 2 * cell.life) {
							cell.status = s.DIE;
							map[cell.r][cell.c] = -1; 
							continue;
						}
						break;
					case BREED:
						for (int d = 0; d < 4; d++) {
							if (map[cell.r + dr[d]][cell.c+ dc[d]]==0) {
								Cell c2 = new Cell(cell.r + dr[d], cell.c + dc[d], cell.life, time, s.DEACTIVE);
								newQ.offer(c2);
							} 	
						}

						if ((time - cell.birthTime) >= 2 * cell.life) {
							cell.status = s.DIE;
							map[cell.r][cell.c] = -1; 
							continue;
						}else {							
							cell.status = s.ACTIVE;
						}
						break;

					case DEACTIVE:
						if (cell.life == (time - cell.birthTime)) {
							cell.status = s.BREED;
						}
						break;

					default:
						break;
					}

					map[cell.r][cell.c] = cell.life;
					cellQ.offer(cell);
				}

				while(!newQ.isEmpty()) {
					Cell cell = newQ.poll();
					if (map[cell.r][cell.c] != 0) 	continue;
					map[cell.r][cell.c] = cell.life;
					cellQ.offer(cell);
				}
			}

			int cnt = 0;
			while (!cellQ.isEmpty()) {
				Cell cell = cellQ.poll();				
				if (cell.status != s.DIE) cnt++;
			}
			sb.append('#').append(tc).append(' ').append(cnt).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}