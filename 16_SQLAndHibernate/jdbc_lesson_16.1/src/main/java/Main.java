import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "password";

        try{
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Courses.name AS course_name, count(*) / " +
                    "(DATEDIFF(MAX(subscription_date), MIN(subscription_date))/30.4375) AS avg_count_reg FROM Courses \n" +
                    "JOIN Subscriptions ON Courses.id = Subscriptions.course_id\n" +
                    "JOIN Students ON Students.id = Subscriptions.student_id \n" +
                    "WHERE subscription_date > DATE(\"2018-01-01 00:00:00\") GROUP BY Courses.name");

            while(resultSet.next()){
                String courseName = resultSet.getString("course_name");
                String avgCountReg = resultSet.getString("avg_count_reg");
                System.out.println(courseName + " - " + avgCountReg);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
