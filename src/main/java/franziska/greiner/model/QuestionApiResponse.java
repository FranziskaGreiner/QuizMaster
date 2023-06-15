package franziska.greiner.model;

import franziska.greiner.entities.Question;

import java.util.List;

public class QuestionApiResponse {
    private List<Question> items;

    public List<Question> getItems() {
        return items;
    }

    public void setItems(List<Question> items) {
        this.items = items;
    }
}
