package com.liufei.每日一题;

import java.util.HashSet;
import java.util.Set;

public class Top0804_simple {

    public static void main(String[] args) {
        String[] works = {"gin", "zen", "gig", "msg"};
        int i = uniqueMorseRepresentations(works);
        System.out.println(i);
    }


    public static int uniqueMorseRepresentations(String[] words) {
        if (words.length <= 1) {
            return words.length;
        }
        String[] strs = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> sets = new HashSet<>();
        for (String word : words) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                stringBuilder.append(strs[word.charAt(i) - 'a']);
            }
            sets.add(stringBuilder.toString());
        }
        System.out.println(sets);
        return sets.size();
    }
}
