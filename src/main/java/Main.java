import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        Scanner scanner = new Scanner(System.in);
        // do wystartowania
        ScheduledWorker worker;
        ScheduledFuture future = null;
        String komenda;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        do {
            komenda = scanner.nextLine();

            if (komenda.equalsIgnoreCase("start") && future == null) {
                System.out.println(LocalDateTime.now().format(formatter));
                worker = new ScheduledWorker();
                future = pool.scheduleAtFixedRate(worker, 0, 1, TimeUnit.SECONDS);
                System.out.println("Started.");
            } else if (komenda.equalsIgnoreCase("s") && future != null) {
                future.cancel(true);
                future = null;
                System.out.println("Stopped.");
            } else if (!komenda.equalsIgnoreCase("quit")) {
                System.out.println("Nie poznaje komendy.");
            }
        } while (!komenda.equalsIgnoreCase("q"));
        // do zatrzymania:
        pool.shutdown();
//        pool.awaitTermination(5, TimeUnit.SECONDS);
    }
}

