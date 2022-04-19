package org.mrk.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @ParameterizedTest
    @ValueSource(strings = { "-1", "7", "99" })
    void validInt_test(String value) {
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


    @ParameterizedTest
    @MethodSource("argsProviderFactory")
    void validInt_EnteringIncorrectData(String argument) {
        //when
        int result = Util.validInt(argument); //should return -1
        //then
        assertEquals(-1, result);
    }

    static Stream<String> argsProviderFactory() {
        return Stream.of("Ten", "Nine", "Null");
    }
}