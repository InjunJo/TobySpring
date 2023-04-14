import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Coding3 {

    public static void main(String[] args) {

        String answer = "";
        String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] completion = {"josipa", "filipa", "marina", "nikola"};

        Map<String,Integer> map = new HashMap<>();

        Arrays.sort(participant);

    }

    public static String[] sortStrArr(String[] strArr){

        String[] result = new String[strArr.length];

        for(int i = 1; i< strArr.length; i++){

            for(int j = 0; j< strArr.length-1; j++){

                if(strArr[j].compareTo(strArr[j+1]) >0){
                    String tmp = strArr[j];
                    strArr[j] = strArr[j+1];
                    strArr[j+1] = tmp;

                }

            }
        }
        result = strArr;
        return result;

    }


}
