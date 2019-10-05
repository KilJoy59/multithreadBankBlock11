public class Account
{
    private long money;
    private String accNumber;
    private boolean isBlock = false;
    private String blockMessage = "Account is block";

    public long getMoney() {
        if (!isBlock) {
            return money;
        }
        System.out.println(blockMessage);
        return 0;
    }

    public void setMoney(long money) {
            this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        if (!isBlock) {
            this.accNumber = accNumber;
        }
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean block) {
        if (block) {
            System.out.println(accNumber + " " + blockMessage);
        }
        isBlock = block;
    }

    public void refillAccount ( long money) {
        if (isBlock) {
            System.out.println(blockMessage);
        } else {
            setMoney(getMoney() + money);
        }
    }

    public void withdrawCash ( long money) {
        if (isBlock) {
            System.out.println(blockMessage);
        } else {

            setMoney(getMoney() - money);
        }
    }
}
