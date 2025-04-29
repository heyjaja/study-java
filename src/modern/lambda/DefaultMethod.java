package modern.lambda;

import modern.apple.Apple;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;
import static modern.apple.Color.RED;
import static modern.ch2.FilteringApples.Color.GREEN;

public class DefaultMethod {

    public static void main(String[] args) {
        List<Apple> inventory = Apple.createInventory();

        // Comparator
        // 역정렬
        inventory.sort(comparing(Apple::getWeight).reversed());
        // 연결(무게-내림차순, 무게가 같으면 국가별로 정렬)
        inventory.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));

        // Predicate
        Predicate<Apple> redApple = a -> a.getColor() == RED;
        // 기존 프레디케이트 객체 결과를 반전
        Predicate<Apple> notRedApple = redApple.negate();
        // and
        Predicate<Apple> redAndHeavyApple = redApple
                .and(apple -> apple.getWeight() > 150);
        // or
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple
                .and(apple -> apple.getWeight() > 150)
                .or(apple -> GREEN.equals(apple.getColor()));
        // a.or(b).and(c) => (a || b) && c


        // Function
        // andThen: 주어진 함수를 먼저 적용한 결과를 다른 함수의 입력으로 전달하는 함수 반환
        // compose: 인수로 주어진 함수를 먼저 실행한 다음 그 결과를 외부 함수의 인수로 제공

        // andThen
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h1 = f.andThen(g);
        int result1 = h1.apply(1); // 4 => f -> g
        System.out.println("result1 = " + result1);
        // compose
        Function<Integer, Integer> h2 = f.compose(g); // f(g(x)) or (f o g)(x)
        int result2 = h2.apply(1); // 3 => g -> f
        System.out.println("result2 = " + result2);

        Function<String, String> addHeader = Letter::addHeader;
        // addHeader -> checkSpelling -> addFooter
        Function<String, String> transformationPipeline = addHeader
                .andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
        System.out.println("transformationPipeline = " + transformationPipeline.apply("labda"));
    }

    public class Letter {
        public static String addHeader(String text) {
            return "FROM Raoul, Mario and Alan: " + text;
        }

        public static String addFooter(String text) {
            return text + " Kind regards";
        }

        public static String checkSpelling(String text) {
            return text.replaceAll("labda", "lambda");
        }
    }
}
