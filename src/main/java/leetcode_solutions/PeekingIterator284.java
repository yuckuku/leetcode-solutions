package leetcode_solutions;

import java.util.Iterator;
import java.util.LinkedList;

public class PeekingIterator284 {
    // Java Iterator interface reference:
    // https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
    class PeekingIterator implements Iterator<Integer> {

        private LinkedList<Integer> back;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
//            this.iterator = iterator;
            back = new LinkedList<>();

            while (iterator.hasNext()) {
                back.add(iterator.next());
            }
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return back.peek();
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            return back.poll();
        }

        @Override
        public boolean hasNext() {
            return !back.isEmpty();
        }
    }

    class PeekingIterator1 implements Iterator<Integer> {
        private Iterator<Integer> iterator;
        private Integer temp;

        public PeekingIterator1(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;
            temp = null;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if (temp == null) {
                if (!iterator.hasNext()) {
                    return null;
                }

                temp = iterator.next();
            }
            return temp;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if (temp != null) {
                Integer value = temp;
                temp = null;
                return value;
            } else {
                if (!iterator.hasNext()) {
                    return null;
                }
                return iterator.next();
            }
        }

        @Override
        public boolean hasNext() {
            if (temp != null) {
                return true;
            }
            return iterator.hasNext();
        }
    }
}
