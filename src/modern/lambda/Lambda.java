package modern.lambda;

/*
메서드로 전달할 수 잇는 익명 함수를 단순화한 것
파라미터 리스트, 바디, 반환 형식, 예외 리스트 포함
- 익명: 이름이 없음
- 함수: 특정 클래스에 종속되지 않음
- 전달: 메서드 인수로 전달하거나 변수로 저장 가능
- 간결성: 익명 클래스보다 간결
 */
public class Lambda {

    public static void main(String[] args) {
        // 함수형 인터페이스: 하나의 추상 메서드를 가지는 인터페이스 - @FunctionalInterface 어노테이션을 가짐
        Runnable r1 = () -> System.out.println("Hello World 1");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World 2");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println("Hello world 3"));
    }

    public static void process(Runnable r) {
        r.run();
    }

}
