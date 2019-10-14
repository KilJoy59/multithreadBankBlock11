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
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount)  {
        System.out.println("the transaction from "
                + fromAccountNum + " to " + toAccountNum + " in the amount " + amount);
        Account account1 = accounts.get(fromAccountNum);
        Account account2 = accounts.get(toAccountNum);
        new Transaction(this, account1, account2, amount).start();
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum)
    {
        Account account = accounts.get(accountNum);
        return account.getMoney();
    }



    public void addAccount(String accountNum, Account account) {
        accounts.put(accountNum, account);
    }

    public void withdrawCash (String accountNum, long money) {
        Account account = accounts.get(accountNum);
        if (account.isBlock()) {
            System.out.println( account.getBlockMessage());
        } else {
            account.setMoney(account.getMoney() - money);
        }
    }

    public void refillAccount (String accountNum, long money) {
        Account account = accounts.get(accountNum);
        if (account.isBlock()) {
            System.out.println(account.getBlockMessage());
        } else {
            account.setMoney(account.getMoney() + money);
        }
    }

}
