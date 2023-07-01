import franziska.greiner.model.QuestionTag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTagTest {

    @Test
    public void testGettersAndSetters() {
        QuestionTag questionTag = new QuestionTag();

        questionTag.setId(1);
        questionTag.setQuestionId(2);
        questionTag.setTagId(3);

        assertEquals(1, questionTag.getId());
        assertEquals(2, questionTag.getQuestionId());
        assertEquals(3, questionTag.getTagId());
    }
}
