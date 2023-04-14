import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Coding5 {

    public static void main(String[] args) {
        int[] ingredient = {2, 1, 1, 2, 3, 1, 2, 3, 1};
                        //  0  1  2  3  4  5  6  7  8    ingredient.length = 9; lastIndex = 8
        int N =2;

        boolean possible = false;
        boolean success = false;
        int startIndex = 0;
        int endIndex = 0;
        int combo = 0;
        int count = 0;

        List<Integer> tmp = new ArrayList();

        while(true){
            startIndex = startIndex;
            endIndex = endIndex;
            System.out.println("starIndex : "+startIndex+" endIndex : "+endIndex);

            for(int i=0; i<ingredient.length; i++){

                if(endIndex >ingredient.length-1) {

                    clearZero(ingredient,startIndex,endIndex,tmp);
                    System.out.println(Arrays.toString(ingredient));
                    break;
                }
                else if( ingredient[i] +1 == ingredient[i+1]){
                    possible = true;
                    ++combo;
                }else possible = false;

                if(combo == 2){
                    if(ingredient[i+1] ==1 ){
                        count++;
                        success = true;
                        startIndex = i+1;
                        endIndex = startIndex+3;
                        System.out.println("combo2에서 starIndex : "+startIndex+" endIndex : "+endIndex);

                        break;
                    }
                }else break;
            }

            System.out.println(Arrays.toString(ingredient));

            /*if(success ==true){

                for(int i=startIndex; i<=endIndex; i++){
                    ingredient[i] = 0;
                }



                startIndex = endIndex+1;
                endIndex = startIndex+3;

                System.out.println("success 작업 마치고 "+Arrays.toString(ingredient));
                System.out.println("success 작업 마치고 "+startIndex+"  "+endIndex);

                System.out.println("----------------------------------");

                success = false;

            }*/



        }



        /*for(int i=startIndex; i<=endIndex; i++){
            ingredient[i] = 0;
        }

        System.out.println(count+"회 success 작업 마치고 "+Arrays.toString(ingredient));
        System.out.println("success 작업 마치고 "+startIndex+"  "+endIndex);

        startIndex = endIndex+1;
        endIndex = startIndex+3;

        System.out.println(Arrays.toString(ingredient));
        System.out.println("----------------------------------");*/

    }

    public static void makingZero(int[]ingredient,int startIndex,int endIndex){
        for(int i=startIndex; i<=endIndex; i++){
            ingredient[i] = 0;
        }

        System.out.println("success 작업 마치고 "+Arrays.toString(ingredient));
        System.out.println("success 작업 마치고 "+startIndex+"  "+endIndex);

        startIndex = endIndex+1;
        endIndex = startIndex+3;

        System.out.println(Arrays.toString(ingredient));
        System.out.println("----------------------------------");

    }

    public static void clearZero(int[]ingredient,int startIndex,int endIndex,List<Integer> tmp ){
        int count = 0;

        for(int i=0; i<ingredient.length;i++){

            if(ingredient[i] !=0){
                ++count;
                tmp.add(ingredient[i]);
            }
        }

        for(int j=0; j<ingredient.length;j++){

            if(ingredient[j] !=0){
                ++count;
                tmp.add(ingredient[j]);
            }
        }

        ingredient = tmp.stream().mapToInt(value -> value.intValue()).toArray();

        startIndex = 0;
        endIndex = 0;
    }
}
