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
        //Поверяем аккаунты, заблокированы или нет
        if (!fromAccount.isBlock() && !toAccount.isBlock()) { //если перевод свыше 50_000
            // то создаем тразакцию для больших сумм в потоке для дальнейшей проверки на мошенничество
            if (amount > 50_000) {
                new LongTransaction(bank, fromAccount, toAccount, amount).start();
            } else { //все что ниже 50_000 проводим без проверки на мошенничество
                System.out.println("the transaction from "
                        + fromAccount + " to " + toAccount + " in the amount " + amount);
                bank.withdrawal(fromAccount.getAccNumber(), amount); //снимаем деньги со счета отправителя
                if (fromAccount.isCanSpend()) { //проверяем достаточно ли денег на счете на аккаунте отправителя
                    bank.replehnishment(toAccount.getAccNumber(), amount); //если да, то пополняем счет получателя
                    System.out.println("Transaction from " + fromAccount + " " + toAccount + " complete");
                }

            }
        }
    }
}
