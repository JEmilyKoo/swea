package d3;

import java.io.*;
import java.util.*;
 
public class Swea1873 {
    static void shoot(int r, int c, int d) {
        int lr = r;
        int lc = c;
         
        while(true) {
            lr += dr[d];
            lc += dc[d];
            if(0 > lr || lr >= H || 0 > lc || lc >= W || map[lr][lc] == '#') {
                break;
            }
            if(map[lr][lc] == '*') {
                map[lr][lc] = '.';
                break;
            }
        }
    }
 
    static void move(int d) {
        D = d;
        int lr = R + dr[d];
        int lc = C + dc[d];
 
        if (0 <= lr && lr < H && 0 <= lc && lc < W && map[lr][lc] == '.') {
            R = lr;
            C = lc;
        }
    }
 
    static void printMap(StringBuilder sb) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
    }
 
    static int H;
    static int W;
    static int N;
    static int R;
    static int C;
    static int D;
    static char[][] map;
    static int[] dr = { -1, 1, 0, 0 }; // ╩С го аб ©Л
    static int[] dc = { 0, 0, -1, 1 };
     
    static char[] tank= {'^', 'v', '<', '>'};
    static char[] orderArr= {'U', 'D', 'L', 'R'};
 
 
    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./res/input1873.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
 
            map = new char[H][W];
 
            boolean searchTank = false;
 
            for (int i = 0; i < H; i++) {
                String line = br.readLine();
 
                for (int j = 0; j < W; j++) {
                    map[i][j] = line.charAt(j);
 
                    if (!searchTank && (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>')) {
                        R = i;
                        C = j;
 
                        for(int k = 0 ; k < 4; k++) {
                            if(tank[k]==map[i][j]) {
                                D = k;
                                break;
                            }
                        }
                         
                        map[i][j] = '.';
                        searchTank = true;
                    }
                }
            }
 
            N = Integer.parseInt(br.readLine());
 
            String command = br.readLine();
            for (int i = 0; i < N; i++) {
                char order = command.charAt(i);
                boolean isShoot = true;
                for(int k = 0 ; k < 4; k++) {
                    if(orderArr[k] == order) {
                        D = k;
                        move(k);
                        isShoot = false;
                        break;
                    }
                }
                if(isShoot) shoot(R, C, D);
            }
 
            sb.append('#').append(tc).append(' ');
            map[R][C] = tank[D];
            printMap(sb);
        }
        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}