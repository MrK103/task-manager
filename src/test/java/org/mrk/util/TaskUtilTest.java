package org.mrk.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.mrk.enums.Category;
import org.mrk.enums.Priority;
import org.mrk.interfaces.Task;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskUtilTest {

    @ParameterizedTest
    @NullSource
    void deadLineMs_NullPointerTest(Date date){
        int expected = 0; //if null should return 0
        long result = TaskUtil.deadLineMs(date);
        assertEquals(expected, result);
    }
    @Test
    void timeIsOverDeadLineMs() {
        //given
        Date dateFromTask = new Date();
        dateFromTask.setTime(1650022744537L); //15.04.22 14:39
        //when
        long result = TaskUtil.deadLineMs(dateFromTask);
        //then
        long excepted = dateFromTask.getTime() - new Date().getTime();
        if (excepted<0) excepted = 0;

        assertEquals(excepted, result);
    }


    @ParameterizedTest
    @NullSource
    void deadLineTime_NullPointerTest(Date date) {
        //given
        String excepted = "null"; //if null should return null
        //when
        String result = TaskUtil.deadLineTime(date);
        //then
        assertEquals(excepted , result);
    }

    @Test
    void testAddTaskOnce() {
        //given
        String name = "Mark";
        Category category = Category.ONCE;
        Priority priority = Priority.DEFAULT;
        Date date = new Date();

        //when
        Task result = TaskUtil.addTaskOnce(name, category,priority,date);

        //then
        assertAll(
                () -> assertEquals(result.getName(), name),
                () -> assertEquals(result.getDate(), date),
                () -> assertEquals(result.getPriority(), priority),
                () -> assertEquals(result.getCategory(), category)
        );
    }
}