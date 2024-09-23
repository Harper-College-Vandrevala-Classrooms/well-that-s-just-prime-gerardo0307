import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Factorizer {

    
    public ArrayList<Integer> primeFactors(int n) {
        if (n <= 1) {
            return null;
        }
        
        ArrayList<Integer> factors = new ArrayList<>();
        int divisor = 2;

        while (n > 1) {
            while (n % divisor == 0) {
                factors.add(divisor);
                n /= divisor;
            }
            divisor++;
        }
        
        return factors;
    }

    
    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        return primeFactors(n).size() == 1;
    }

    
    public boolean isComposite(int n) {
        return n > 1 && !isPrime(n);
    }

    
    public String reduce(int numerator, int denominator) {
        if (denominator == 0) {
            return "undefined";
        }
        if (numerator == 0) {
            return "0";
        }

        ArrayList<Integer> numFactors = primeFactors(numerator);
        ArrayList<Integer> denomFactors = primeFactors(denominator);

        
        Map<Integer, Integer> numCount = countFactors(numFactors);
        Map<Integer, Integer> denomCount = countFactors(denomFactors);

        
        for (Integer prime : new ArrayList<>(numCount.keySet())) {
            if (denomCount.containsKey(prime)) {
                int minCount = Math.min(numCount.get(prime), denomCount.get(prime));
                numCount.put(prime, numCount.get(prime) - minCount);
                denomCount.put(prime, denomCount.get(prime) - minCount);

                if (numCount.get(prime) == 0) {
                    numCount.remove(prime);
                }
                if (denomCount.get(prime) == 0) {
                    denomCount.remove(prime);
                }
            }
        }

        int reducedNumerator = 1;
        int reducedDenominator = 1;

        for (Map.Entry<Integer, Integer> entry : numCount.entrySet()) {
            reducedNumerator *= Math.pow(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<Integer, Integer> entry : denomCount.entrySet()) {
            reducedDenominator *= Math.pow(entry.getKey(), entry.getValue());
        }

        // Format the output
        if (reducedDenominator == 1) {
            return String.valueOf(reducedNumerator);
        } else {
            return reducedNumerator + "/" + reducedDenominator;
        }
    }

   
    private Map<Integer, Integer> countFactors(ArrayList<Integer> factors) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int factor : factors) {
            countMap.put(factor, countMap.getOrDefault(factor, 0) + 1);
        }
        return countMap;
    }

    
    public static void main(String[] args) {
        Factorizer factorizer = new Factorizer();

       
        System.out.println(factorizer.primeFactors(100)); // Output: [2, 2, 5, 5]
        System.out.println(factorizer.primeFactors(35));  // Output: [5, 7]
        System.out.println(factorizer.primeFactors(24));  // Output: [2, 2, 2, 3]
        System.out.println(factorizer.primeFactors(17));  // Output: [17]
        System.out.println(factorizer.primeFactors(1));   // Output: null

        
        System.out.println(factorizer.isPrime(17)); // Output: true
        System.out.println(factorizer.isPrime(18)); // Output: false
        System.out.println(factorizer.isPrime(1));  // Output: false

        
        System.out.println(factorizer.isComposite(36)); // Output: true
        System.out.println(factorizer.isComposite(17)); // Output: false
        System.out.println(factorizer.isComposite(1));  // Output: false

        
        System.out.println(factorizer.reduce(12, 26));  // Output: 6/13
        System.out.println(factorizer.reduce(36, 12));  // Output: 3
        System.out.println(factorizer.reduce(12, 12));  // Output: 1
        System.out.println(factorizer.reduce(12, 37));  // Output: 12/37
    }
}
