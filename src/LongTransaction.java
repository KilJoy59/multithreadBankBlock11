/**
 * Project Transactions
 * Created by End on окт., 2019
 */
public class LongTransaction extends Thread
{
    Bank bank;
    Account fromAccount;
    Account toAccount;
    long amount;

    public LongTransaction ( Bank bank, Account fromAccount, Account toAccount, long amount)
    {
        this.bank = bank;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public void run()
    {
        System.out.println("the transaction is sent to the security service for verification");
        try {
            if (bank.isFraud(fromAccount.getAccNumber(), toAccount.getAccNumber(), amount)) {
                fromAccount.setBlock(true);
                toAccount.setBlock(true);
                System.out.println("Transaction from " + fromAccount + " to " + toAccount + " is canceled");
            } else {
                System.out.println("Verification was successful. The transaction from "
                        + fromAccount + " to " + toAccount + " in the amount " + amount);
                bank.withdrawal(fromAccount.getAccNumber(), amount);
                if (fromAccount.isCanSpend()) {
                    bank.replehnishment(toAccount.getAccNumber(), amount);
                    System.out.println("Transaction " + fromAccount +
                            toAccount + " complete");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
