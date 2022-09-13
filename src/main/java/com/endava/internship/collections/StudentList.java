package com.endava.internship.collections;

import java.util.*;

public class StudentList implements List<Student> {

    private int size;
    private int maxsize;
    private final Student[] temp;

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
        //TODO
        return null;
    }

    @Override
    public boolean add(Student student) {
        add(size, student);
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
        //TODO
        size = size + 1;
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
        //TODO
        return null;
    }

    @Override
    public ListIterator<Student> listIterator(int i) {
        //TODO
        return null;
    }

    @Override
    public List<Student> subList(int i, int i1) {
        //TODO
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends Student> collection) {
        //TODO
        return false;
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
}
