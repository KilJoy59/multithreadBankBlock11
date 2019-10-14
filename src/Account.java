public class Account
{
    private long money;
    private String accNumber;
    private boolean isBlock = false;
    private String blockMessage = "Account is block";
    private boolean canSpend = true;

    public Account(String accNumber) {
        this.accNumber = accNumber;
    }

    public synchronized  long getMoney() {
            return money;
    }

    public synchronized  void setMoney(long money) {
        if (canSpend) {
            this.money = money;
        }
    }

    public String getAccNumber() {
        return accNumber;
    }

    /*public void setAccNumber(String accNumber) {
        if (!isBlock) {
            this.accNumber = accNumber;
        }
    }*/

    public synchronized  boolean isBlock() {
        return isBlock;
    }

    public synchronized  void setBlock(boolean block) {
        isBlock = block;
    }

    public synchronized String getBlockMessage() {
        return blockMessage;
    }

/*    public void setBlockMessage(String blockMessage) {
        this.blockMessage = blockMessage;
    }*/

    public void setCanSpend(boolean canSpend) {
        this.canSpend = canSpend;
    }

    public boolean isCanSpend() {
        return canSpend;
    }

    @Override
    public String toString() {
        return accNumber;
    }
}
