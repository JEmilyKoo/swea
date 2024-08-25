package d4;

import java.io.*;
import java.util.*;

public class Swea4796 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("./res/input4796.txt")));
	//	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
	//	int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
		//	st = new StringTokenizer(br.readLine(), " ");
			arr[0] = sc.nextInt();
			int up = 0;
			int down = 0;
			int sum = 0;
			for (int i = 1; i < N; i++) {
				arr[i] = sc.nextInt();
				if((arr[i-1] -arr[i]) < 0) {	
					if(down>0) {
						sum += up * down;
						up = 0;
						down = 0;
					}
					up++;
				}else {
					down++;
					if(i == N-1) {
						sum += up * down;
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(sum).append('\n');
		}
		//br.close();
		System.out.print(sb.toString());
	}
}