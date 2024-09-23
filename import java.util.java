import java.util.ArrayList;
import java.util.List;

public class PrimeFactorizer {


    public static List<Integer> getPrimeFactors(int number) {
        List<Integer> factors = new ArrayList<>();

        if (number <= 1) {
            return null;
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

    public static void main(String[] args) {

        System.out.println(getPrimeFactors(56));
        System.out.println(getPrimeFactors(97));
        System.out.println(getPrimeFactors(1)); 
        System.out.println(getPrimeFactors(100));
    }
}