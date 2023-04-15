import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2025);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {

        DateFormat df = new SimpleDateFormat("yyyy");

        return staff.stream() //создаем стрим из элементов списка
                .filter(e -> Integer.parseInt(df.format(e.getWorkStart())) == year) //оставляем только тех сотрудников,
                //которые были приняты в заданном году
                .max(Comparator.comparing(Employee::getSalary)) //находим сотрудника с максимальной зарплатой
                .orElse(null); //возвращаем элемент (если элемента нет возвращаем null)
    }
}