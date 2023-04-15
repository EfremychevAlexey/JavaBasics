import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {

  private static final Random random = new Random();
  private Map<String, Account> accounts = new HashMap<>();

  public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
      throws InterruptedException {
    Thread.sleep(1000);
    return random.nextBoolean();
  }

  /**
   * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
   * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
   * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
   * усмотрение)
   */
  public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount)
      throws InterruptedException {
    Account fromAcc = getAccount(fromAccountNum);
    Account toAcc = getAccount(toAccountNum);

    synchronized (fromAcc.compareTo(toAcc) > 0 ? fromAcc : toAcc) {
      synchronized (fromAcc.compareTo(toAcc) > 0 ? toAcc : fromAcc) {
        if (isValidate(fromAcc, toAcc, amount)) {
          long initialBalanceFromAcc = fromAcc.getMoney();
          long initialBalanceToAcc = toAcc.getMoney();
          long finalBalanceFromAcc = initialBalanceFromAcc - amount;
          long finalBalanceToAcc = initialBalanceToAcc + amount;
          long sumAllAcc = getSumAllAccounts();

          fromAcc.setMoney(finalBalanceFromAcc);
          toAcc.setMoney(finalBalanceToAcc);

          if (fromAcc.getMoney() == finalBalanceFromAcc && toAcc.getMoney() == finalBalanceToAcc
              && sumAllAcc == getSumAllAccounts()) {
            System.out.println("Перевод выполнен");
          }
        }
      }
    }
  }

  public boolean isValidate(Account fromAcc, Account toAcc, long amount)
      throws InterruptedException {
    if (amount > 50000) {
      if (isFraud(fromAcc.getAccNumber(), toAcc.getAccNumber(), amount)) {
        fromAcc.setBlock(true);
        toAcc.setBlock(true);
        System.out.println("Счета заблокированы.");
        return false;
      }
    }
    if (fromAcc.isBlock() || toAcc.isBlock()) {
      System.out.println("Операция не может быть выполнена. Стоит блокировка");
      return false;
    }
    if (amount <= 0 || fromAcc.getMoney() <= amount) {
      System.out.println("Не достаточно средств");
      return false;
    }
    return true;
  }

  public void addAccount(Account account) {
    accounts.put(account.getAccNumber(), account);
  }

  public Account getAccount(String account) {
    return accounts.get(account);
  }

  public Map<String, Account> getAccounts() {
    return accounts;
  }

  /**
   * TODO: реализовать метод. Возвращает остаток на счёте.
   */

  public long getSumAllAccounts() {
    return accounts.values().stream().mapToLong(account -> account.getMoney()).sum();
  }
}
