package d2;

import java.io.*;
import java.util.*;
public class Swea14510 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./res/input14510.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int [] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			int max = 1;
			for(int i = 0 ; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if(arr[i]>max) max = arr[i];
			}
			
			sb.append('#').append(tc).append(' ').append(solve(arr, max)).append('\n');
		}
		System.out.println(sb);
		br.close();
	}

	static int solve(int[] arr, int max) {
		int cnt = 0;
		int cnt1 = 0;
		int cnt2 = 0;
		for(int i = 0 ; i < arr.length ; i++) {
			int gap = max - arr[i];
			cnt += (gap/3) * 2;
			
			if(gap %3 == 1) {
				cnt1++;
			}else if(gap % 3 == 2) {
				cnt2++;
			}
		}
		
		if(cnt1>=cnt2) {
			cnt += cnt2 * 2;
			cnt1 -= cnt2;
			cnt2 = 0;
			
			
			if(cnt1 >1) {
				while(cnt1 >= 3) {
					int slice = cnt1 / 3;
					cnt1 -= slice * 2;
					cnt += slice * 2;
				}
				
				if(cnt1==1) {
					cnt++;

				}else if(cnt1 ==2 && cnt % 2 ==0) {
					cnt+=2;
					cnt+=1000000;	
				}
				
			
				
			}else if(cnt1 ==1) {
					cnt++;
				
			}
			
		}else {
			cnt += cnt1 * 2;
			cnt2 -= cnt1;
			cnt1 = 0;
			
			while(cnt2>2) {
				int slice = cnt2/3;
				cnt2 -= slice * 2;
				cnt+= slice * 4;
			}
			
			if(cnt2==2 ) {
				cnt+=2;
			}
			else if(cnt2 ==1) {
				if(cnt %2 ==0) {
					cnt+=2;
				}else {
					cnt+=1;
				}
			}
		}
		
		return cnt;
	}
}
