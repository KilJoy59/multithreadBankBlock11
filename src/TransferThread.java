/**
 * Project Transactions
 * Created by End on окт., 2019
 */
public class TransferThread implements Runnable {

    private String fromAccountName;
    private String toAccountName;
    private long amount;

    public TransferThread(String fromAccountName, String toAccountName, long amount) {
        this.fromAccountName = fromAccountName;
        this.toAccountName = toAccountName;
        this.amount = amount;

    }

    @Override
    public void run() {
            Main.bank.transfer(fromAccountName, toAccountName, amount);
    }
}
