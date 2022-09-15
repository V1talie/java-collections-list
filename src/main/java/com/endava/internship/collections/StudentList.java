package com.endava.internship.collections;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Collection;

public class StudentList<T> implements List<T> {

    private int size;
    private int maxsize;
    private Object[] temp;

    public StudentList() {
        this.maxsize = 10;
        this.temp = new Object[this.maxsize];
    }

    public StudentList(int desiredSize) {
        this.maxsize = desiredSize;
        this.temp = new Object[this.maxsize];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object t) {
        for (int i = 0; i < this.size; i++) {
            if (temp[i].equals(t)) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return listIterator(0);
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        System.arraycopy(temp, 0, arr, 0, size);
        return arr;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        if (ts.length >= size) {
            int i = 0;
            for (; i < size; i++) {
                ts[i] = (T) temp[i];
            }
            for (; i < ts.length; i++) {
                ts[i] = null;
            }
        }
        return ts;
    }

    @Override
    public boolean add(T t) {
        if (size == maxsize) {
            growSize();
        }
        temp[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object t) {
        if (contains(t)) {
            for (int i = 0; i < size; i++) {
                if (temp[i].equals(t)) {
                    temp[i] = null;
                    for (int j = i; j < size; j++) {
                        temp[j] = temp[j + 1];
                    }
                }
            }
            size--;
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            temp[i] = null;
        }
        size = 0;
    }

    @Override
    public T get(int i) {
        return (T) temp[i];
    }

    @Override
    public Student set(int i, Student student) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " is greater than array size (" + this.size + ")");
        }
        Student oldValue = temp[i];
        temp[i] = student;
        return oldValue;
    }

    @Override
    public void add(int i, T t) {
        if (size == maxsize) {
            growSize();
        }
        if (size + 1 <= maxsize && i <= size && i >= 0) {
            for (int j = size - 1; j > i; j--) {
                temp[j] = temp[j - 1];
            }
            temp[i] = t;
            size++;
        }
    }

    public void growSize() {
        this.maxsize = maxsize * 2;
        temp = Arrays.copyOf(temp, maxsize);
    }

    public void growSize(int i) {
        this.maxsize = maxsize + i;
        temp = Arrays.copyOf(temp, maxsize);
    }

    @Override
    public T remove(int i) {
        temp[i] = null;
        T oldValue = null;
        for (int j = 0; j < size; j++) {
            oldValue = (T) temp[i];
            temp[i] = null;
            for (int k = j; k < size; k++) {
                temp[k] = temp[k + 1];
            }
        }

        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (temp[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int occ = -1;
        for (int i = 0; i < size; i++) {
            if (temp[i].equals(o)) occ = i;
        }
        return occ;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyIterator();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return new MyIterator(i);
    }

    @Override
    public List<Student> subList(int i, int i1) {
        if (i < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + i);
        if (i1 > size)
            throw new IndexOutOfBoundsException("toIndex = " + i1);
        if (i > i1)
            throw new IllegalArgumentException("fromIndex(" + i +
                    ") > toIndex(" + i1 + ")");
        if (i == i1) {
            List<Student> subList = new StudentList();
            return subList;
        }
        List<Student> subList = new StudentList();
        for (int j = i; j < i1; j++) {
            subList.add(temp[j]);
        }
        return subList;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        Object[] contain = collection.toArray();
        int leng = contain.length;
        if (leng == 0) return false;
        Object[] temp = this.temp;
        if (leng > size) {
            growSize(leng);
        }
        System.arraycopy(contain, 0, temp, size, leng);
        size = size + leng;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    private class MyIterator implements ListIterator<T> {

        int currentPosition;

        MyIterator() {
            currentPosition = 0;
        }

        MyIterator(int index) {
            currentPosition = index;
        }

        @Override
        public boolean hasNext() {
            return currentPosition < size;
        }

        @Override
        public T next() {
            return (T) temp[currentPosition++];
        }

        @Override
        public boolean hasPrevious() {
            return temp[currentPosition - 1] != null;
        }

        @Override
        public T previous() {
            return (T) temp[currentPosition--];
        }

        @Override
        public int nextIndex() {
            return currentPosition++;
        }

        @Override
        public int previousIndex() {
            return currentPosition--;
        }

        @Override
        public void remove() {
            StudentList.this.remove(currentPosition);
        }

        @Override
        public void set(T t) {
            StudentList.this.set(currentPosition, t);
        }

        @Override
        public void add(T t) {
            StudentList.this.add(currentPosition, t);
        }
    }
}
