import java.util.ArrayList;
import java.util.List;

public class PrimeFactorizer {

    // Method to calculate prime factors
    public static List<Integer> getPrimeFactors(int number) {
        List<Integer> factors = new ArrayList<>();

        if (number <= 1) {
            return null;
        }

        // Divide by 2 until the number is odd
        while (number % 2 == 0) {
            factors.add(2);
            number /= 2;
        }

        // Check for odd factors from 3 onwards
        for (int i = 3; i * i <= number; i += 2) {
            while (number % i == 0) {
                factors.add(i);
                number /= i;
            }
        }

        // If remaining number is greater than 2, it is a prime number
        if (number > 2) {
            factors.add(number);
        }

        return factors;
    }

    public static void main(String[] args) {
        // Example tests
        System.out.println(getPrimeFactors(56)); // Output: [2, 2, 2, 7]
        System.out.println(getPrimeFactors(97)); // Output: [97]
        System.out.println(getPrimeFactors(1));  // Output: null
        System.out.println(getPrimeFactors(100)); // Output: [2, 2, 5, 5]
    }
}
