package 哈希;

import java.util.HashMap;
import java.util.Map;

public class Top0383_simple {

    public static void main(String[] args) {
        String ransomNote = "baa";
        String magazine = "aab";
        boolean ans = canConstruct(ransomNote, magazine);
        System.out.println(ans);
        System.out.println(canConstruct2(ransomNote, magazine));
    }

    /**
     * hash法
     * @param ransomNote
     * @param magazine
     * @return
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character,Integer> record = new HashMap<>();
        char key;
        for (int i = 0; i < ransomNote.length(); i++) {
            key = ransomNote.charAt(i);
            record.compute(key, (k, v) -> v == null ? 1 : v + 1);
        }
        for (int i = 0; i < magazine.length(); i++) {
            key = magazine.charAt(i);
            if (record.containsKey(key)) {
                record.put(key, record.get(key) - 1);
                if (record.get(key) == 0) {
                    record.remove(key);
                }
            }
        }
        return record.isEmpty();
    }

    /**
     * hash法
     * @param ransomNote
     * @param magazine
     * @return
     */
    public static boolean canConstruct2(String ransomNote, String magazine) {
        int[] record = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            record[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            record[ransomNote.charAt(i) - 'a']--;
            if (record[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
