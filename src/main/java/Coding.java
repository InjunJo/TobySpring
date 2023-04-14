import java.util.Arrays;

public class Coding {

    public static void main(String[] args) {


        int[] answer = new int[2];

        int denum1 = 1;//분자
        int num1 = 2; //분모

        int denum2 = 3; //분자
        int num2 = 4; //분모

        int denum = 0; //분자
        int num = 0; //분모

        //최소공배수는 4;
        // 9/1  5/2

          //최소공배수

        int min = num1;
        int max = num2;

        if(num1>num2){
            min = num2;
            max = num1;
        }
        int x = max%min;
        int lcm = 0;

        if(x ==0){
            lcm = max;
        }else lcm = (num1*num2)/x;

        /*if(num2%num1 == 0 || num1%num2 ==0){
            lcm = lcm/min;
        }*/



        denum = denum1 *(lcm/num1)+denum2*(lcm/num2);  //분자
        num = lcm; //분모

        int remain = num % denum; // 240 % 9 = 6
        int denumRemain = denum% remain;
        int numRemain = denum%num;
        int tmp = 1;

        if(denum < num){

            if(remain ==0){
                denum = denum/ denum;
                num = num /denum;
            }else{

                while(denumRemain !=0){
                    tmp = denumRemain;
                    denumRemain = denum%denumRemain;

                }
                answer[0] = denum/tmp;
                answer[1] = num/tmp;
            }

        }else if(denum == num){
            denum = 1;
            num =1;
        }else{

        }

        answer[0] = denum;
        answer[1] = num;



        System.out.println(Arrays.toString(answer));

    }
}
