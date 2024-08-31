package d9;

import java.io.*;
import java.util.*;

public class Swea2383 {
	
	
	
	
	0123456이 있어
	
	1번에 가는 경우랑 0번에 가는 경우의 수가 있어
	
	
	0101011
	0101010
	
	뭐 이런 경우의 수가 쫙 있어
	이걸 전부 계산을 해
	
	
	얘네는 그럼 큐에 분리를 한 다음에
	3칸 차면 들어가고 3칸 차면 들어가는 식이겠지
	
	들어갔다가 나가고 나가고 나가고 나가고 나가고 나가고 나가고 나가고
	
	
	시간이 너무 지났다 싶으면 x
	
	-> 진짜 이딴 식으로 풀어야 함??????????????????
			
	제일 빠를 것 같은 경우의 수를 구해
	
	
	거리순으로 소팅을 했을 때 제일 가까운 애
	
	
     
	
	
	
	
	
	
	
	static class Edge implements Comparable<Edge> {
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

	static int V;
	static int[] parents;
	static Edge[] edges;

	static void make() {
		parents = new int[pList.size() + 2];
		for (int i = 0; i < pList.size() + 2; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = findSet(parents[a]);
	}// pass compression

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

	static boolean isUnion(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		return aRoot == bRoot;
	}

	static int s1r;
	static int s1c;
	static int s1d;
	static int s2r;
	static int s2c;
	static int s2d;
	static List<int[]> pList;
	static ArrayDeque<int[]> s1;
	static ArrayDeque<int[]> s2;
	static int pCnt;
	static int [][] sArr;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./res/input2383.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 1; tc++) {
			System.out.println("tc" + tc);
			sArr = new int[2][3];
			s1r = -1;
			s1c = -1;
			s1d = -1;
			s2r = -1;
			s2c = -1;
			s2d = -1;

			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			pList = new ArrayList<>();
			pCnt = 2;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						pList.add(new int[] { i, j, 0, 0, pCnt++, 0 });
						continue;
					}

					if (s2r != -1)
						continue;
					if (s1r == -1 && map[i][j] >= 2) {
						s1r = i;
						s1c = j;
						s1d = map[i][j];
						sArr[0][0] = i;
						sArr[0][1] = j;
						sArr[0][2] = map[i][j];
					} else if (map[i][j] >= 2) {
						s2r = i;
						s2c = j;
						s2d = map[i][j];
						sArr[1][0] = i;
						sArr[1][1] = j;
						sArr[1][2] = map[i][j];
					}
				}
			}
			makePList();
			Collections.sort(pList, ((o1, o2) -> o1[5] -  o2[5]));
			make();

			s1 = new ArrayDeque<>();
			s2 = new ArrayDeque<>();

			int time = pList.get(0)[5];
			while (true) {
				show(time);
				if (pCnt == 2)
					break;
				if (s1.size() != 3 && s2.size() != 3) {
					List<int[]> bothList = new ArrayList<int[]>();
					System.out.println("현재시각 " + time);
					for (int[] p : pList) {
						System.out.println("p " + p[0]+  "," + p[1] +" "  + (p[4] -1) +"번" + " 예상 시간 " + p[5]);
					
						if ((6 - s1.size() - s2.size()) == bothList.size())
							break;
						if (isUnion(p[4], 0) || isUnion(p[4], 1))
							continue;
						if (p[3] > time -1 && p[2] <= time -1  && s1.size() < 3) {
							System.out.println("p  s1 삽입 " + p[0]+  "," + p[1] +" "  + (p[4] -1) +"번" + " 예상 시간 " + p[5]);
							
							parents[p[4]] = 0;
							s1.add(p);
						} else if (p[3] <= time  -1&& p[2] > time -1  && s2.size() < 3) {
							parents[p[4]] = 1;
							System.out.println("p  s2 삽입 " + p[0]+  "," + p[1] +" "  + (p[4] -1) +"번" + " 예상 시간 " + p[5]);
							
							s2.add(p);
						} else if ((6 - s1.size() - s2.size() > bothList.size()) && p[2] <= time - 1 && p[3] <= time -1 ) {
							System.out.println("p  both 삽입 " + p[0]+  "," + p[1] +" "  + (p[4] -1) +"번" + " 예상 시간 " + p[5]);
							
							bothList.add(p);
						}

					}

					for (int[] p : bothList) {
						if (s1.size() >= s2.size() && s2.size() < 3) {
							parents[p[4]] = 0;
							s2.offer(p);
						} else if (s2.size() >= s1.size() && s1.size() < 3) {
							parents[p[4]] = 1;
							s1.offer(p);
						}
					}
				}
				System.out.println("===============walk");
//				show(time);

				walk(s1, time, 0);
				walk(s2, time, 1);
				time++;
			}
//			for(int[] a : pList)System.out.println(Arrays.toString(a));
			/*
			 * Arrays.sort(edges); make(); int cnt = 0, cost = 0; for (Edge edge : edges) {
			 * if (union(edge.start, edge.end)) { cost += edge.weight; if (++cnt == V - 1)
			 * break; } } System.out.println(cost);
			 * System.out.println(Arrays.toString(parents));
			 */
			sb.append('#').append(tc).append(' ').append(time).append('\n');
		}
		System.out.println(sb);
	}

	static void makePList() {
		for (int[] p : pList) {
			p[2] = Math.abs(p[0] - s1r) + Math.abs(p[1] - s1c);
			p[3] = Math.abs(p[0] - s2r) + Math.abs(p[1] - s2c);
			p[5] = Math.min(p[2] + s1d, p[3] + s2d);
		}
	}

	static void walk(ArrayDeque<int[]> s, int time, int d) {
		for (int i = 0; i < 3; i++) {
			if (s.isEmpty())
				break;
			int[] p = s.poll();
			System.out.println("p[d+2] " + (p[d+2]+ sArr[d][2]) + "나갈 시간 " + (time + 1));
			if (p[d+2]  + sArr[d][2] >= time + 1) {
				s.offer(p);
			} else {
				System.out.println("p  탈출  " + p[0]+  "," + p[1] +" "  + (p[4] -1) +"번" + " 예상 시간 " + p[5]);
				pCnt--;
			}
			p[d+2]--;
		}
	}

	static void show(int time) {
		System.out.println("---it's show time-------" + time + "----------");
		System.out.println(pCnt + " pCnt");
		System.out.println("s1d" + s1d + " s2d " + s2d);
		System.out.println("---parents=--");
		System.out.println(Arrays.toString(parents));
		System.out.println("-pList--");
		for (int[] a : pList) {
			System.out.print(parents[a[4]]);
			System.out.println(Arrays.toString(a));
		}
		System.out.println();
		System.out.println("--stair1 -");
		for (int[] a : s1)
			System.out.println(Arrays.toString(a));
		System.out.println();
		System.out.println("--stair2 -");
		for (int[] a : s2)
			System.out.println(Arrays.toString(a));
		System.out.println();
	}
}
