/**
 * Project Transactions
 * Created by End on окт., 2019
 */
public class LongTransaction extends Thread
{
    Bank bank;
    Account account1;
    Account account2;
    long amount;

    public LongTransaction ( Bank bank, Account account1, Account account2, long amount)
    {
        this.bank = bank;
        this.account1 = account1;
        this.account2 = account2;
        this.amount = amount;
    }

    public void run()
    {
        System.out.println("the transaction is sent to the security service for verification");
        try {
            if (bank.isFraud(account1.getAccNumber(), account2.getAccNumber(), amount)) {
                account1.setBlock(true, "Not is Fraud");
                account2.setBlock(true, "Not is Fraud");
                System.out.println("transaction is canceled");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
