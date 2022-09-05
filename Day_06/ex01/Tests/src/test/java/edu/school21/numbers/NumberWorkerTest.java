package edu.school21.numbers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NumberWorkerTest {
    public NumberWorker nw;

    @BeforeAll
    public void setUP() throws Exception {
        nw = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 661, 1009, 4177, 7919})
    void isPrimeForPrimes(int number) {
        Assertions.assertTrue(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 12, 660, 1008, 4176, 7918})
    void isPrimeForNotPrimes(int number) {
        Assertions.assertFalse(nw.isPrime(number));
    }

    @ParameterizedTest()
    @ValueSource(ints = {-241, -421, -142, -1, 0, 1 })
    void isPrimeForIncorrectNumbers(int number) {
        Assertions.assertThrows(IllegalNumberException.class, () -> {
            nw.isPrime(number);
        });
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"}, delimiter = ',')
    void isTruedigitsSum(int number, int res) {
        Assertions.assertEquals(nw.digitsSum(number), res);
    }
}