package coding;


import java.util.*;
import java.util.stream.Stream;

public class Coding {

    public static void main(String[] args) {

        /*  answers	    return
          [1,2,3,4,5]	[1]
           [1,3,2,4,2]	[1,2,3]*/

        int[] answers = {1,2,3,4,5,2,1};

        int[] answer = {};


        int[] student1 =  {1, 2, 3, 4, 5};
        int[] student2 =  {2, 1, 2, 3, 2, 4, 2, 5};
        int[] student3 ={3, 3, 1, 1, 2, 2, 4, 4, 5, 5};


        student1 = getStudentAnswer(answers,student1);
        student2 = getStudentAnswer(answers,student2);
        student3 = getStudentAnswer(answers,student3);


        int answer1 = checkAnswer(student1,answers);
        int answer2 = checkAnswer(student2,answers);
        int answer3 = checkAnswer(student3,answers);

        int[] tmp = {answer1,answer2,answer3};

        System.out.println("answer1 : "+answer1);
        System.out.println("answer2 : "+answer2);
        System.out.println("answer3 : "+answer3);


        int[] index = {1,2,3};

        for(int i=0; i<3; i++){

            if(tmp[i] == 0){
                index[i] = 0;
            }
        }

        int max = tmp[0];
        int count = 0;

        for(int i=0; i<tmp.length; i++){

            if(tmp[i]>max){
                max = tmp[i];
                ++count;
            }
        }




        for(int i=1; i<tmp.length; i++){

            for(int j=i-1; j<tmp.length-1; j++){

                if(tmp[j] <tmp[j+1]){
                    int tmp1 = tmp[j];
                    tmp[j] = tmp[j+1];
                    tmp[j+1] = tmp1;

                    int indexTmp = index[j];
                    index[j] = index[j+1];
                    index[j+1] = indexTmp;
                }

            }

        }

        List<Integer> list = new ArrayList<>();


        System.out.println(max);

        for(int i=0; i<tmp.length; i++){

            if(tmp[i] >=max){
                list.add(index[i]);
            }

        }

        answer = list.stream().mapToInt(value -> value.intValue()).toArray();

        System.out.println(Arrays.toString(answer));




    }

    public static int[] getStudentAnswer(int[] answers,int[] pattern){

        List<Integer> list = new ArrayList<>();

        for(int i=0; i<answers.length; i++){
            list.add(pattern[i%pattern.length]);
        }


        return list.stream().mapToInt(value -> value.intValue()).toArray();

    }

    public static int checkAnswer(int[] student,int[] answers){
        int answerCount = 0;

        for(int i=0; i<answers.length; i++){

            if(student[i] == answers[i]){
                ++answerCount;
            }
        }

        return answerCount;
    }
}
