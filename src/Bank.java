import java.util.HashMap;
import java.util.Random;

public class Bank
{
    private HashMap<String, Account> accounts; //список аккаунтов
    private final Random random = new Random();

    public Bank() {
        accounts = new HashMap<>();
    }

    //Метод проверки на мошеничество
    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException
    {
        Thread.sleep(1000);
        boolean rand = random.nextBoolean();
        return rand;
    }

    //Метод тразакции между аккаунтами
    public void transfer(String fromAccountNum, String toAccountNum, long amount)  {
        if (!fromAccountNum.equals(toAccountNum)) {
            Account fromAccount = accounts.get(fromAccountNum);
            Account toAccount = accounts.get(toAccountNum);
            new Transaction(this, fromAccount, toAccount, amount).start();//создаем транзакцию в потоке
        }
    }

    //Метод который возвращает остаток на счете
    public long getBalance(String accountNum)
    {
        Account account = accounts.get(accountNum);
        System.out.println("Account " + accountNum + " balance: " + account.getMoney());
        return account.getMoney();
    }

    //Метод добавления аккаунта в список аккаунтов банка
    public void addAccount(String accountNum, Account account) {
        accounts.put(accountNum, account);
    }

    //Пополнение счета аккаунта
    public synchronized void replehnishment (String accountNum, long money) {
        Account account = accounts.get(accountNum);
        if (account.isBlock()) {
            System.out.println(accountNum + " " + account.getBlockMessage()); //если акк заблокирован выводим сообщение
        } else {
            System.out.println( "Replenishment " + accountNum + " in the amount of " + money);
            account.setMoney(account.getMoney() + money);//если не заблокирован то пополняем счет аккаунта
        }
    }
    //снятие денег со счета аккаунта
    public synchronized void withdrawal (String accountName, long money) {
        Account account = accounts.get(accountName);
        //
        if (account.isBlock()) {
            System.out.println(accountName + " " + account.getBlockMessage());//если акк заблокирован выводим сообщение
        } else if (account.getMoney() < money) { //проверяем достаточно ли средств на аккаунте
                account.setCanSpend(false);
                System.out.println("Not enough money in " + accountName + " bank account." +
                        " Operation canceled");// если недостаточно то выводим сообщение
            } else {
                if (account.isCanSpend())
                    System.out.println("Withdrawal from " + accountName + " " + money);
            account.setMoney(account.getMoney() - money); //если достаточно - снимаем средства
        }
    }

}
