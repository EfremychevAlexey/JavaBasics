public class Operator implements Employee {

    private String name = "Operator";
    private int profit = 50000 + (int) (Math.random() * 10000);
    private int monthSalary = 45000 + (int) (Math.random() * 5000);
    private Company company;

    @Override
    public Employee setCompany(Company company){
        this.company = company;
        company.setIncome(profit - getMonthSalary());
        return this;
    }

    @Override
    public int getMonthSalary() {
        return monthSalary;
    }

    @Override
    public String getName() {
        return this.name;
    }
}