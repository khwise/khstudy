package study.codingtest.nexon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by kh.jin on 2020. 3. 28.
 */
public class Test5 {

    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] arr = input.split(" ");
        int len = arr.length;
        int[] b;
        int[] c;
        int d = 0;
        int result = 0;

        Stack<int[]> stack = new Stack<>();
        b = new int[len];
        for (int i = 0; i < len; i++) {
            b[i] = Integer.parseInt(arr[i]);
        }
        stack.push(new int[]{-1, -1});
        result = 0;
        int tmp;
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && stack.peek()[0] >= b[i]) {
                stack.pop();
            }
            tmp = b[i] * (i - stack.peek()[1]);
            int j = i + 1;
            while (j < len && b[j] >= b[i]) {
                j++;
                tmp += b[i];
            }
            if (tmp > result) result = tmp;
            stack.push(new int[]{b[i], i});
        }

        System.out.println(result);
    }
}
