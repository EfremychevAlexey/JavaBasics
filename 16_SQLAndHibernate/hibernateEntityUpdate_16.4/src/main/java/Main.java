import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Purchase.class);
        Root<Purchase> root = query.from(Purchase.class);
        query.select(root);

        List<Purchase> purList = session.createQuery(query).getResultList();
        for(Purchase p : purList){
            String studentName = p.getStudentName();
            String courseName = p.getCourseName();
            Student student = session.byNaturalId(Student.class).using("name", studentName).load();
            Course course = session.byNaturalId(Course.class).using("name", courseName).load();
            LinkedPurchase linkedPurchase = new LinkedPurchase(student.getId(), course.getId());
            session.save(linkedPurchase);
        }
        transaction.commit();
        sessionFactory.close();
    }
}
