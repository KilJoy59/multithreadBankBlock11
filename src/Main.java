/**
 * Project Transactions
 * Created by End on окт., 2019
 */
public class Main {
    public static void main(String[] args) {

        Account vasyaAccount = new Account();
        vasyaAccount.setAccNumber("123");
        vasyaAccount.refillAccount(150_000);

        Account edikAccount = new Account();
        edikAccount.setAccNumber("321");
        vasyaAccount.refillAccount(50_000);

        Bank bank = new Bank();
        bank.addAccount(vasyaAccount.getAccNumber(),vasyaAccount);
        bank.addAccount(edikAccount.getAccNumber(),edikAccount);
        bank.transfer(vasyaAccount.getAccNumber(),edikAccount.getAccNumber(),10_000);


        bank.transfer(vasyaAccount.getAccNumber(),edikAccount.getAccNumber(),60_000);
        bank.getBalance(vasyaAccount.getAccNumber());
        edikAccount.refillAccount(1000);
        bank.getBalance(edikAccount.getAccNumber());
    }
}
