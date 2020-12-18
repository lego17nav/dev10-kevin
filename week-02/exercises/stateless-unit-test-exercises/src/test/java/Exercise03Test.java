import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise03Test {

    @Test
    void hasAllVowels() {
        assertEquals(true,Exercise03.hasAllVowels("Has everything in our"));
        assertEquals(false,Exercise03.hasAllVowels("John"));
        assertEquals(false,Exercise03.hasAllVowels(""));
    }
}