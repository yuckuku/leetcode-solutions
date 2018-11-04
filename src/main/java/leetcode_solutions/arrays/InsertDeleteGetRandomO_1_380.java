package leetcode_solutions.arrays;


import org.junit.Test;

import java.util.*;

public class InsertDeleteGetRandomO_1_380 {

    //my solution:use one list, about 600ms
    class RandomizedSet {

        private List<Integer> list;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            list = new ArrayList<>();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (!list.contains(val)) {
                list.add(val);
                return true;
            } else {
                return false;
            }
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
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
         * Get a random element from the set.
         */
        public int getRandom() {
            Random random = new Random();
            int randomIndex = random.nextInt(list.size());
            System.out.println(randomIndex);
            return list.get(randomIndex);
        }
    }

    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */

    //use one map and one list; faster than my solution; about 100ms
    class RandomizedSet0 {
        Map<Integer, Integer> map;
        List<Integer> list;
        int size = 0;


        /** Initialize your data structure here. */
        public RandomizedSet0() {
            map =  new HashMap<Integer, Integer>();
            list = new ArrayList<Integer>();
            this.size = 0;

        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(map.containsKey(val)){
                return false;
            }else{
                list.add(size,val);
                map.put(val, size++);
                return true;
            }
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(!map.containsKey(val)){
                return false;
            }else if(size == 0){
                map.remove(val);

            }else{
                int tailKey = list.get(size-1);
                map.put(tailKey,map.get(val));
                list.set(map.get(val),tailKey);
                size--;
                map.remove(val);

            }
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            Random rd = new Random();
            return list.get(rd.nextInt(size));
        }
    }

    @Test
    public void test() {
        Random random = new Random(2);
        System.out.println(random.nextInt(2));
    }
}
