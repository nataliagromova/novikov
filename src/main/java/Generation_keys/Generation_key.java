package Generation_keys;


import java.math.BigInteger;
import java.util.Random;

public class Generation_key {
    private static long e;
    private static long d;
    private static long n;

    public Generation_key(long n) {
        this.n = n;
        this.e = Calculate_e(n-1);
        this.d = Calculate_d(n-1, e);
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

    private long Calculate_e(long n) {
        Random r=new Random();
        e=r.nextInt(256);
        while (true){
            if(BigInteger.valueOf(n).gcd(BigInteger.valueOf(e)).equals(BigInteger.ONE)){
                return e;
            }
            e++;
        }
    }
    private long Calculate_d(long n,long e) {
        BigInteger d = new BigInteger(String.valueOf(e));
        d = d.modInverse(BigInteger.valueOf(n));
        return d.longValue();
    }

    static long RandPrime() {
        final int min = 0;
        final int max = 256;
        BigInteger rnd = new BigInteger(String.valueOf(Rnd(min, max)));
        rnd = rnd.nextProbablePrime();
        if (!rnd.isProbablePrime(50))
            rnd = rnd.nextProbablePrime();
        return rnd.longValue();
    }

    private static int Rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}




