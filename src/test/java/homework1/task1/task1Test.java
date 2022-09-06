package homework1.task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class task1Test {

        int[][] testArray = new int[][]{
                {50, 77, 24},
                {47, 45, 36},
                {75, 65, 50}
        };

@Test
    void averageValueTest(){
    assertEquals(52.11, task1.getAverage(testArray));
}

@Test
    void getMinValueTest(){
    assertEquals(24, task1.getMin(testArray));
}

@Test
    void getMaxValueTest(){
    assertEquals(77, task1.getMax(testArray));
}


}
