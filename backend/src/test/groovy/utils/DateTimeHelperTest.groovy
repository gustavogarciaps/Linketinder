package utils

import org.junit.jupiter.api.Test

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertEquals

class DateTimeHelperTest {

    @Test
    void insertDateString(){

        LocalDate actual = DateTimeHelper.convertStringToTime("09/06/2001")
        LocalDate expected = LocalDate.of(2001,6,9)

        assertEquals(expected, actual)

    }
}
