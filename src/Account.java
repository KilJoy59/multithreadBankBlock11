import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Account
{
    private long money;
    private String accNumber;
    private AtomicBoolean isBlock = new AtomicBoolean(false);
    private String blockMessage = "Account is block";
    private AtomicBoolean canSpend = new AtomicBoolean(true);

    public Account(String accNumber , long money) {
        this.accNumber = accNumber;
        this.money = money;
    }

    public long getMoney() {
            return money;
    }

    public void setMoney(long money) {
        if (canSpend.get()) {
            this.money = money;
        }
    }

    public String getAccNumber() {
        return accNumber;
    }

    public String getBlockMessage() {
        return blockMessage;
    }

    boolean isBlock() {
        return isBlock.get();
    }

    void blockAccount() {
       isBlock.set(true);
    }

    void unBlockAccount() {
        isBlock.set(false);
    }

    boolean isCanSpend() {
        return canSpend.get();
    }

    void setCantSpend() {
        canSpend.set(false);
    }

    void setCanSpend() {
        canSpend.set(true);
    }

    @Override
    public String toString() {
        return "Account number:" + accNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getAccNumber().equals(account.getAccNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccNumber());
    }
}
