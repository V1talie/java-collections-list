package com.endava.internship.collections;


import java.util.*;

public class StudentList<T> implements List<T> {

    private int size;
    private int maxsize;
    private Object[] elementArray;

    public StudentList() {
        this.maxsize = 10;
        this.elementArray = new Object[this.maxsize];
    }

    public StudentList(int desiredSize) {
        this.maxsize = desiredSize;
        this.elementArray = new Object[this.maxsize];
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
            if (elementArray[i].equals(t)) return true;
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
        System.arraycopy(elementArray, 0, arr, 0, size);
        return arr;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] ts) {
        if (ts.length >= size) {
            int i = 0;
            for (; i < size; i++) {
                ts[i] = (T) elementArray[i];
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
        elementArray[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object t) {
        if (contains(t)) {
            for (int i = 0; i < size; i++) {
                if (elementArray[i].equals(t)) {
                    elementArray[i] = null;
                    for (int j = i; j < size; j++) {
                        elementArray[j] = elementArray[j + 1];
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
            elementArray[i] = null;
        }
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int i) {
        return (T) elementArray[i];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int i, T t) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException("Index " + i + " is greater than array size (" + this.size + ")");
        }
        T oldValue = (T) elementArray[i];
        elementArray[i] = t;
        return oldValue;
    }

    @Override
    public void add(int i, T t) {
        if (size == maxsize) {
            growSize();
        }
        if (size + 1 <= maxsize && i <= size && i >= 0) {
            for (int j = size; j > i; j--) {
                elementArray[j] = elementArray[j - 1];
            }
            elementArray[i] = t;
            size++;
        }
    }

    public void growSize() {
        this.maxsize = maxsize * 2;
        elementArray = Arrays.copyOf(elementArray, maxsize);
    }

    public void growSize(int i) {
        this.maxsize = maxsize + i;
        elementArray = Arrays.copyOf(elementArray, maxsize);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int i) {
        elementArray[i] = null;
        T oldValue = null;
        for (int j = 0; j < size; j++) {
            oldValue = (T) elementArray[i];
            elementArray[i] = null;
            for (int k = j; k < size; k++) {
                elementArray[k] = elementArray[k + 1];
            }
        }

        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elementArray[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int occ = -1;
        for (int i = 0; i < size; i++) {
            if(elementArray[i] == null){continue;}
            if (elementArray[i].equals(o)){
                occ = i;
            }
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
    @SuppressWarnings("unchecked")
    public List<T> subList(int i, int i1) {
        if (i < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + i);
        if (i1 > size)
            throw new IndexOutOfBoundsException("toIndex = " + i1);
        if (i > i1)
            throw new IllegalArgumentException("fromIndex(" + i +
                    ") > toIndex(" + i1 + ")");
        if (i == i1) {
            return new StudentList<>();
        }
        List<T> subList = new StudentList<>();
        for (int j = i; j < i1; j++) {
            subList.add((T) elementArray[j]);
        }
        return subList;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        Object[] contain = collection.toArray();
        int leng = contain.length;
        if (leng == 0) return false;
        if (leng > size) {
            growSize(leng);
        }
        System.arraycopy(contain, 0, elementArray, size, leng);
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

        private int currentPosition;

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
        @SuppressWarnings("unchecked")
        public T next() {
            return (T) elementArray[currentPosition++];
        }

        @Override
        public boolean hasPrevious() {
            return elementArray[currentPosition - 1] != null;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T previous() {
            return (T) elementArray[currentPosition--];
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
