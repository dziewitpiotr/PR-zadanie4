public class ScheduledWorker implements Runnable {
private int counter =1;

    @Override
    public void run() {
        System.out.println(counter++);
    }
}
