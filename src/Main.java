import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Project Transactions
 * Created by End on окт., 2019
 */
public class Main {
     public static List<String> keys = new ArrayList<>();
    public static void main(String[] args) {

        Bank bank = new Bank();
        //Создаем 100 аккаунтов и записываем в HashMap
        for (int i = 0; i < 100 ; i++) {
            bank.addAccount("account" + i, new Account("account" + i) );
            keys.add("account" + i);
            bank.replehnishment(getRandomKey(keys), getRandomCash());
        }
        //Вызываем 50 транзакций
        for (int i = 0; i <50 ; i++) {
            bank.transfer(getRandomKey(keys),getRandomKey(keys), getRandomCash());
        }


    }

    public static String getRandomKey ( List list) {
        Random random = new Random();
        return (String) list.get(random.nextInt(list.size()));
    }

    public static Long getRandomCash () {
        long range = 123456L;
        Random r = new Random();
        return (long)(r.nextDouble()*range);
    }
}
