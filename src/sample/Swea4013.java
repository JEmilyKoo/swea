package sample;

import java.io.*;
import java.util.*;

public class Swea4013 {

	static int[] dc = { -1, 0, 1 };
	static List<Deque<Boolean>> mList;
	static int[] output;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("./res/input4013.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int k;
		Deque<int[]> todo;
		for (int tc = 1; tc <= T; tc++) {
			todo = new ArrayDeque<int[]>();
			mList = new ArrayList<Deque<Boolean>>();
			k = Integer.parseInt(br.readLine());

			for (int i = 0; i < 4; i++) {
				Deque<Boolean> deque = new ArrayDeque<Boolean>();
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 8; j++) {
					deque.offer((st.nextToken().equals("1") ? true : false));
				}
				mList.add(deque);
			}

			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int m = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				todo.offer(new int[] { m - 1, t });
			}

			while (!todo.isEmpty()) {
				int[] mt = todo.poll();
				findTurn(mt[0], mt[1]);
				
				for (int i = 0; i < 4; i++) {
					if (output[i] != 0) {
						turn(i, output[i]);
					}
				}
			}

			int score = countScore();
			sb.append('#').append(tc).append(' ').append(score).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int countScore() {
		int score = 0;
		for (int i = 0; i < 4; i++) {
			Deque<Boolean> magnet = mList.get(i);
			if (magnet.poll())
				score += Math.pow(2, i);
		}
		return score;
	}

	static void turn(int m, int d) {
		Deque<Boolean> magnet = mList.get(m);
		if (d == -1) {
			magnet.offer(magnet.poll());
		} else {
			magnet.offerFirst(magnet.pollLast());
		}
	}

	static boolean checkTurn(int m1, int m2) {
		Object[] magnet1 = mList.get(m1).toArray();
		Object[] magnet2 = mList.get(m2).toArray();
		return magnet1[2] != magnet2[6];
	}

	static void findTurn(int m, int t) {
		visited = new boolean[4];
		output = new int[4];
		visited[m] = true;
		output[m] = t;

		Deque<int[]> deque = new ArrayDeque<int[]>();
		deque.offer(new int[] { m, t });

		while (!deque.isEmpty()) {
			int[] que = deque.poll();
			for (int d = 0; d < 2; d++) {
				int lc1 = que[0] + dc[d];
				int lc2 = que[0] + dc[d + 1];
				int lc3 = que[0] + dc[d] + dc[d + 1];
				if (0 > lc1 || lc2 >= 4 || visited[lc3]) continue;
				visited[lc3] = true;
				if (checkTurn(lc1, lc2)) {
					output[lc3] = que[1] * -1;
					deque.offer(new int[] { lc3, que[1] * -1 });
				}

			}
		}
	}

}