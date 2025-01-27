package com.dancesphere.dancer.application.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DancerNotFoundExceptionTest {

    @Test
    public void testDancerNotFoundExceptionMessage() {
        String message = "Dancer not found";
        DancerNotFoundException exception = new DancerNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }
}