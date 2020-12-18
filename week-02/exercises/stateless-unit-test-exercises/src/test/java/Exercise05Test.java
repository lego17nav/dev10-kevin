import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise05Test {

    @Test
    void isWithinFiveOfAHundred() {

        Exercise05 exercise05 = new Exercise05();

        assertEquals(true, exercise05.isWithinFiveOfAHundred(100) );
        assertEquals(false, exercise05.isWithinFiveOfAHundred(25) );
        assertEquals(true, exercise05.isWithinFiveOfAHundred(105) );
        assertEquals(true, exercise05.isWithinFiveOfAHundred(-1000) );
    }
}