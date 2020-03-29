package study.codingtest.nexon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by kh.jin on 2020. 3. 28.
 */
public class Test2 {

    public static void main (String[] args) throws java.lang.Exception
    {
        List<String> wordList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(String input = br.readLine(); !input.equals("====="); input = br.readLine()) {
            wordList.add(input);
        }
        wordList.sort(null);
        Map<String, Integer> result = new HashMap<>();
        for (String s : wordList) {
            for (int i = 1; i <= s.length(); i++) {
                String key = (String) s.subSequence(0, i);
                System.out.println("s: "+ s + " key :" + key);
                if (result.containsKey(key)) {
                    result.put(key, (result.get(key))+1);
                } else {
                    result.put(key, 1);
                }
            }
        }

        for (String key : result.keySet()) {
            System.out.println(key + " " + result.get(key));
        }
    }
}
