public class Main {

  public static void main(String[] args) throws InterruptedException {
    Bank bank = new Bank();
    Account account1 = new Account(200000, "account_01");
    Account account2 = new Account(100000, "account_02");

    bank.addAccount(account1);
    bank.addAccount(account2);

    bank.transfer(account1.getAccNumber(), account2.getAccNumber(), 45000);
    System.out.println(account1.getMoney() + "\n" + account2.getMoney());
  }
}
