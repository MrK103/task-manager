package org.mrk.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void validInt_test() {
        //given
        String value = "10";
        //when
        int result = Util.validInt(value);
        //then
        int excepted = Integer.parseInt(value);
        assertEquals(excepted, result);
    }

    @ParameterizedTest
    @NullSource
    void validInt_NullPointerTest(String test) {
        //when
        int result = Util.validInt(test); // return -1
        //then
        assertEquals(-1, result);
    }
    @Test
    void validTest_EnteringIncorrectData() {
        //given
        String value = "Ten"; // return -1
        //when
        int result = Util.validInt(value);
        //then
        assertEquals(-1, result);
    }
}