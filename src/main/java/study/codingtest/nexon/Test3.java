package study.codingtest.nexon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by kh.jin on 2020. 3. 28.
 */
public class Test3 {

    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strTotal = br.readLine();
        String strShareRates = br.readLine();

        final double total_amount = Double.valueOf(strTotal);
        final double[] share = Arrays.stream(strShareRates.split(" ")).mapToDouble(Double::valueOf).toArray();
        final double share_sum = Arrays.stream(share).reduce((d1, d2) -> d1 + d2).orElse(.0);

        for (double a : share) {
            System.out.println("a : " + a);
            System.out.println("total : " + total_amount * (a / share_sum));
            System.out.println("* 10  : " + total_amount * (a / share_sum) * 10);
            System.out.println("round : " + Math.round(total_amount * (a / share_sum) * 10));
            System.out.println("/ 10  : " + (Math.round(total_amount * (a / share_sum) * 10) / 10.0));
        }

        String result = Arrays.stream(share)
                .map(s -> ((Math.round(total_amount * (s / share_sum) * 10))) / 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}
