import java.util.Random;
import java.util.concurrent.*;

/**
 * Project Transactions
 * Created by End on окт., 2019
 */
public class Main {
     static Bank bank;
     static Semaphore semaphore;
    public static void main(String[] args) {
        bank = Bank.getInstance();
            semaphore = new Semaphore(10);
            ExecutorService executorService = Executors.newFixedThreadPool(100);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <100 ; i++) {
            executorService.submit(new TransferThread("account" + getRandomInt(),
                    "account" + getRandomInt(), getRandomCash()));
        }
        semaphore.release();
            shutdownAndAwaitTermination(executorService);
    }

    //Метод получения случайного числа типа Long от 0 до 150_000
    public static Long getRandomCash () {
        long range = 150_000L;
        Random r = new Random();
        return (long)(r.nextDouble()*range);
    }

    public static int getRandomInt() {
        int max = 1000;
        return (int)(Math.random()*++max);
    }

    private static void shutdownAndAwaitTermination(ExecutorService pool) {
        //отключаем новые задачи которые были отправлены
        pool.shutdown();
        try {
            //ждем завершения существующих задач
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                //отменяем текущие задачи
                pool.shutdownNow();
                //ждем реакцию на отмену
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // повторяем
            pool.shutdownNow();
            // сохраянем статус
            Thread.currentThread().interrupt();
        }
    }
}
