package modern.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static modern.apple.Data.menu;

public class Reducing {
    public static void main(String[] args) {
        // 리듀싱 - 모든 스트림 요소를 처리해서 값으로 도출(함수형 프로그래밍에서는 폴드라고 함)
        List<Integer> numbers = Arrays.asList(4, 5, 3, 9);
        int sum = 0;
        for(int x : numbers) {
            sum += x;
        }
        System.out.println("sum = " + sum);
        // 초기값 0, 두 요소를 조합해서 새로운 값을 만드는 BinaryOperator<T>
        // 초기값부터 연산하여 누적 값을 다음 연산의 인수로 호출
        // 초기값이 없는 경우 Optional 반환 - 결과 없음
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println("sum2 = " + sum2);
        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println("product = " + product);

        // 최댓값, 최솟값
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println("max = " + max);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        System.out.println("min = " + min);

        // map과 reduce를 이용해 스트림의 요리 개수
        int count = menu.stream()
                .map(d -> 1)
                .reduce(0, Integer::sum);
        System.out.println("count = " + count);
        long count2 = menu.stream().count();
        System.out.println("count2 = " + count2);

    }
}
