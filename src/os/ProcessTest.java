package os;

public class ProcessTest {
    public static void main(String[] args) {
        System.out.println("hello, os!");
        System.out.println("parent process: " + ProcessHandle.current().pid());
    }
}
