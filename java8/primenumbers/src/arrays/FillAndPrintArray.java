package arrays;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FillAndPrintArray {

    public static void main(String[] args) {
        String[] array = 
                IntStream.range(0, 3).mapToObj(x -> "Text" + x).toArray(String[]::new);
        Arrays.stream(array).forEach(x -> System.out.println(x));
    }

}
