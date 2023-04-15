import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

public class BankTest extends TestCase {

  Bank bank = new Bank();
  Account account1 = new Account(100000, "Account_001");
  Account account2 = new Account(45000, "Account_002");
  static int countRunningThread = 0;


  @Override
  protected void setUp() throws Exception {
    bank.addAccount(account1);
    bank.addAccount(account2);
  }

  public void testTransfer() throws InterruptedException {

    long expectedMoney1 = account1.getMoney() - 1000;
    long expectedMoney2 = account2.getMoney() + 1000;
    long expectedSumAllAccount = account1.getMoney() + account2.getMoney();

    initList(30, 20, 100).forEach(Thread::start);

    Thread.sleep(6000);
    assertEquals(expectedMoney1, account1.getMoney());
    assertEquals(expectedMoney2, account2.getMoney());
    assertEquals(expectedSumAllAccount, bank.getSumAllAccounts());
  }

  public void testTransferManyOperation() throws InterruptedException {

    account1.setMoney(80000);
    account2.setMoney(20000);
    long expectedMoney1 = 50000;
    long expectedMoney2 = 50000;
    long expectedSumAllAccount = account1.getMoney() + account2.getMoney();

    initList(2, 1, 30000).forEach(Thread::start);

    Thread.sleep(6000);
    assertEquals(expectedMoney1, account1.getMoney());
    assertEquals(expectedMoney2, account2.getMoney());
    assertEquals(expectedSumAllAccount, bank.getSumAllAccounts());
  }

  public void testTransferLimitOfFounds() throws InterruptedException {

    long expectedMoney1 = account1.getMoney() - 10;
    long expectedMoney2 = account2.getMoney() + 10;
    long expectedSumAllAccount = account1.getMoney() + account2.getMoney();

    initList(1, 49000, 10).forEach(Thread::start);

    Thread.sleep(7000);
    assertEquals(expectedMoney1, account1.getMoney());
    assertEquals(expectedMoney2, account2.getMoney());
    assertEquals(expectedSumAllAccount, bank.getSumAllAccounts());
  }

  public void testTransferBlockAccount() throws InterruptedException {

    account1.setBlock(true);
    long expectedMoney1 = account1.getMoney();
    long expectedMoney2 = account2.getMoney();
    long expectedSumAllAccount = account1.getMoney() + account2.getMoney();

    initList(1, 1, 10).forEach(Thread::start);

    Thread.sleep(6000);
    assertEquals(expectedMoney1, account1.getMoney());
    assertEquals(expectedMoney2, account2.getMoney());
    assertEquals(expectedSumAllAccount, bank.getSumAllAccounts());
  }

  public void testIsValidate() throws InterruptedException {
    assertEquals(true, bank.isValidate(account1, account2, 10000));
  }

  public void testIsValidateInsufficientFounds() throws InterruptedException {
    account1.setMoney(0);
    assertEquals(false, bank.isValidate(account1, account2, 1));
  }

  public void testIsValidateIsBlockAcc() throws InterruptedException {
    account1.setBlock(true);
    account2.setBlock(true);
    assertEquals(false, bank.isValidate(account1, account2, 1));
  }

  public void testGetSumAllAccounts() {
    long expected = account1.getMoney() + account2.getMoney();
    assertEquals(expected, bank.getSumAllAccounts());
  }

  public List<Thread> initList(long amountOfAccount1, long amountOfAccount2, int count) {
    List<Thread> threads = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      threads.add(new Thread(() -> {
        try {
          bank.transfer(account1.getAccNumber(), account2.getAccNumber(), amountOfAccount1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }));
      threads.add(new Thread(() -> {
        try {
          bank.transfer(account2.getAccNumber(), account1.getAccNumber(), amountOfAccount2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }));
    }
    return threads;
  }
}
