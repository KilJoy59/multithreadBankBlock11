import java.util.HashMap;
import java.util.Random;

public class Bank
{
    private HashMap<String, Account> accounts;
    private final Random random = new Random();

    public Bank() {
        accounts = new HashMap<>();
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException
    {
        Thread.sleep(1000);
        boolean rand = random.nextBoolean();
        return rand;
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount)  {
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);
        new Transaction(this, fromAccount, toAccount, amount).start();
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum)
    {
        Account account = accounts.get(accountNum);
        System.out.println("Account " + accountNum + " balance: " + account.getMoney());
        return account.getMoney();
    }



    public void addAccount(String accountNum, Account account) {
        accounts.put(accountNum, account);
    }

    public synchronized void replehnishment (String accountNum, long money) {
        Account account = accounts.get(accountNum);
        if (account.isBlock()) {
            System.out.println(accountNum + " " + account.getBlockMessage());
        } else {
            System.out.println( "Replenishment " + accountNum + " in the amount of " + money);
            account.setMoney(account.getMoney() + money);
        }
    }

    public synchronized void withdrawal (String accountName, long money) {
        Account account = accounts.get(accountName);
        if (account.isBlock()) {
            System.out.println(accountName + " " + account.getBlockMessage());
        } else if (account.getMoney() < money) {
                account.setCanSpend(false);
                System.out.println("Not enough money in " + accountName + " bank account." +
                        " Operation canceled");
            } else {
                if (account.isCanSpend())
                    System.out.println("Withdrawal from " + accountName + " " + money);
            account.setMoney(account.getMoney() - money);
        }
    }

}
