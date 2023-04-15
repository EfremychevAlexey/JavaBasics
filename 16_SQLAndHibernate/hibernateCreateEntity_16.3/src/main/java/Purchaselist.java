import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Purchaselist")
@Getter
@Setter
public class Purchaselist {

    @EmbeddedId
    private Keys.PurchaseKey id;

    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;

    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;

    private int price;
    @Column(name = "subscription_date")

    private Date subscriptionDate;

}
