import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise02Test {

    @Test
    void surroundWithTags() {
        assertEquals("<b>a</b>", Exercise02.surroundWithTag("a", "b"));
        assertEquals("splendid", Exercise02.surroundWithTag("splendid",""));
        assertEquals("lazy", Exercise02.surroundWithTag("lazy", ""));
        assertEquals("<boom>lazy</boom>", Exercise02.surroundWithTag("lazy", "boom"));
    }
}