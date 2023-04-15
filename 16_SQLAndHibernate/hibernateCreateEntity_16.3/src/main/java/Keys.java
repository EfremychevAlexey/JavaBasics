import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

public class Keys{

    @Embeddable
    @Getter
    @Setter
    public class SubscriptionKey implements Serializable {
        @Column(name = "student_id")
        private int studentId;
        @Column(name = "course_id")
        private int courseId;
    }

    @Embeddable
    @Getter
    @Setter
    public class PurchaseKey implements Serializable {
        @Column(name = "student_name")
        private String studentName;
        @Column(name = "course_name")
        private String courseName;
    }



}
