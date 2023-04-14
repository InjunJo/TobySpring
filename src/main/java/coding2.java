import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class coding2 {

    public static void main(String[] args) {

        String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] completion = {"josipa", "filipa", "marina", "nikola"};

        Arrays.sort(participant);
        Arrays.sort(completion);

        String[] newCompletion = new String[completion.length + 1];


    }

    public static String solution(String[] participant, String[] completion) {
        String answer = "";

        String[] newCompletion = new String[completion.length + 1];

        Arrays.sort(completion);

        for (int i = 0; i < newCompletion.length; i++) {

            if (i == newCompletion.length - 1) {
                newCompletion[i] = "*";
                break;
            }

            newCompletion[i] = completion[i];

        }

        completion = newCompletion;

        Arrays.sort(participant);

        for (int i = 0; i < participant.length; i++) {

            if (completion[i].equals("*")) {

                return answer = participant[i];

            } else if (!participant[i].equals(completion[i])) {
                answer = participant[i];
                System.out.println("answer : " + answer);

                return answer = participant[i];

            }
        }
        return "답없음";
    }

    public static String[] sortStrArr(String[] strArr) {

        String[] result = new String[strArr.length];

        for (int i = 1; i < strArr.length; i++) {

            for (int j = 0; j < strArr.length - 1; j++) {

                if (strArr[j].compareTo(strArr[j + 1]) > 0) {
                    String tmp = strArr[j];
                    strArr[j] = strArr[j + 1];
                    strArr[j + 1] = tmp;

                }

            }
        }
        result = strArr;
        return result;

    }

    private static void l_pivot_sort(String[] a, int lo, int hi) {

        /*
         *  lo가 hi보다 크거나 같다면 정렬 할 원소가
         *  1개 이하이므로 정렬하지 않고 return한다.
         */
        if(lo >= hi) {
            return;
        }

        /*
         * 피벗을 기준으로 요소들이 왼쪽과 오른쪽으로 약하게 정렬 된 상태로
         * 만들어 준 뒤, 최종적으로 pivot의 위치를 얻는다.
         *
         * 그리고나서 해당 피벗을 기준으로 왼쪽 부분리스트와 오른쪽 부분리스트로 나누어
         * 분할 정복을 해준다.
         *
         * [과정]
         *
         * Partitioning:
         *
         *   a[left]          left part              right part
         * +---------------------------------------------------------+
         * |  pivot  |    element <= pivot    |    element > pivot   |
         * +---------------------------------------------------------+
         *
         *
         *  result After Partitioning:
         *
         *         left part          a[lo]          right part
         * +---------------------------------------------------------+
         * |   element <= pivot    |  pivot  |    element > pivot    |
         * +---------------------------------------------------------+
         *
         *
         *  result : pivot = lo
         *
         *
         *  Recursion:
         *
         * l_pivot_sort(a, lo, pivot - 1)     l_pivot_sort(a, pivot + 1, hi)
         *
         *         left part                           right part
         * +-----------------------+             +-----------------------+
         * |   element <= pivot    |    pivot    |    element > pivot    |
         * +-----------------------+             +-----------------------+
         * lo                pivot - 1        pivot + 1                 hi
         *
         */
        int pivot = partition(a, lo, hi);

        l_pivot_sort(a, lo, pivot - 1);
        l_pivot_sort(a, pivot + 1, hi);
    }



    /**
     * pivot을 기준으로 파티션을 나누기 위한 약한 정렬 메소드
     *
     * @param a		정렬 할 배열
     * @param left	현재 배열의 가장 왼쪽 부분
     * @param right	현재 배열의 가장 오른쪽 부분
     * @return		최종적으로 위치한 피벗의 위치(lo)를 반환
     */
    private static int partition(String[] a, int left, int right) {

        int lo = left;
        int hi = right;
        String pivot = a[left];		// 부분리스트의 왼쪽 요소를 피벗으로 설정

        // lo가 hi보다 작을 때 까지만 반복한다.
        while(lo < hi) {

            /*
             * hi가 lo보다 크면서, hi의 요소가 pivot보다 작거나 같은 원소를
             * 찾을 떄 까지 hi를 감소시킨다.
             */
            while(a[hi].compareTo(pivot) >0 && lo < hi) {
                hi--;
            }

            /*
             * hi가 lo보다 크면서, lo의 요소가 pivot보다 큰 원소를
             * 찾을 떄 까지 lo를 증가시킨다.
             */
            while(a[lo].compareTo(pivot) <=0 && lo < hi) {
                lo++;
            }

            // 교환 될 두 요소를 찾았으면 두 요소를 바꾼다.
            swap(a, lo, hi);
        }


        /*
         *  마지막으로 맨 처음 pivot으로 설정했던 위치(a[left])의 원소와
         *  lo가 가리키는 원소를 바꾼다.
         */
        swap(a, left, lo);

        // 두 요소가 교환되었다면 피벗이었던 요소는 lo에 위치하므로 lo를 반환한다.
        return lo;
    }

    private static void swap(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


}
