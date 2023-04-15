import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Courses")
public class Course {
    //

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NaturalId
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Teacher teacher;

    private int price;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "Subscriptions",
//            joinColumns = {@JoinColumn(name = "course_id")},
//            inverseJoinColumns = {@JoinColumn(name = "student_id")})
//    private List<Student> students;

}

