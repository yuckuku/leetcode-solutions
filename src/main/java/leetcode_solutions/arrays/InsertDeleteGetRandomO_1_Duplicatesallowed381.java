package leetcode_solutions.arrays;

import java.util.*;

public class InsertDeleteGetRandomO_1_Duplicatesallowed381 {
    class RandomizedCollection {

        private List<Integer> list;

        /**
         * Initialize your data structure here.
         */
        public RandomizedCollection() {
            list = new ArrayList<>();
        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (!list.contains(val)) {
                list.add(val);
                return true;
            } else {
                list.add(val);
                return false;
            }
        }

        /**
         * Removes a value from the collection. Returns true if the collection contained the specified element.
         */
        public boolean remove(int val) {
            if (list.contains(val)) {
                list.remove(Integer.valueOf(val));
                return true;
            } else {
                return false;
            }
        }

        /**
         * Get a random element from the collection.
         */
        public int getRandom() {
            Random random = new Random();
            int randomIndex = random.nextInt(list.size());
            return list.get(randomIndex);
        }
    }

    /**
     * Your RandomizedCollection object will be instantiated and called as such:
     * RandomizedCollection obj = new RandomizedCollection();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */

    class RandomizedCollection0 {
        private HashMap<Integer, HashSet<Integer>> index = new HashMap();
        private int tail = 0;
        private List<Integer> numbers = new ArrayList(100);

        /**
         * Initialize your data structure here.
         */
        public RandomizedCollection0() {

        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
         */
        public boolean insert(int val) {
            boolean res = true;
            if (index.containsKey(val)) {
                res = false;
            } else {
                index.put(val, new HashSet<Integer>());
            }
            index.get(val).add(tail);
            if (tail == numbers.size()) {
                numbers.add(val);
            } else {
                numbers.set(tail, val);
            }

            tail++;
            return res;
        }

        /**
         * Removes a value from the collection. Returns true if the collection contained the specified element.
         */
        public boolean remove(int val) {
            if (!index.containsKey(val)) {
                return false;
            } else {
                int i = index.get(val).iterator().next();
                index.get(val).remove(i);
                if (index.get(val).size() == 0) {
                    index.remove(val);
                }
                numbers.set(i, numbers.get(--tail));
                if (i != tail) {
                    index.get(numbers.get(i)).remove(tail);
                    index.get(numbers.get(i)).add(i);
                }
                return true;
            }
        }

        /**
         * Get a random element from the collection.
         */
        public int getRandom() {
            if (tail > 0) {
                return numbers.get((int) (Math.random() * tail));
            } else {
                return -1;
            }
        }
    }
}
