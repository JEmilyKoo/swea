package sample;

import java.io.*;
import java.util.*;

class BC {
	int x;
	int y;
	int c;
	int p;
	int index;

	public BC(int x, int y, int c, int p, int index) {
		this.x = x;
		this.y = y;
		this.c = c;
		this.p = p;
		this.index = index;
	}

	@Override
	public String toString() {
		return "BC [x=" + x + ", y=" + y + ", c=" + c + ", p=" + p + "]";
	}
}

public class Swea5644 {
	static int maxPTotal;
	static int[] aPoint;
	static int[] bPoint;
	static BC[] bcArr;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("./res/input5644.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		StringTokenizer st1;
		int T = Integer.parseInt(br.readLine());
		int m;
		int a;

		int[] aMove;
		int[] bMove;
		int[] dy = { 0, -1, 0, 1, 0 }; // 0 상 우 하 좌
		int[] dx = { 0, 0, 1, 0, -1 };

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			m = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			maxPTotal = 0;

			aMove = new int[m];
			bMove = new int[m];

			st = new StringTokenizer(br.readLine(), " ");
			st1 = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < m; i++) {
				aMove[i] = Integer.parseInt(st.nextToken());
				bMove[i] = Integer.parseInt(st1.nextToken());
			}

			bcArr = new BC[a];

			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				bcArr[i] = new BC(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
			}

			aPoint = new int[] { 1, 1 };
			bPoint = new int[] { 10, 10 };
			charge();

			for (int i = 0; i < m; i++) {
				aPoint[0] += dx[aMove[i]];
				aPoint[1] += dy[aMove[i]];
				bPoint[0] += dx[bMove[i]];
				bPoint[1] += dy[bMove[i]];
				charge();
			}

			sb.append('#').append(tc).append(' ').append(maxPTotal).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void charge() {
		List<BC> aList = new ArrayList<BC>();
		aList.add(new BC(0, 0, 0, 0, -1));

		List<BC> bList = new ArrayList<BC>();
		bList.add(new BC(0, 0, 0, 0, -1));

		for (int i = 0; i < bcArr.length; i++) {
			int distance = getDistance(aPoint[0], bcArr[i].x, aPoint[1], bcArr[i].y);
			if (bcArr[i].c >= distance) {
				aList.add(bcArr[i]);
			}

			distance = getDistance(bPoint[0], bcArr[i].x, bPoint[1], bcArr[i].y);
			if (bcArr[i].c >= distance) {
				bList.add(bcArr[i]);
			}
		}
		int maxCharge = 0;

		for (BC abc : aList) {
			for (BC bbc : bList) {
				if (abc.index == bbc.index) {
					maxCharge = Math.max(maxCharge, abc.p);
				} else {
					maxCharge = Math.max(maxCharge, abc.p + bbc.p);
				}

			}
		}
		maxPTotal += maxCharge;
	}

	static int getDistance(int ax, int bx, int ay, int by) {
		return Math.abs(ax - bx) + Math.abs(ay - by);

	}
}