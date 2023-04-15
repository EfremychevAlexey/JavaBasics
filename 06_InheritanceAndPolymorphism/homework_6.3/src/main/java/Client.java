public abstract class Client {

  private double balance;

  public double getAmount() {
    return (double) Math.round(balance * 100) / 100;
  }

  public void put(double amountToPut) {
    if (amountToPut > 0) {
      balance = balance + amountToPut;
    }
  }

  public void take(double amountToTake) {
    if (balance >= amountToTake) {
      balance = balance - amountToTake;
    }
  }

}
