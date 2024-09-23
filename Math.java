import java.util.ArrayList;
import java.util.List;

public class Math {


    private static List<Integer> getPrimeFactors(int number) {
        List<Integer> factors = new ArrayList<>();
        
        if (number <= 1) {
            return factors;
        }

        while (number % 2 == 0) {
            factors.add(2);
            number /= 2;
        }

        for (int i = 3; i * i <= number; i += 2) {
            while (number % i == 0) {
                factors.add(i);
                number /= i;
            }
        }

        if (number > 2) {
            factors.add(number);
        }

        return factors;
    }

    private static int gcd(int a, int b) {
        List<Integer> factorsA = getPrimeFactors(a);
        List<Integer> factorsB = getPrimeFactors(b);
        
        if (factorsA.isEmpty() || factorsB.isEmpty()) {
            return 1;
        }

        int gcd = 1;
        int indexA = 0, indexB = 0;

        while (indexA < factorsA.size() && indexB < factorsB.size()) {
            if (factorsA.get(indexA).equals(factorsB.get(indexB))) {
                gcd *= factorsA.get(indexA);
                indexA++;
                indexB++;
            } else if (factorsA.get(indexA) < factorsB.get(indexB)) {
                indexA++;
            } else {
                indexB++;
            }
        }

        return gcd;
    }

    public static boolean isPrime(int number) {
        return getPrimeFactors(number).size() == 1 && getPrimeFactors(number).get(0) == number;
    }


    public static String reduce(int numerator, int denominator) {
        if (denominator == 0) {
            return "Undefined (denominator cannot be zero)";
        }
        
        if (numerator == 0) {
            return "0";
        }

        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        int reducedNumerator = numerator / gcd;
        int reducedDenominator = denominator / gcd;

        if (reducedDenominator < 0) {
            reducedNumerator = -reducedNumerator;
            reducedDenominator = -reducedDenominator;
        }

        return reducedNumerator + "/" + reducedDenominator;
    }

    public static void main(String[] args) {
        System.out.println(isPrime(2));  
        System.out.println(isPrime(4));  
        System.out.println(isPrime(17)); 

    
        System.out.println(reduce(12, 26)); 
        System.out.println(reduce(100, 250)); 
        System.out.println(reduce(7, 1)); 
        System.out.println(reduce(0, 5)); 
        System.out.println(reduce(5, 0)); 
    }
}