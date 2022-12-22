package p04_BubbleSortTest;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleTest {

    @Test
    public void testBubbleSort() {
        int[] numbers = {5, 8, 6, 7};
        int[] sortedNumbers = {5, 6, 7, 8};

        Bubble.sort(numbers);

        Assert.assertArrayEquals(numbers,sortedNumbers);
    }

}