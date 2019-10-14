/**
 * Project Transactions
 * Created by End on окт., 2019
 */
public class Transaction extends Thread {

    Bank bank;
    Account fromAccount;
    Account toAccount;
    long amount;

    public Transaction(Bank bank, Account fromAccount, Account toAccount, long amount) {
        this.bank = bank;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (!fromAccount.isBlock() && !toAccount.isBlock()) {
            if (amount > 50_000) {
                new LongTransaction(bank, fromAccount, toAccount, amount).start();
            } else {
                System.out.println("the transaction from "
                        + fromAccount + " to " + toAccount + " in the amount " + amount);
                bank.withdrawal(fromAccount.getAccNumber(), amount);
                if (fromAccount.isCanSpend()) {
                    bank.replehnishment(toAccount.getAccNumber(), amount);
                    System.out.println("Transaction from " + fromAccount + " " + toAccount + " complete");
                }

            }
        }
    }
}
