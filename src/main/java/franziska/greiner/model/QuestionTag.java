package franziska.greiner.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "question_tags")
public class QuestionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "tag_id")
    private Integer tagId;
}
