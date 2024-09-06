package d2;

import java.io.*;
import java.util.*;
public class Swea14510_2 {
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
		int count = 0;
		int total = 0;
		for(int i = 0 ; i < arr.length; i++) {
			int j = max - arr[i];
			arr[i] = j;
			if(j != 0) count++;
		}
		boolean odd = true;
		int start = 0;
		
		while(count>=1) {
			for(int i = start ; i < arr.length; i++) {
				if(arr[i] != 0) break;
				start = i;
			}
			
			boolean pass = true;
			for(int i = start ; i < arr.length; i++) {
				if(arr[i] == 0) continue;
				if(odd && arr[i] %2 ==1) {
					arr[i] -=1;
				}else if(!odd && arr[i] %2 ==0) {
					arr[i] -=2;
				}else {
					continue;
				}
				total++;
				odd = !odd;
				pass = false; 
				if(arr[i]==0) count--;
			}
			if(pass) {
				boolean pass2 = true; // 짝수번인데 홀수번 뺌
				for(int i = start ; i < arr.length; i++) {
					if(arr[i] <3) continue;
					 if(!odd  && arr[i] %2 ==1) {
						arr[i] -=2;
						pass2 = false;
						total+=1;
						odd = !odd;
						break; 
					}else if(odd && arr[i] %2 ==0) {
						arr[i] -=1;
						if(arr[i]==0) count--;
						pass2 = false;
						total+=1;
						odd = !odd;
						break;
					}
					 
				} // 짝수번인데 이제 홀수인 애가 남아있지 않거나, 홀수번인데 이제 홀수인 애 가 남아있지 않음 
				if(pass2) {
					for(int i = start ; i < arr.length; i++) {
						if(arr[i] ==0) continue;
						 if(odd  && arr[i] %2 ==0) {
							arr[i] -=1;
							odd = !odd;
							break; 
						}
					}
					
					for(int i = start ; i < arr.length; i++) {
						if(arr[i] == 0) continue;
						if(odd && arr[i] == 2) {
							arr[i] -=2;
						}else if(!odd && arr[i] == 1) {
							arr[i] -=1;
						}else {
							continue;
						}
						total+=2;
						if(arr[i]==0) count--;
					}
				}
			}
		}
		return total;
	}
}
