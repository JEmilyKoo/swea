package d9;
import java.io.*;
import java.util.*;

public class Swea1952 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./res/input1952.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int month1 = Integer.parseInt(st.nextToken());
			int month3 = Integer.parseInt(st.nextToken());
			int minCost = Integer.parseInt(st.nextToken()); 
			st = new StringTokenizer(br.readLine());
			int [] plan = new int [13];
			int [] monthPlan = new int[13];
			int [] month3Plan = new int[13];
			for (int i = 1 ; i <= 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken()); 
				monthPlan[i] = Math.min(plan[i] * day, month1);
				if(i>=3) {
					month3Plan[i-2] += monthPlan[i];
					month3Plan[i-1] += monthPlan[i];
					month3Plan[i-0] += monthPlan[i];
				}
			}

			System.out.println(Arrays.toString(monthPlan));
			System.out.println(Arrays.toString(month3Plan));
			
			
			int cost = 0;
			int month = 1;
			while(month <=12) {
				int max = 0;
				for(int i = 1; i <= 10; i++) {
					
				}
				
				if(month<=10) {
					int cost3 = 0;
					for(int i = 0 ; i <3; i++) {
						cost3 += monthPlan[month + i];
					} 
					if(month3<cost3) {
						cost+= month3;
						month+=3;
						continue;
					}
				}
				cost+=monthPlan[month];
				month++;
			}
			minCost = Math.min(cost, minCost);
			
			sb.append('#').append(tc).append(' ').append(minCost).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
