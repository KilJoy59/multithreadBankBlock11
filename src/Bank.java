import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class Bank {
    private ConcurrentHashMap<String, Account> accounts; //список аккаунтов
    private final Random random = new Random();
    private Semaphore semaphore = new Semaphore(10);

    static Bank getInstance() {
        Bank bank = new Bank();
        bank.init();
        return bank;
    }

    private void init() {
        Random r = new Random();
        accounts = new ConcurrentHashMap<>();
        for (int i = 0; i <= 1000 ; i++) {
            String accNumber = String.valueOf(100_000 + r.nextInt(900_000));
            long money = (long)(r.nextDouble()*150_000L);
            if (!accounts.containsValue(new Account(accNumber,money))) {
                accounts.put("account" + i, new Account(accNumber, money));
            }
        }
    }

    public ConcurrentHashMap<String, Account> getAccounts() {
        return accounts;
    }

    //Метод проверки на мошеничество
    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    //Метод тразакции между аккаунтами
    public void transfer(String fromAccountName, String toAccountName, long amount)  {
        if (!fromAccountName.equals(toAccountName)) { //проверка одинаковые аккаунты или нет
            Account fromAccount = accounts.get(fromAccountName);
            Account toAccount = accounts.get(toAccountName);
            //Поверяем аккаунты, заблокированы или нет
            if (!fromAccount.isBlock() && !toAccount.isBlock()) { //если перевод свыше 50_000
                // то создаем тразакцию для больших сумм в потоке для дальнейшей проверки на мошенничество
                if (amount > 50_000) {
                    fromAccount.blockAccount();
                    toAccount.blockAccount();
                    System.out.println("the transaction from " + fromAccount + " to "
                            + toAccount + " is sent to the security service for verification");
                    try {
                        //проверка на мошенничество
                        if (isFraud(fromAccount.getAccNumber(), toAccount.getAccNumber(), amount)) {
                            fromAccount.blockAccount(); //устанавливаем блокировку на аккаунт отправителя
                            toAccount.blockAccount();//устанавливаем блокировку на аккаунт получателя
                            System.out.println("Transaction from " + fromAccount + " to " + toAccount + " is canceled");
                        } else { //если проверка прошла то продолжаем операцию
                            fromAccount.unBlockAccount();
                            toAccount.unBlockAccount();
                            System.out.println("Verification was successful. The transaction from "
                                    + fromAccount + " to " + toAccount + " in the amount " + amount);
                            withdrawal(fromAccount.getAccNumber(), amount); //снимаем средства со счета отправителя
                                replehnishment(toAccount.getAccNumber(), amount); // пополняем счет получателя
                                System.out.println("Transaction " + fromAccount +
                                        toAccount + " complete");

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else { //все что ниже 50_000 проводим без проверки на мошенничество
                    System.out.println("the transaction from "
                            + fromAccount + " to " + toAccount + " in the amount " + amount);
                    withdrawal(fromAccount.getAccNumber(), amount); //снимаем деньги со счета отправителя
                    if (fromAccount.isCanSpend()) { //проверяем достаточно ли денег на счете на аккаунте отправителя
                        replehnishment(toAccount.getAccNumber(), amount); //если да, то пополняем счет получателя
                        System.out.println("Transaction from " + fromAccount + " " + toAccount + " complete");
                    }

                }
            }
        }
    }

    //Метод который возвращает остаток на счете
    public long getBalance(String accountNum)
    {
        return accounts.get(accountNum).getMoney();
    }

    //Пополнение счета аккаунта
    public void replehnishment (String accountNum, long money) {
        Account account = accounts.get(accountNum);
        if (account.isBlock()) {
            System.out.println(accountNum + " " + account.getBlockMessage()); //если акк заблокирован выводим сообщение
        } else {
            System.out.println( "Replenishment " + accountNum + " in the amount of " + money);
            account.setMoney(account.getMoney() + money);//если не заблокирован то пополняем счет аккаунта
        }
    }
    //снятие денег со счета аккаунта
    public void withdrawal (String accountName, long money) {
        Account account = accounts.get(accountName);
        //
        if (account.isBlock()) {
            System.out.println(accountName + " " + account.getBlockMessage());//если акк заблокирован выводим сообщение
        } else if (account.getMoney() < money) { //проверяем достаточно ли средств на аккаунте
                account.setCantSpend();
                System.out.println("Not enough money in " + accountName + " bank account." +
                        " Operation canceled");// если недостаточно то выводим сообщение
            } else {
                if (account.isCanSpend())
                    System.out.println("Withdrawal from " + accountName + " " + money);
            account.setMoney(account.getMoney() - money); //если достаточно - снимаем средства
        }
    }
}
