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
            //проверка на мошенничество
            if (bank.isFraud(fromAccount.getAccNumber(), toAccount.getAccNumber(), amount)) {
                fromAccount.setBlock(true); //устанавливаем блокировку на аккаунт отправителя
                toAccount.setBlock(true);//устанавливаем блокировку на аккаунт получателя
                System.out.println("Transaction from " + fromAccount + " to " + toAccount + " is canceled");
            } else { //если проверка прошла то продолжаем операцию
                System.out.println("Verification was successful. The transaction from "
                        + fromAccount + " to " + toAccount + " in the amount " + amount);
                bank.withdrawal(fromAccount.getAccNumber(), amount); //снимаем средства со счета отправителя
                if (fromAccount.isCanSpend()) { //проверяем достаточно ли средств на счете отправителя
                    bank.replehnishment(toAccount.getAccNumber(), amount); // пополняем счет получателя
                    System.out.println("Transaction " + fromAccount +
                            toAccount + " complete");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
