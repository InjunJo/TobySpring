public class InsertionSort {


    public static void main(String[] args) {

        int[] arr = {35,38,1,45,44,89,24,77,54,2,4};

        int temp;

        for(int i=0; i<arr.length; i++){
            for(int j=i; j>0; j--){
                if(arr[j-1]<arr[j]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }

        }

        for(int i=0; i<arr.length; i++){

            System.out.println(arr[i]);
        }


    }

}
