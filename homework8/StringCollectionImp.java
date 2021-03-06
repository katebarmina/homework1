import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

public class StringCollectionImp implements StringCollection, Iterable {

    private String[] array = new String[10];
    private int size;

    @Override
    public boolean add(String o) {
        if (size < array.length) {
            array[size] = o;
        } else {
            String[] newArray = new String[(int) (array.length * 1.5)];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            newArray[size] = o;
            array = newArray;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(int index, String o) {
        checkIndex(index);
        if (size < array.length) {
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
        } else {
            String[] newArray = new String[(int) (array.length * 1.5)];
            for (int i = 0; i < index; i++) {
                newArray[i] = array[i];
            }
            for (int i = 1; i < array.length - index; i++) {
                newArray[index + i] = array[index + i - 1];
            }
            array = newArray;
        }
        array[index] = o;
        size++;
        return true;
    }

    @Override
    public boolean delete(String o) {
        boolean isIndexFound = false;
        for (int i = 0; i < size - 1; i++) {
            if (array[i].equals(o)) {
                isIndexFound = true;
            }
            if (isIndexFound) {
                array[i] = array[i + 1];
            }
        }
        array[size] = null;
        size--;
        return true;
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return array[index];
    }


    @Override
    public boolean contain(String o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Collection str) {
        boolean isPresent = false;
        for (int i = 0;i<str.size();i++) {
                isPresent = str.contains(array[i]);
        }
        return isPresent;
    }

    @Override
    public boolean clear() {
        array = new String[10];
        size = 0;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be less than " + size + " and bigger then 0.");
        }
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator {

        private int currentIndex;

        public MyIterator() {
            this.currentIndex = currentIndex;
        }

        @Override
        public boolean hasNext() {
            return currentIndex != size;
        }

        @Override
        public Object next() {
            return array[currentIndex++];
        }

        @Override
        public void remove() {
            int delIndex = currentIndex - 1;
            delete(array[delIndex]);
            currentIndex--;
        }

        @Override
        public void forEachRemaining(Consumer action) {
            while (hasNext()) {
                action.accept(next());
            }
        }
    }
}