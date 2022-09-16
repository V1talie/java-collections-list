package com.endava.internship.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


class StudentListTests {
    private StudentList<Student> studentList;
    private ArrayList<Student> stList;
    private static final Student student0 = new Student("student", LocalDate.parse("2000-10-05"), "none");
    private static final Student student1 = new Student("student1", LocalDate.parse("2005-12-15"), "none");
    private static final Student student2 = new Student("student2", LocalDate.parse("1900-07-09"), "none");
    private static final Student student3 = new Student("student3", LocalDate.parse("2014-05-17"), "none");
    private static final Student student4 = new Student("student4", LocalDate.parse("2003-08-01"), "none");

    @BeforeEach
    void setUp() {
        studentList = new StudentList<>();
        stList = new ArrayList<>();
    }

    @Test
    void initialSize_shouldBeZero() {
        assertEquals(0, studentList.size());
    }

    @Test
    void addingANewElement_shouldAddItAndIncreaseSize() {
        studentList.add(student0);
        stList.add(student0);

        assertAll(
                () -> assertThat(studentList).contains(student0),
                () -> assertThat(studentList).hasSize(1),
                () -> assertThat(studentList.iterator().next()).isEqualTo(student0),
                () -> assertThat(studentList.get(0)).isEqualTo(student0),
                () -> assertThat(studentList.toArray(new Student[1])).containsOnly(student0),
                () -> assertThat(studentList).containsExactlyElementsOf(stList),
                () -> assertThat(studentList.add(student1)).isEqualTo(stList.add(student1))
        );
    }

    @Test
    void removingAnElement_shouldRemoveItFromList() {
        studentList.add(student1);
        studentList.add(student0);
        stList.add(student1);
        stList.add(student0);

        assertAll(
                () -> assertThat(studentList).hasSize(2),
                () -> assertTrue(studentList.remove(student0)),
                () -> assertThat(studentList).doesNotContain(student0),
                () -> assertThat(studentList.get(0)).isEqualTo(student1),
                () -> assertThat(studentList.remove(student1)).isEqualTo(stList.remove(student1))
        );
    }

    @Test
    void aNewCreatedList_shouldBeEmptyByDefault() {
        assertTrue(studentList.isEmpty());
    }

    @Test
    void aNewCreatedList_shouldNotContainAnyElement() {
        assertFalse(studentList.contains(student1));
    }

    @Test
    void anItemThatDoesNotExist_shouldBeNotPossibleToRemove() {
        assertFalse(studentList.remove(student0));
    }

    @Test
    void creatingASubListFromAList_shouldReturnAlistFrom2Indexes() {
        List<Student> subListTest;
        studentList.add(student0);
        studentList.add(student0);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student0);
        studentList.add(student0);
        subListTest = studentList.subList(2, 5);
        assertAll(
                () -> assertThat(subListTest).doesNotContain(student0),
                () -> assertThat(subListTest).contains(student1),
                () -> assertThat(subListTest).contains(student2),
                () -> assertThat(subListTest).contains(student3),
                () -> assertThat(subListTest).hasSize(3)
        );
    }

    @Test
    void creatingAsubListWithNegativeFromIndex_shouldThrowIndexOutOfBounds() {
        int from = -1;
        studentList.add(student0);
        studentList.add(student0);
        studentList.add(student1);
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.subList(from, 2));
    }

    @Test
    void creatingAsubListWithToIndexGreaterThanListSize_shouldThrowIndexOutOfBounds() {
        int to = 20;
        studentList.add(student0);
        studentList.add(student0);
        studentList.add(student1);
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.subList(1, to));
    }

    @Test
    void creatingAsubListWhereFromIsGreaterThanTo_shouldThrowIndexOutOfBounds() {
        int from = 3;
        int to = 2;
        studentList.add(student0);
        studentList.add(student0);
        studentList.add(student1);
        assertThrows(IllegalArgumentException.class, () -> studentList.subList(from, to));
    }

    @Test
    void WhenSetPositionIsHigherThanSize_shouldThrowsIndexOutOfBounds() {
        int setPosition = 15;
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.set(setPosition, student0));
    }

    @Test
    void WhenRemoveIndexIsNegative_shouldThrowsIndexOutOfBounds() {
        int removeIndex = -1;
        assertThrows(IndexOutOfBoundsException.class, () -> studentList.remove(removeIndex));
    }

    @Test
    void creatingAsubListWithSameToAndFromPosition_shouldReturnAnEmptyList() {
        studentList.add(student0);
        studentList.add(student0);
        studentList.add(student1);
        int from = 1;
        int to = 1;
        List<Student> subListTest = studentList.subList(from, to);
        assertThat(subListTest).isEmpty();
    }

    @Test
    void removeAtSpecificIndex_shouldRemoveAndDecreaseSize() {
        studentList.add(student0);
        studentList.add(student1);
        studentList.add(student2);
        studentList.remove(1);

        assertAll(
                () -> assertThat(studentList).hasSize(2),
                () -> assertThat(studentList).doesNotContain(student1),
                () -> assertThat(studentList.get(1)).isEqualTo(student2)
        );
    }

    @Test
    void whenIteratorCurrentPositionIsGreaterThanZero_listIteratorHasPreviousShouldReturnTrue() {
        studentList.add(student0);
        studentList.add(student1);
        studentList.add(student2);

        ListIterator<Student> listIterator = studentList.listIterator();
        listIterator.nextIndex();
        assertTrue(listIterator.hasPrevious());

    }

    @Test
    void IteratorReturnsPreviousLocation_shouldReturnPreviousIteratorPosition() {
        studentList.add(student0);
        studentList.add(student1);
        studentList.add(student2);

        ListIterator<Student> listIterator = studentList.listIterator();
        listIterator.next();
        assertThat(listIterator.previousIndex()).isZero();
    }

    @Test
    void iteratorRemoveAtCurrentPosition_shouldRemoveElementAtThatPosition() {
        studentList.add(student0);
        studentList.add(student1);
        studentList.add(student2);

        ListIterator<Student> listIterator = studentList.listIterator();
        listIterator.next();
        listIterator.remove();
        assertThat(studentList).doesNotContain(student1);
    }

    @Test
    void iteratorSetAtCurrentPosition_shouldChangeElementAtCurrentPositionWithAnewOne() {
        studentList.add(student0);
        studentList.add(student1);
        studentList.add(student2);

        ListIterator<Student> listIterator = studentList.listIterator();
        listIterator.next();
        listIterator.set(student4);
        assertThat(studentList)
                .contains(student4)
                .hasSize(3);
    }

    @Test
    void iteratorAddAtCurrentPosition_shouldAddTheNewElementAndIncreaseSize() {
        studentList.add(student0);
        studentList.add(student1);
        studentList.add(student2);

        ListIterator<Student> listIterator = studentList.listIterator();
        listIterator.add(student4);
        assertAll(
                () -> assertThat(studentList).contains(student4),
                () -> assertThat(studentList).contains(student2),
                () -> assertThat(studentList).contains(student4),
                () -> assertThat(studentList).contains(student0),
                () -> assertThat(studentList).hasSize(4)
        );
    }

    @Test
    void iteratorPrevious_shouldReturnElementAtPreviousPosition() {
        studentList.add(student0);
        studentList.add(student1);
        studentList.add(student2);

        ListIterator<Student> listIterator = studentList.listIterator();
        listIterator.next();
        assertThat(listIterator.previous()).isEqualTo(student0);

    }

    @ParameterizedTest
    @MethodSource("studentProvider")
    void setChangeTest(List<Student> students) {
        studentList.addAll(students);
        List<Student> arrayList = new ArrayList<>();
        arrayList.addAll(students);
        arrayList.set(0, student4);
        studentList.set(0, student4);

        assertAll(
                () -> assertThat(studentList).hasSize(3),
                () -> assertThat(studentList).contains(student0),
                () -> assertThat(studentList).doesNotContain(student2),
                () -> assertThat(studentList.get(0)).isEqualTo(student4),
                () -> assertThat(studentList.get(0)).isEqualTo(arrayList.get(0)),
                () -> assertThat(studentList).containsExactlyElementsOf(arrayList)
        );
    }

    @Test
    void getMethod_shouldReturnTheElementAtIndicatedPosition() {
        studentList.add(student0);
        studentList.add(student1);
        assertThat(studentList.get(0)).isInstanceOf(Student.class);
        assertThat(studentList.get(0)).isEqualTo(student0);
    }

    @Test
    void addAll_shouldAddAllElementsFromSourceListToDestinationList() {
        StudentList<Student> studentListSource = new StudentList<>();
        StudentList<Student> studentListDest = new StudentList<>();
        List<Student> arrayListSource = new ArrayList<>();
        List<Student> arrayListDest = new ArrayList<>();

        studentListSource.add(student0);
        studentListSource.add(student1);
        studentListSource.add(student2);
        studentListDest.add(student3);
        studentListDest.addAll(studentListSource);

        arrayListSource.add(student0);
        arrayListSource.add(student1);
        arrayListSource.add(student2);
        arrayListDest.add(student3);
        arrayListDest.addAll(studentListSource);
        assertAll(
                () -> assertThat(studentListDest).hasSize(4),
                () -> assertThat(studentListDest).contains(student0),
                () -> assertThat(studentListDest).contains(student0),
                () -> assertThat(studentListDest).contains(student1),
                () -> assertThat(studentListDest).contains(student2),
                () -> assertThat(studentListDest).contains(student3),
                () -> assertThat(studentListDest).containsExactlyElementsOf(arrayListDest)
        );
    }

    @Test
    void clearMethod_shouldRemoveAllElementsFromListAndSetSizeToZero() {
        studentList.add(student4);
        studentList.add(student4);
        studentList.add(student4);
        studentList.add(student4);
        studentList.add(student4);
        studentList.add(student4);
        studentList.add(student4);
        studentList.add(student4);
        studentList.add(student4);
        studentList.add(student4);
        studentList.add(student4);

        assertThat(studentList).hasSize(11);
        studentList.clear();
        assertThat(studentList).isEmpty();
    }

    @Test
    void addAtSpecificLocation_shouldAddTheElementAndShiftListToRight() {
        List<Student> list = new StudentList<>(15);
        for (int i = 0; i < 12; i++) {
            list.add(student1);
        }
        list.add(11, student0);

        assertAll(
                () -> assertThat(list).hasSize(13),
                () -> assertThat(list.indexOf(student0)).isEqualTo(11),
                () -> assertThat(list.lastIndexOf(student1)).isEqualTo(12)
        );
    }

    @Test
    void ifAnElementDoesNotExistIndexOf_shouldReturnNegativeOne() {
        studentList.add(student4);
        studentList.add(student3);
        assertThat(studentList.indexOf(student1)).isEqualTo(-1);


        ArrayList<Student> st = new ArrayList<>();
        st.add(student0);
        st.add(student1);
        ListIterator<Student> stList = st.listIterator();
        System.out.println(stList.nextIndex());

    }

    private static Stream<Arguments> studentProvider() {
        List<Student> stList = new StudentList<>();
        stList.add(student2);
        stList.add(student0);
        stList.add(student1);
        List<Student> secondList = new StudentList<>();
        secondList.add(student0);
        secondList.add(student0);
        secondList.add(student3);
        return Stream.of(
                Arguments.of(stList),
                Arguments.of(secondList));
    }
}