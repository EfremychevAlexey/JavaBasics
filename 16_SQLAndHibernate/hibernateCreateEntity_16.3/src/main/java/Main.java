import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Course course = session.get(Course.class,49);
        System.out.println(course);
        session.delete(course);


        //Subscription subscription = session.get(Subscription.class, new KeySubscription(1, 2));
        //System.out.println(subscription.getDateTime());
        //studentList.forEach(System.out::println);
        //System.out.println(course.getStudents().size());



        //session.save(course);
        //session.delete(course);
        transaction.commit();
        sessionFactory.close();
    }
}
