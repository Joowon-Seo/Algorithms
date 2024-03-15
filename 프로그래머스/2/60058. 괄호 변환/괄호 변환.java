import java.util.*;
class Solution {
    public String solution(String p) {
        return dfs(p);
    }
    
    public static String dfs(String w){

		if(w.length() == 0){
			return "";
		}

		String u = "";
		String v = "";
		int lcnt = 0;
		int rcnt = 0;

		for(int i=0; i<w.length(); i++){
			if(w.charAt(i) == '('){
				lcnt++;
			}else rcnt++;
			u += w.charAt(i);
			if(lcnt == rcnt){
				v = w.substring(i+1);
				break;
			}
		}

		if(isCorrect(u)){
			return u += dfs(v);
		}

		else{
			String temp = "(";
			temp += dfs(v);
			temp += ")";
			u = u.substring(1, u.length()-1);
			for(int i=0; i<u.length(); i++){
				if(u.charAt(i) =='('){
					temp += ')';
				}else{
					temp += '(';
				}
			}

			return temp;
		}

	}

	/* 올바른 괄호 문자열 체크 함수 */
	public static boolean isCorrect(String str){
		Stack<Character> st = new Stack<>();

		for(int i=0; i<str.length(); i++){
			if(str.charAt(i) == '('){
				st.push('(');
			}else{
				if(st.isEmpty() || st.peek() == ')' ){
					return false;
				}else{
					st.pop();
				}
			}
		}

		return true;
	}
}