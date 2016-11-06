package numbers;

import java.util.stream.IntStream;

public class ThreesAndFives {
    
    public static int countNumbers(int upTo) {
        int result = 0;
        for (int i = 1; i <= upTo; i++) {
            if ((i % 3 == 0) || (i % 5 == 0)) 
                result++;
        }
        return result;
    }
    
    public static long countNumbersJ8(int upTo) {
        return IntStream.rangeClosed(1, upTo).filter(i -> ((i % 3 == 0) || (i % 5 == 0))).count();
    }
    
    public static void main(String[] args) {
        int upTo = 100;
        boolean result =  ThreesAndFives.countNumbers(upTo) == ThreesAndFives.countNumbersJ8(upTo);
        System.out.println(result);
    }
}
