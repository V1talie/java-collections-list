package com.endava.internship.collections;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Collection;

public class StudentList implements List<Student> {

    private int size;
    private int maxsize;
    private Student[] temp;

    public StudentList() {
        this.maxsize = 10;
        this.temp = new Student[this.maxsize];
    }

    public StudentList(int desiredSize) {
        this.maxsize = desiredSize;
        this.temp = new Student[this.maxsize];
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
    public boolean contains(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (temp[i].equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<Student> iterator() {
        return listIterator(0);
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = temp[i];
        }
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
    public boolean add(Student student) {
        if (size == maxsize) {
            growSize();
        }
        temp[size++] = student;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            for (int i = 0; i < size; i++) {
                if (temp[i].equals(o)) {
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
    public Student get(int i) {
        return temp[i];
    }

    @Override
    public Student set(int i, Student student) {
        Student oldValue = temp[i];
        temp[i] = student;
        return oldValue;
    }

    @Override
    public void add(int i, Student student) {
        if (size == maxsize) {
            growSize();
        }
        if (size + 1 <= maxsize && i <= size && i >= 0) {
            for (int j = size - 1; j > i; j--) {
                temp[j] = temp[j - 1];
            }
            temp[i] = student;
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
    public Student remove(int i) {
        temp[i] = null;
        Student oldValue = null;
        for (int j = 0; j < size; j++) {
            oldValue = temp[i];
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
    public ListIterator<Student> listIterator() {
        return new MyIterator();
    }

    @Override
    public ListIterator<Student> listIterator(int i) {
        return new MyIterator(i);
    }

    @Override
    public List<Student> subList(int i, int i1) {
        if (i >= 0 && i < i1 && i1 < size) {
            List<Student> subList = new StudentList();
            for (int j = i; j < i1; j++) {
                subList.add(temp[j]);
            }
            return subList;
        }
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends Student> collection) {
        Object[] contain = collection.toArray();
        int leng = contain.length;
        if (leng == 0) return false;
        Student[] temp = this.temp;
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
    public boolean addAll(int i, Collection<? extends Student> collection) {
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

    private class MyIterator implements ListIterator<Student> {

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
        public Student next() {
            return temp[currentPosition++];
        }

        @Override
        public boolean hasPrevious() {
            return temp[currentPosition - 1] != null;
        }

        @Override
        public Student previous() {
            return temp[currentPosition--];
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
        public void set(Student student) {
            StudentList.this.set(currentPosition, student);
        }

        @Override
        public void add(Student student) {
            StudentList.this.add(currentPosition, student);
        }
    }
}
