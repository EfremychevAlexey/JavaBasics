import Keys.LinkedPurchaseKey;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchase {
//
    @EmbeddedId
    private LinkedPurchaseKey id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

    public LinkedPurchase(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
        id = new LinkedPurchaseKey(studentId, courseId);
    }
}