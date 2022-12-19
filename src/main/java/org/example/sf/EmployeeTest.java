package org.example.sf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    @Test
    public void testToString_johnSmith_success() {
        Employee johnSmith = new Employee(1, "John", "Smith", "USA", 25);

        String expected = "Employee{" +
                "id=1" +
                ", firstName='John'" +
                ", lastName='Smith'" +
                ", country='USA'" +
                ", age=25" +
                '}';

        Assertions.assertEquals(expected, johnSmith.toString());
    }

    @Test
    public void testToString_johnSmith_failure() {
        Employee johnSmith = new Employee(2, "Inav", "Petrov", "RU", 23);

        String expected = "Employee{" +
                "id=1" +
                ", firstName='John'" +
                ", lastName='Smith'" +
                ", country='USA'" +
                ", age=25" +
                '}';

        Assertions.assertNotEquals(expected, johnSmith.toString());
    }
}