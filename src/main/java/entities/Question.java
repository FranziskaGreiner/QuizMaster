package entities;

public class Question {
    private long id;
    private final String question;
    private String description;
    private final String[] answers;
    private Boolean multipleCorrectAnswers;
    private String[] correctAnswers;
    private String explanation;
    private String tip;
    private String[] tags;
    private String category;
    private Enum difficulty;



    public Question(String question, String[] answers) {
        this.question = question;
        this.answers = answers;
    }
}
