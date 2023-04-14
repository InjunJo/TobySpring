import java.lang.reflect.Array;
import java.util.*;

public class Coding4 {

    public static void main(String[] args) {

                    /*ingredient	result
                [2, 1, 3, 2, 1, 2, 1, 3, 1, 2,1,2,2,3 1, 2, 3, 1]	1
                [2, 1, 1, 2, 3, 1, 3, 2, 1, 2, 1, 3, 1, 2,1,2,2,3 1, 2, 3, 1]

                [1, 3, 2, 1, 2, 1, 3, 1, 2]	0*/
        // 1 - 빵   2 - 야채   3- 고기

        //빵, 빵, 야채, 고기, 빵, 빵,야채,고기

        //-----
        // 빵 1
        // 고기 3
        // 야채 2
        // 빵 1
        //----

        int[] ingredient = {1, 3, 2, 1, 2, 1, 3, 1, 2};

                        //  0  1  2  3  4  5  6  7  8    ingredient.length = 9;
        int result = 2;



        List<Integer> tmp = new ArrayList();

        boolean end = false;
        int startIndex = 0;
        int endIndex = 0;

        int answer = 0;

        while(true){
            boolean possible = false;
            boolean success = false;

            startIndex = startIndex;
            endIndex = endIndex;
            int combo = 0;

            System.out.println("start : "+startIndex);
            System.out.println("end : "+endIndex);

            for(int i=startIndex; i<ingredient.length; i++){

                if(i == ingredient.length-1){
                    break;
                }

                if(startIndex+3 >ingredient.length-1){
                    break;
                }


                if(combo == 2){
                    if(ingredient[i+1] ==1 ){
                        success = true;
                        endIndex = i+1;
                        startIndex = endIndex-3;
                        System.out.println("combo에서 starIndex : "+startIndex+" endIndext : "+endIndex);

                        break;
                    }
                }


                if( ingredient[i] +1 == ingredient[i+1]){
                    possible = true;
                    ++combo;
                }else possible = false;
            }

            if(success == true&& startIndex!=0){

                ++answer;

                for(int i=startIndex; i<=endIndex; i++){
                    ingredient[i] = 0;
                }

                System.out.println("success 작업 마치고 "+answer+"회"+Arrays.toString(ingredient));
                System.out.println("success 작업 마치고 "+startIndex+"  "+endIndex);

                startIndex = endIndex+1;
                endIndex = startIndex+3;


            }else if(success==false && startIndex != 0) {

                int count = 0;

                for(int i=0; i<ingredient.length;i++){

                    if(ingredient[i] !=0){
                        ++count;
                        tmp.add(ingredient[i]);
                    }
                }
                ingredient = new int[count];

                for(int i=0; i<count; i++){

                    ingredient[i] = tmp.get(i);
                }

                startIndex = 0;
                endIndex = 0;

                System.out.println("success false라 작업 : "+Arrays.toString(ingredient));

            }else break;

        }


        System.out.println(answer);


    }
}
