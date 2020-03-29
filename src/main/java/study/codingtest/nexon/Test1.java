package study.codingtest.nexon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by kh.jin on 2020. 3. 28.
 */
public class Test1 {

    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int k = Integer.parseInt(br.readLine());

        String[] arr = input.split(" ");
        int len = arr.length;

        int[] src = new int[len];
        int tmp = 0;

        for (int i = 0; i < len; i++) {
            src[i] = Integer.parseInt(arr[i]);
        }

        for (int i = 0; i < k; i++) {
            tmp = src[len-1];
            for (int j = len-1; j > 0; j--) {
                src[j] = src[j-1];
            }
            src[0] = tmp;
        }

        String output = "";
        for (int i = 0; i <len; i++) {
            output = output + src[i] + " ";
        }


        System.out.println(output.trim());
    }
}
