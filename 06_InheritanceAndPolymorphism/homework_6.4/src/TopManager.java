public class TopManager implements Employee {

    private String name = "TopManager";
    private int profit = (int) (Math.random() * 150000);
    private int monthSalary = 50000 + (int) (Math.random() * 5000);
    private int bonus = (int)(monthSalary * 2.5);
    private Company company;

    @Override
    public Employee setCompany(Company company){
        this.company = company;
        company.setIncome(profit - getMonthSalary());
        return this;
    }

    @Override
    public int getMonthSalary() {
        if(company.getIncome() > 10000000 && bonus != 0){
            monthSalary += bonus;
            company.setIncome(-bonus);
            bonus = 0;
        }
        return monthSalary;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
