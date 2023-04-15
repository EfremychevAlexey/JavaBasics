public class Account {

  private long money;
  private String accNumber;
  private boolean block;


  public Account(long money, String accNumber) {
    this.money = money;
    this.accNumber = accNumber;
    block = false;
  }

  public long getMoney() {
    return money;
  }

  public void setMoney(long money) {
    this.money = money;
  }

  public String getAccNumber() {
    return accNumber;
  }

  public void setAccNumber(String accNumber) {
    this.accNumber = accNumber;
  }

  public boolean isBlock() {
    return block;
  }

  public void setBlock(boolean block) {
    this.block = block;
  }

  public int compareTo(Account toAcc) {
    return this.getAccNumber().compareTo(toAcc.getAccNumber());
  }
}
