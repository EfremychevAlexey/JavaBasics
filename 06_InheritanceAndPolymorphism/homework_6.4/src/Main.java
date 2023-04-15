import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Company company = new Company();
        ArrayList<Employee> list = new ArrayList<>();
        for (int i = 0; i < 180; i++){
            list.add(new Operator());
            if(i > 99){
                list.add(new Manager());
            }
            if(i > 176){
                list.add(new TopManager());
            }
        }
        company.hireAll(list);
        System.out.println(company.getIncome());


        company.getTopSalaryStaff(30).forEach(e -> System.out.println(e.getName() + " " + e.getMonthSalary()));
        System.out.println();


        company.getLowestSalaryStaff(15).forEach(e -> System.out.println(e.getName() + " " + e.getMonthSalary()));
        System.out.println();

        company.fire(132);

        company.getTopSalaryStaff(30).forEach(e -> System.out.println(e.getName() + " " + e.getMonthSalary()));
        System.out.println();

        company.getLowestSalaryStaff(15).forEach(e -> System.out.println(e.getName() + " " + e.getMonthSalary()));
    }
}
