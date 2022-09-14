package com.endava.internship.collections;

import java.util.*;

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
        //TODO - ??????
        if(ts.length>=size){
            int i =0;
            for(; i < size; i++){
                ts[i] = (T) temp[i];
            }
            for(;i< ts.length;i++){
                ts[i] = null;
            }
        }
        return ts;
    }

    @Override
    public boolean add(Student student) {
        if(size == maxsize){growSize();}
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
        //TODO - PARCA
        if(size == maxsize){growSize();}
        if(size+1<=maxsize && i<=size && i>=0){
            for(int j=size-1;j>i;j--){
                temp[j]=temp[j-1];
            }
            temp[i] = student;
            size++;
        }
    }

    public void growSize(){
        this.maxsize = maxsize*2;
        /*Student[] newArr = new Student[maxsize];
        for(int i = 0; i < size; i++){
            newArr[i] = temp[i];
        }*/
        temp = Arrays.copyOf(temp,maxsize);
    }
    public void growSize(int i){
        this.maxsize = maxsize+i;
        temp = Arrays.copyOf(temp,maxsize);
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
        //TODO - PARCA
        return new MyIterator<>();
    }

    @Override
    public ListIterator<Student> listIterator(int i) {
        //TODO - PARCA
        return new MyIterator<>(i);
    }

    @Override
    public List<Student> subList(int i, int i1) {
        //TODO - ????
//        Student[] subList = new Student[i1-i] ;
//        for(int j=i;j<i1;j++){
//            subList[j] = temp[j];
//        }
//        return List.of(subList);
        if(i>=0 && i<i1 && i1<size){
            List<Student> subList = new StudentList();
            for(int j=i;j<i1;j++){
                subList.add(temp[j]);
            }
            return subList;
        }
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends Student> collection) {
        //TODO -
        Object[] contain = collection.toArray();
        int leng = contain.length;
        if(leng == 0) return false;
        Student[] temp = this.temp;
        if(leng>size){
            growSize(leng);
        }
        System.arraycopy(contain,0,temp,size,leng);
        size = size+leng;
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

    private class MyIterator<Student> implements ListIterator<com.endava.internship.collections.Student> {

        int current_pos;

        MyIterator(){
            current_pos = 0;
        }

        MyIterator(int index){
            current_pos = index;
        }

        @Override
        public boolean hasNext() {
            if(current_pos < size) return true;
            return false;
        }

        @Override
        public com.endava.internship.collections.Student next() {
            return temp[current_pos++];
        }

        @Override
        public boolean hasPrevious() {
            if(temp[current_pos-1]!= null) return true;
            return false;
        }

        @Override
        public com.endava.internship.collections.Student previous() {
            return temp[current_pos--];
        }

        @Override
        public int nextIndex() {
            return current_pos++;
        }

        @Override
        public int previousIndex() {
            return current_pos--;
        }

        @Override
        public void remove() {
            StudentList.this.remove(current_pos);
        }

        @Override
        public void set(com.endava.internship.collections.Student student) {
            StudentList.this.set(current_pos,student);
        }

        @Override
        public void add(com.endava.internship.collections.Student student) {
            StudentList.this.add(current_pos,student);
        }
    }
}
