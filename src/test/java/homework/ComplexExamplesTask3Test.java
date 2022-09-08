package homework;

import org.junit.jupiter.api.Test;

import static homework.ComplexExamples.fuzzySearch;
import static org.junit.jupiter.api.Assertions.*;

class ComplexExamplesTask3Test {

    @Test
    void fuzzySearchTest1() {
        assertEquals(fuzzySearch("car", "ca6$$#_rtwheel"), true); // true
    }

    @Test
    void fuzzySearchTest2() {
        assertEquals(fuzzySearch("cwhl", "cartwheel"), true); // true
    }

    @Test
    void fuzzySearchTest3() {
        assertEquals(fuzzySearch("cwhee", "cartwheel"),true); // true
    }

    @Test
    void fuzzySearchTest4() {
        assertEquals(fuzzySearch("cartwheel", "cartwheel"),true); // true
    }

    @Test
    void fuzzySearchTest5() {
        assertEquals(fuzzySearch("cwheeel", "cartwheel"), false); // false
    }

    @Test
    void fuzzySearchTest6() {
            assertEquals(fuzzySearch("lw", "cartwheel"),false); // false
        }


}