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
        for (int i = 0; i < 100 ; i++) {
            //Создаем 100 аккаунтов и записываем в список аккаунтов банка
            bank.addAccount("account" + i, new Account("account" + i) );
            keys.add("account" + i); //заполняем список названий аккаунтов
            //пополняем счет случайных акаунтов случайной суммой
            bank.replehnishment(getRandomKey(keys), getRandomCash());
        }

        for (int i = 0; i <100 ; i++) {
            //Вызываем 100 транзакций со случайной суммой перевода
            bank.transfer(getRandomKey(keys),getRandomKey(keys), getRandomCash());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
    //Метод получания случайного аккаунта
    public static String getRandomKey ( List list) {
        Random random = new Random();
        return (String) list.get(random.nextInt(list.size()));
    }

    //Метод получения случайного числа типа Long от 0 до 150_000
    public static Long getRandomCash () {
        long range = 150_000L;
        Random r = new Random();
        return (long)(r.nextDouble()*range);
    }
}
