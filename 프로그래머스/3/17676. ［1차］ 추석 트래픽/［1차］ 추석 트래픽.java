import java.util.*;
class Solution {
    public static int[] start;
    public static int[] end;
    public static int max = 0;
    public static int N;
    public int solution(String[] lines) {
        int answer = 0;
        N = lines.length;
        
        start = new int[N];
        end = new int[N];
        
        for(int i=0;i<lines.length;i++) {
            String[] str = lines[i].split(" ");
            double time = doubleTime(str[1]);
            double sec = Double.parseDouble(str[2].substring(0,str[2].length()-1));
            double s = time - sec + 0.001;
            start[i] = (int)(s*1000);
            end[i] = (int)(time*1000) + 1000;
        }
        for(int i=0;i<N;i++) {
            int cnt = 0;
            for(int j=i;j<N;j++) {
                if(end[i] > start[j]) {
                    cnt++;
                }
            }
            if(answer < cnt) {
                answer = cnt;
            }
        }
        return answer;
    }
    public static double doubleTime(String s) {
        String[] str = s.split(":");
        double time = Double.parseDouble(str[0])*3600 + Double.parseDouble(str[1])*60 + Double.parseDouble(str[2]);
        return time;
    }
}