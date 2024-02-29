import java.util.*;
class Solution {
    public String solution(String new_id) {
        String[] id = new_id.split("");
		String[] id_level1 = level1(id);
		StringBuilder id_level2 = level2(id_level1);
		StringBuilder id_level3 = level3(id_level2);
		StringBuilder id_level4 = level4(id_level3);
		StringBuilder id_level5 = level5(id_level4);
		StringBuilder id_level6 = level6(id_level5);
		StringBuilder id_level7 = level7(id_level6);

		return id_level7.toString();
    }
    
    // 소문자 -> 대문자
	public static String[] level1(String[] id) {
		for (int i = 0; i < id.length; i++) {
			int cur = id[i].charAt(0);
			if (cur >= 65 && cur <= 90) {
				id[i] = String.valueOf((char) (cur + 32));
			}
		}
		return id;
	}

	// 숫자, 뺴기, 밑줄, 마침표 외 문자 제거
	public static StringBuilder level2(String[] id) {
		StringBuilder id_level2 = new StringBuilder();
		for (int i = 0; i < id.length; i++) {
			int cur = id[i].charAt(0);
			if ((cur >= 97 && cur <= 122) || (cur >= 48 && cur <= 57) || cur == 45 || cur == 95 || cur == 46) {
				id_level2.append(id[i]);
			}
		}

		return id_level2;
	}

	public static StringBuilder level3(StringBuilder id) {
		StringBuilder id_level3 = new StringBuilder(String.valueOf(id.charAt(0)));
		for (int i = 1; i < id.length(); i++) {
			int cur = id.charAt(i);
			if (id.charAt(i - 1) == cur && cur == 46) {
			} else {
				id_level3.append(id.charAt(i));
			}
		}

		return id_level3;
	}

	public static StringBuilder level4(StringBuilder id) {
		if (id.length() != 0 && id.charAt(0) == '.') {
			id.delete(0, 1);
		}

		if (id.length() != 0 && id.charAt(id.length() - 1) == '.') {
			id.delete(id.length() - 1, id.length());
		}

		return id;
	}

	public static StringBuilder level5(StringBuilder id) {
		if (id.length() == 0) {
			id.append("a");
		}

		return id;
	}

	public static StringBuilder level6(StringBuilder id) {
		if (id.length() >= 16) {
			id.delete(15, id.length());
		}

		if (id.charAt(id.length() - 1) == '.') {
			id.delete(id.length() - 1, id.length());
		}

		return id;
	}

	public static StringBuilder level7(StringBuilder id) {
		String last = String.valueOf(id.charAt(id.length() - 1));
		while (id.length() <= 2) {
			id.append(last);
		}

		return id;
	}
}