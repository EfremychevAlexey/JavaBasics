public class Manager implements Employee {

    private String name = "Manager";
    private int profit = 115000 + (int) (Math.random() * 25000);
    private int monthSalary = 43000 + (int) (profit * 0.05);
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