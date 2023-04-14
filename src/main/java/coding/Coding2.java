package coding;

import java.util.*;
import java.util.stream.Collectors;


public class Coding2 {

    public static void main(String[] args) {

        /*         nums	result
                [1,2,3,4]	1
                [1,2,7,6,4]	4*/
        //[1,2,7,6,4,8,9,10,13,14,17,19]
        int[] nums = {1,2,7,6,4};

        //nums = {[1, 2, 4, 7, 10, 13, 14, 17, 19]} min 7 max 50  7(1+2+4),11(x) (1+2+7),13,17,19,23,29,31,37,41,43,47,


        nums = Arrays.stream(nums).sorted().toArray();

        System.out.println(Arrays.toString(nums));

        int min = nums[0]+nums[1]+nums[2];
        int max = nums[nums.length-1]+nums[nums.length-2]+nums[nums.length-3];

        System.out.println(min+ " : "+max);

        int count =0;

        int[] decimalArr = decimalArr(min,max);

        List<Integer> list = new ArrayList<>();

        System.out.println(Arrays.toString(decimalArr));

        for(int i=0; i<decimalArr.length; i++){

           int decimal = decimalArr[i];

           for(int j=0; j<nums.length; j++){



           }

        }

    }

    public static int[] decimalArr(int min,int max){

        List<Integer> list = new ArrayList<>();

        for(int i=min; i<=max; i++){

            if(decimal(i)){
                list.add(i);
            }

        }

        return list.stream().mapToInt(value -> value.intValue()).toArray();

    }


    public static int findDecimal(int min, int max,int[] nums){
        int count =0;

        List<Integer> list = new ArrayList<>();

        for(int i: nums){
            list.add(i);
        }

        for(int i=min; i<=max; i++){

            if(decimal(i)){
                ++count;
            }

        }

        return count;
    }


    public static boolean decimal(int num){

        boolean decimal = true;

        if(num ==2){
            return true;
        }

        for(int i=2; i<num/2; i++){

            if(num % i == 0){
                decimal = false;
            }
        }

        return decimal;
    }
}
