package os;

public class ThreadTest {

    public static void main(String[] args) {
        System.out.println("my pid is: " + ProcessHandle.current().pid());

        Thread thread1 = new MyThread(1);
        Thread thread2 = new MyThread(2);
        Thread thread3 = new MyThread(3);
        thread1.start();
        thread2.start();
        thread3.start();

    }
}

class MyThread extends Thread {

    int n;

    public MyThread(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        System.out.println(n + " my thread id: " + this.threadId() + ". run! ");
        System.out.println(n + " my thread pid: " + ProcessHandle.current().pid());
    }
}
