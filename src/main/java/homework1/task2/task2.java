package homework1.task2;

import java.util.Arrays;

public class task2 {

    public static void main(String[] args) {

        int[] ints = {5,6,3,2,5,1,4,9};

        System.out.println("Unsorted array: " + Arrays.toString(ints));
        System.out.println("Sorted array: " + Arrays.toString(sortArrayBy(ints)));

    }

     static int[] sortArrayBy(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] < array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

}
