package patterns.decorator.util;

import java.util.Collection;
import java.util.Iterator;

public class Collections {

    public static <T> Collection<T> unmodifiableCollection(Collection<T> c) {
        return new UnmodifiableCollectionDecorator<T>(c);
    }


    static class UnmodifiableCollectionDecorator<T> implements Collection {

        Collection<T> c;

        UnmodifiableCollectionDecorator(Collection<T> c) {
            this.c = c;
        }

        @Override
        public int size() {
            return c.size();
        }

        @Override
        public boolean isEmpty() {
            return c.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return c.contains(o);
        }

        @Override
        public Iterator iterator() {
            return new UnmodifiableIterator(c.iterator());
        }

        @Override
        public Object[] toArray() {
            return c.toArray();
        }

        @Override
        public boolean add(Object o) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(Object o) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(Collection c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean removeAll(Collection c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsAll(Collection c) {
            return this.c.containsAll(c);
        }

        @Override
        public Object[] toArray(Object[] a) {
            return c.toArray(a);
        }


        class UnmodifiableIterator<E> implements Iterator<E> {

            Iterator<E> itr;

            public UnmodifiableIterator(Iterator<E> itr) {
                this.itr = itr;
            }


            @Override
            public boolean hasNext() {
                return itr.hasNext();
            }

            @Override
            public E next() {
                return itr.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

    }

}
