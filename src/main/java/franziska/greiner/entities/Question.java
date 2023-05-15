package franziska.greiner.entities;

import java.util.List;
import java.util.Map;

public class Question {
    private int id;
    private String question;
    private String description;
    private Map<String, String> answers;
    private boolean multiple_correct_answers;
    private Map<String, String> correct_answers;
    private String correct_answer;
    private String explanation;
    private String tip;
    private List<String> tags;
    private String category;
    private String difficulty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }

    public boolean isMultiple_correct_answers() {
        return multiple_correct_answers;
    }

    public void setMultiple_correct_answers(boolean multiple_correct_answers) {
        this.multiple_correct_answers = multiple_correct_answers;
    }

    public Map<String, String> getCorrect_answers() {
        return correct_answers;
    }

    public void setCorrect_answers(Map<String, String> correct_answers) {
        this.correct_answers = correct_answers;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Question() { }
}
