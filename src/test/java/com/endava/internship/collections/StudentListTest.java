package com.endava.internship.collections;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StudentListTest {
    private StudentList studentList;

    private static final Student student0 = new Student("student", LocalDate.parse("2000-10-05"),"none");
    private static final Student student1 = new Student("student1", LocalDate.parse("2005-12-15"),"none");
    private static final Student student2 = new Student("student2", LocalDate.parse("1900-7-9"),"none");
    private static final Student student3 = new Student("student3", LocalDate.parse("2014-5-17"),"none");
    private static final Student student4 = new Student("student4", LocalDate.parse("2003-8-01"),"none");

    @BeforeEach
    void setUp() {
        studentList = new StudentList();
    }

    @Test
    public void testAdd() {

        studentList.add(student0);

        assertAll(
                () -> assertThat(studentList).contains(student0),
                () -> assertThat(studentList).hasSize(1)
        );
    }

}
