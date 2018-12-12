import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractCollection<E> implements Collection<E> {

    @Override
    public int size() {
        int size = 0;
        for (E e : this)
            size++;
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object x) {
        for (E e : this)
            if (e == x || x != null && x.equals(e))
                return true;
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean contains = false;
        Iterator<?> itr = c.iterator();
        while(itr.hasNext()){
            contains = contains(itr.next());
        }
        return contains;
    }

    @Override
    public void clear() {
        Iterator itr = iterator();
        while (itr.hasNext()) {
            itr.next();
            itr.remove();
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        AtomicBoolean added = new AtomicBoolean(false);
        c.forEach(e -> added.set(add(e)));
        return added.get();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        AtomicBoolean added = new AtomicBoolean(false);
        c.forEach(e -> added.set(remove(e)));
        return added.get();
    }

    @Override
    public boolean remove(Object x) {

        Iterator itr = iterator();
        while (itr.hasNext()) {
            if (itr.next().equals(x)) {
                itr.remove();
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO
        return false;
    }

    @Override
    public Object[] toArray() {
        // TODO
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO
        return null;
    }
}
