/**
 * Project Transactions
 * Created by End on окт., 2019
 */
public class Transaction extends Thread {

    Bank bank;
    Account account1;
    Account account2;
    long amount;

    public Transaction(Bank bank, Account account1, Account account2, long amount) {
        this.bank = bank;
        this.account1 = account1;
        this.account2 = account2;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (!account1.isBlock() && !account2.isBlock())
            if (amount > 50_000) {
                new LongTransaction(bank, account1, account2, amount).start();
            } else {
                account1.setMoney(account1.getMoney() - amount);
                account2.setMoney(account2.getMoney() + amount);
                System.out.println("Transaction complete");
            }
    }
}
