package com.dancesphere.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DancerTest {

    private Dancer dancer;

    @BeforeEach
    void setUp() {
        dancer = new Dancer();
        dancer.setId("1");
        dancer.setDocumentId("123");
        dancer.setEmail("john@example.com");
        dancer.setName("John Doe");
        dancer.setStyle("Hip-Hop");
        dancer.setAge((short) 25);
    }

    @Test
    void getId() {
        assertEquals("1", dancer.getId());
    }

    @Test
    void getDocumentId() {
        assertEquals("123", dancer.getDocumentId());
    }

    @Test
    void getEmail() {
        assertEquals("john@example.com", dancer.getEmail());
    }

    @Test
    void getName() {
        assertEquals("John Doe", dancer.getName());
    }

    @Test
    void getStyle() {
        assertEquals("Hip-Hop", dancer.getStyle());
    }

    @Test
    void getAge() {
        assertEquals((short) 25, dancer.getAge());
    }

    @Test
    void setId() {
        dancer.setId("2");
        assertEquals("2", dancer.getId());
    }

    @Test
    void setDocumentId() {
        dancer.setDocumentId("456");
        assertEquals("456", dancer.getDocumentId());
    }

    @Test
    void setEmail() {
        dancer.setEmail("jane@example.com");
        assertEquals("jane@example.com", dancer.getEmail());
    }

    @Test
    void setName() {
        dancer.setName("Jane Doe");
        assertEquals("Jane Doe", dancer.getName());
    }

    @Test
    void setStyle() {
        dancer.setStyle("Salsa");
        assertEquals("Salsa", dancer.getStyle());
    }

    @Test
    void setAge() {
        dancer.setAge((short) 30);
        assertEquals((short) 30, dancer.getAge());
    }

    @Test
    void testEquals() {
        Dancer anotherDancer = new Dancer();
        anotherDancer.setId("1");
        anotherDancer.setDocumentId("123");
        anotherDancer.setEmail("john@example.com");
        anotherDancer.setName("John Doe");
        anotherDancer.setStyle("Hip-Hop");
        anotherDancer.setAge((short) 25);

        assertEquals(dancer, anotherDancer);

        anotherDancer.setId("2");
        assertNotEquals(dancer, anotherDancer);
    }

    @Test
    void canEqual() {
        Dancer anotherDancer = new Dancer();
        assertTrue(dancer.canEqual(anotherDancer));
    }

    @Test
    void testHashCode() {
        Dancer anotherDancer = new Dancer();
        anotherDancer.setId("1");
        anotherDancer.setDocumentId("123");
        anotherDancer.setEmail("john@example.com");
        anotherDancer.setName("John Doe");
        anotherDancer.setStyle("Hip-Hop");
        anotherDancer.setAge((short) 25);

        assertEquals(dancer.hashCode(), anotherDancer.hashCode());
    }

    @Test
    void testToString() {
        String expectedToString = "Dancer(id=1, documentId=123, email=john@example.com, name=John Doe, style=Hip-Hop, age=25)";
        assertEquals(expectedToString, dancer.toString());
    }
}
