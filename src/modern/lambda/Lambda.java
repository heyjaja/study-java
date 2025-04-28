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
        process((Runnable) () -> System.out.println("Hello world 3"));

        // 다음 코드의 문제 해결
        // Object o = () -> {System.out.println("Tricky example"); };
        Runnable r = () -> System.out.println("Tricky example");

        // 캐스트로 대상 형식 명시
        process((Action) () ->{});

        // 람다 캡처링: 자유 변수(외부에서 정의된 변수) 활용 - final 변수
        // 원래 변수에 접근을 허용하는 것이 아니라 자유 지역 변수의 복사본 사용
        int portNumber = 1339;
        Runnable rr = () -> System.out.println(portNumber);
        // portNumber = 191; -> error 발생
    }

    public static void process(Runnable r) {
        r.run();
    }

    public static void process(Action action) {
        action.act();
    }

    @FunctionalInterface
    interface Action {
        void act();
    }
}
