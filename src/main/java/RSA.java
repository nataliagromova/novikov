import java.math.BigInteger;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by NATA on 15.09.2018.
 */
public class RSA {

    private static long e;
    private static long d;
    private static long n;

    public  RSA(long p, long q) {
        this.n = p * q;
        long f_n = (p - 1) * (q - 1);
        this.e = Calculate_e(f_n);
        this.d = Calculate_d(f_n, e);
    }

    public static long getE() {
        return e;
    }

    public static long getD() {
        return d;
    }

    public static long getN() {
        return n;
    }

    private long Calculate_e(long f_n) {
        for (int i = 3; i < f_n; i++) {
            if (LucTest(i) && f_n % i != 0) {
                return i;
            }
        }
        return f_n - 1;
    }

    private long Calculate_d(long f_n, long e) {
        BigInteger d = new BigInteger(String.valueOf(e));
        d = d.modInverse(BigInteger.valueOf(f_n));
        return d.longValue();
    }

    public static TreeSet<Long> PrimeNumbersOf(long n) {
        TreeSet<Long> factors = new TreeSet<>();
        for (int i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                factors.add(Long.valueOf(i));
                n /= i;
            }
        }
        if (n > 1) {
            factors.add(n);
        }
        return factors;

    }

    public static boolean LucasTest(long n) {
        TreeSet<Long> primeNumbers = PrimeNumbersOf(n - 1);
        label:
        for (int i = 0; i < 5; i++) {
            int A = (int) (Math.random() * (n - 2 - 2) + 2);
            BigInteger a = BigInteger.valueOf(A);
            if (!a.modPow(BigInteger.valueOf(n - 1), BigInteger.valueOf(n)).equals(BigInteger.ONE)) {
                return false;
            } else {
                for (Long q : primeNumbers) {
                    if (!a.modPow(BigInteger.valueOf((n - 1) / q), BigInteger.valueOf(n)).equals(BigInteger.ONE)) {
                        if (primeNumbers.last().equals(q))
                            return true;
                    } else continue label;
                }
            }
        }
        return true;
    }

    public static boolean LucTest(long n) {
        if (LucasTest(n))
            return true;
        else if (n == 1 || n == 2)
            return true;
        else return false;
    }
}
