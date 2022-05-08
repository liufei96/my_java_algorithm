package 每日一题;

public class Top0744_simple {

    public static void main(String[] args) {
        Top0744_simple top0744_simple = new Top0744_simple();
        char[] letters = { 'c', 'f', 'j'};
        char target = 'j';
        char c = top0744_simple.nextGreatestLetter2(letters,target);
        System.out.println(c);
    }

    /**
     * 线性查找
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {
        for (char letter : letters) {
            if (target < letter) {
                return letter;
            }
        }
        return letters[0];
    }

    /**
     * 二分查找
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter2(char[] letters, char target) {
       int left = 0, right = letters.length - 1;
       while (left <= right) {
           int mid = left + (right - left) / 2;
           if (letters[mid] > target) {
               right = mid - 1;
           } else {
               left = mid + 1;
           }
       }
       return letters[left % letters.length];
    }

    /**
     * 二分查找
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter3(char[] letters, char target) {
        int left = 0, right = letters.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return letters[left % letters.length];
    }
}
