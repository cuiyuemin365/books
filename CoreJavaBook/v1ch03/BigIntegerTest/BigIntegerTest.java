import java.math.*;
import java.util.*;

/**
 * This program uses big numbers to compute the odds of winning the grand prize in a lottery.
 *
 * @author Cay Horstmann
 * @version 1.20 2004-02-10
 */
public class BigIntegerTest {

    public static void test1(){
        BigInteger n1 = new BigInteger("121232145643743214567432456743245643256432565");
        BigInteger n2 = new BigInteger("-1212435673214567324567432563234563256323456325");
        System.out.println(n1);
    }

    public static void main(String[] args) {
        test1();
        Scanner in = new Scanner(System.in);

        System.out.print("How many numbers do you need to draw? ");
        int k = in.nextInt();

        System.out.print("What is the highest number you can draw? ");
        int n = in.nextInt();

      /*
       * compute binomial coefficient n*(n-1)*(n-2)*...*(n-k+1)/(1*2*3*...*k)
       */

        BigInteger lotteryOdds = BigInteger.valueOf(1);

        for (int i = 1; i <= k; i++)
            lotteryOdds = lotteryOdds.multiply(BigInteger.valueOf(n - i + 1)).divide(
                    BigInteger.valueOf(i));

        System.out.println("Your odds are 1 in " + lotteryOdds + ". Good luck!");
    }
}
