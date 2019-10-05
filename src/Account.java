public class Account
{
    private long money;
    private String accNumber;
    private boolean isBlock = false;
    private String blockMessage = "Account is block";

    public Account(String accNumber) {
        this.accNumber = accNumber;
    }

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

    public String getBlockMessage() {
        return blockMessage;
    }

    public void setBlockMessage(String blockMessage) {
        this.blockMessage = blockMessage;
    }

}
