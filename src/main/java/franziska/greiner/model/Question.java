package franziska.greiner.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import franziska.greiner.util.AnswersDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "questions")
public class Question {
    @Id
    private Integer id;

    @Column
    private String question;

    @Column
    private String description;

    @Column(name = "multiple_correct_answers")
    private Boolean multipleCorrectAnswers;

    @Column
    private String explanation;

    @Column
    private String tip;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "question_tag",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @Column
    private String category;

    @Enumerated(EnumType.STRING)
    @Column
    private Difficulty difficulty;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonDeserialize(using = AnswersDeserializer.class)
    private List<Answer> answers;

    public Question() {
    }
}
