import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        // 알파펫은 26, 주문서는 26진법으로 표현됨
        // n까지의 숫자를 표시하는 건 time out
        
        // bans를 순회하면서 각 주문서를 숫자로 바꿔줜다.
        // 정렬을 해준다 -> edge case가 있음 (숫자를 찾을 수록 n이 증가하는데, 정렬된 데이터가 아니면 놓침)
        // 숫자 배열을 순회하면서 n보다 작다면 n++
        // 순회 이후 n을 알파벳으로 바꿔주면 정답
        long[] banNums = new long[bans.length];
        for(int i = 0; i < bans.length; i++) {
            banNums[i] = getNum(bans[i]);
        }
        
        Arrays.sort(banNums);
        
        // 숫자를 알파벳으로 바꾸는 방법
        // 10진수를 다른 형태로 바꿀 때는 일의자리부터 바꾸어준다.
        // 몫연산자로 앞부터 바꾸어주면 변환 과정에서 필요한 자릿수를 미리 알 수 없음으로 불편하기 때문
        // 숫자를 %26 -> 일의 
        
        for(int i = 0; i < banNums.length; i++) {
            if(banNums[i] <= n) {
                n += 1;
            } else {
                break;
            }
        }
        
    
//         System.out.println(n);
//         System.out.println(Arrays.toString(banNums));
        
        String answer = getAlphabet(n);
        return answer;
    }
    
    public static long getNum(String str) {
        long num = 0;
        for(int i = 0; i < str.length(); i++) {
            int n = str.charAt(i) - 'a' + 1;
            long n2 = 1;
            for(int j = 0; j < str.length() - i - 1; j ++) {
                n2 *= 26L;
            }
            
            num += n * n2;
        }
        
        return num;
    }
    
    
    public static String getAlphabet(long n) {
        char[] alphabet = new char[26];
        for(int i = 0; i < 26; i++) {
            alphabet[i] = (char) ('a' + i);
        }
        
        StringBuilder sb = new StringBuilder();
        
        while (n > 0) {
            n -= 1;
            sb.append(alphabet[(int) (n%26)]);
            n /= 26;
        }
        
        return sb.reverse().toString();
    }
}