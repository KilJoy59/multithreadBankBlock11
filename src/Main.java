/**
 * Project Transactions
 * Created by End on окт., 2019
 */
public class Main {
    public static void main(String[] args) {

        Account vasyaAccount = new Account("123");
        Account edikAccount = new Account("321");


        Bank bank = new Bank();
        bank.addAccount(vasyaAccount.getAccNumber(),vasyaAccount);
        bank.addAccount(edikAccount.getAccNumber(),edikAccount);
        bank.refillAccount(vasyaAccount.getAccNumber(),150_000);
        bank.refillAccount(edikAccount.getAccNumber(),50_000);
        bank.transfer(vasyaAccount.getAccNumber(),edikAccount.getAccNumber(),10_000);


        bank.transfer(vasyaAccount.getAccNumber(),edikAccount.getAccNumber(),60_000);
        bank.getBalance(vasyaAccount.getAccNumber());
        bank.getBalance(edikAccount.getAccNumber());
    }
}
