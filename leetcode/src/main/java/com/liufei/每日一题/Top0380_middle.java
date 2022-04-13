package com.liufei.每日一题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 380. O(1) 时间插入、删除和获取随机元素 [ https://leetcode-cn.com/problems/insert-delete-getrandom-o1/ ]
 */
public class Top0380_middle {

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.remove(2));
        System.out.println(randomizedSet.insert(2));
        randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
        randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
        randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
        randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
    }


    static class RandomizedSet {

        List<Integer> list;
        Map<Integer, Integer> map;
        Random random;

        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            // 这里删除的时候，将最后一个元素和要删的元素互换，然后删除最后一个元素
            int index = map.get(val);
            int last = list.get(list.size() - 1);
            list.set(index, last);
            list.remove(list.size() - 1);
            map.put(last, index);
            map.remove(val);
            return true;
        }

        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }

    static class RandomizedSet2 {


        Set<Integer> set;
        Random random;

        public RandomizedSet2() {
            set = new HashSet<>();
            random = new Random();
        }

        public boolean insert(int val) {
            return set.add(val);
        }

        public boolean remove(int val) {
            return set.remove(val);
        }

        public int getRandom() {
            Object[] objects = set.toArray();
            return (int) objects[random.nextInt(set.size())];
        }
    }
}
