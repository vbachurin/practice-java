package numbers;

import java.util.stream.IntStream;

public class PrimeNumbers {
    
    public long countPrimes(int upTo) {
        return IntStream.range(1, upTo).filter(this::isPrime).count();
    }

    private boolean isPrime(int max) {
        return IntStream.range(2, max).allMatch(x -> (max % x) != 0);
    }
    
    public static void main(String[] args) {
        System.out.println(new PrimeNumbers().countPrimes(3));
    }

}
