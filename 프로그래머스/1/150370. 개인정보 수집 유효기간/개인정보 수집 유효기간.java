import java.util.*;
class Solution {

    // 약관에 따라 개인정보 수집 일자가 여러개 일 수 있다. -> Map으로 약관 종류에 따른 유효 기간을 저장
	// 계약 유효 기간은 월 기준 임으로
	// year = 수집 일자(연) + (수집 일자 (월) + 유효 기간) / 12
	// month = (수집 일자 (월) + 유효 기간) % 12

	// 보관 가능한 날짜는 유효기간 -1 day

	// 1. year 비교
	// 1-1. 오늘 날짜보다 year가 더 작다면 파기대상
	// 1-2. 같다면 월 확인
	// 1.3. 크다면 보관 가능

	// 2. month 비교
	// 2-1. 오늘 날짜보다 더 작다면 파기 대상
	// 2-2. 같다면 day 비교
	// 2-3. 크다면 보관 가능

	// 3. day 비교
	// 3-1. 오늘 날짜보다 더 작다면 파기 대상
	// 3-2. 같다면 보관 가능
	// 3-3 크다면 보관 가능
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
		String[] arr = today.split("\\.");
		int year = Integer.parseInt(arr[0]);
		int month = Integer.parseInt(arr[1]);
		int day = Integer.parseInt(arr[2]);

		List<Integer> result = new LinkedList<>();

		Map<String, Integer> termsData = new HashMap<>();
		for (int i = 0; i < terms.length; i++) {
			String[] cur = terms[i].split(" ");
			termsData.put(cur[0], Integer.parseInt(cur[1]));
		}

		for (int i = 0; i < privacies.length; i++) {
			String[] cur = privacies[i].split(" ");
			String[] dayData = cur[0].split("\\.");
			int y = Integer.parseInt(dayData[0]);
			int m = Integer.parseInt(dayData[1]);
			int d = Integer.parseInt(dayData[2]);

			if ((m + termsData.get(cur[1])) % 12 == 0) {
				y += (m + termsData.get(cur[1])) / 12 - 1;
				m = 12;
			} else {
				y += (m + termsData.get(cur[1])) / 12;
				m = (m + termsData.get(cur[1])) % 12;
			}

			if (d == 1) {
				d = 28;
				if (m == 1) {
					m = 12;
					y--;
				} else {
					m--;
				}
			} else {
				d--;
			}

//			System.out.println("y = " + y);
//			System.out.println("m = " + m);
//			System.out.println("d = " + d);

			if (y < year) {
				result.add(i + 1);
				continue;
			} else if (y > year) {
				continue;
			}

			if (m < month) {
				result.add(i + 1);
				continue;
			} else if (m > month) {
				continue;
			}

			if (d < day) {
				result.add(i + 1);
			}
		}


		return result.stream().mapToInt(Integer::intValue).toArray();
    }
}