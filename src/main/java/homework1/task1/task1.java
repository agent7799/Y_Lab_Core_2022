package homework1.task1;

public class task1 {

    public static void main(String[] args) {
        int arrayDimension = 5;
        int[][] testArray = new int[][]{
                {50, 77, 24},
                {47, 45, 36},
                {75, 65, 50}};

        int[][] array = getRandomIntArray(arrayDimension);

        System.out.println("Random two-dimension array: ");

        printArray(array);

        System.out.println("Maximal value: " + getMax(array));
        System.out.println("Minimal value: " + getMin(array));
        System.out.println("Average value: " + getAverage(array));

        assert  getAverage(testArray) == 51.11 : "test 1: average incorrect, expected value: 52.11" ;
        assert getMin(array) >= 0;

    }

    public static void printArray(int[][] array){
        for (int[] i : array) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println("\r");
        }
        System.out.println("");
    }

    public static int[][] getRandomIntArray(int range) {
        long startTime = System.nanoTime();
        int[][] numbers = new int[range][range];
        for (int i = 0; i < range; i++) {
            for (int j = 0; j < range; j++) {
                long estimatedTime = (System.nanoTime() - startTime);
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                numbers[i][j] = (int) estimatedTime % 10000 / 100;
            }
        }
        return numbers;
    }

    public static double getAverage(int[][] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                sum += array[i][j];
            }
        }
        double result = sum / ((array[0].length) * (array.length));
        return Math.floor(result * 100) / 100;
    }

    public static int getMin(int[][] array) {
        int min = array[0][0];
        for (int[] i : array) {
            for (int j : i) {
                if (min > j) {
                    min = j;
                }
            }
        }
        return min;
    }

    public static int getMax(int[][] array) {
        int max = array[0][0];
        for (int[] i : array) {
            for (int j : i) {
                if (max < j) {
                    max = j;
                }
            }
        }
        return max;
    }

}
