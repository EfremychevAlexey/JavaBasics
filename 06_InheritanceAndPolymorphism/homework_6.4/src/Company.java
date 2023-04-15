import java.util.*;

public class Company {

    private int income = 0;
    private ArrayList<Employee> employeeList;

    public Company() {
        employeeList = new ArrayList<>();
    }

    public Company(ArrayList<Employee> list, int income) {
        employeeList = new ArrayList<>(list);
        this.income = income;
    }

    public void hire(Employee employee) {
        employeeList.add(employee.setCompany(this));
    }

    public void hireAll(ArrayList<Employee> list) {
        for(Employee employee: list){
            hire(employee);
        }
        list.clear();
    }

    public void fire(int n) {
        if (n > employeeList.size()) n = employeeList.size();
        employeeList.removeAll(getLowestSalaryStaff(n));
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income += income;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        Collections.sort(employeeList, Comparator.comparing(Employee::getMonthSalary));
        ArrayList<Employee> topSalaryStaff = new ArrayList<>();
        if(count > employeeList.size()) count = employeeList.size();
        for (int i = 0; i < count; i++) {
            topSalaryStaff.add(0, employeeList.get(employeeList.size() - 1 - i));
        }
        return topSalaryStaff;
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        Collections.sort(employeeList, Comparator.comparing(Employee::getMonthSalary));
        ArrayList<Employee> lowerSalaryStaff = new ArrayList<>();
        if(count > employeeList.size()) count = employeeList.size();
        for (int i = 0; i < count; i++) {
            lowerSalaryStaff.add(0, employeeList.get(i));
        }
        return lowerSalaryStaff;
    }
}