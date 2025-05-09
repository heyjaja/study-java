# 람다식
### 메서드 참조
* 가독성 향상
* (::) 구분자 사용
```java
Thread.currentTread().dumpStack() => Thread.currentThread()::dumpStack
(str, i) -> str.substring(i) => String::substring
```
1. 정적 메서드 참조
```text
Integer::parseInt
```
2. 다양한 형식의 인스턴스 메서드 참조
```text
String::length
```
3. 기존 객체의 인스턴스 메서드 참조   
```java
Transaction expensiveTransaction new Transaction();
expensiveTransaction::getValue
```

람다 표현식을 메서드 참조로 바꾸는 방법
1. 
```text
(args) -> ClassName.staticMethod(args)
ClassName::staticMethod
```
2. 
```text
(arg0, rest) -> arg0.instanceMethod(rest)
ClassName::instanceMethod
```
3. 
```text
(args) -> expr.instanceMethod(args)
expr::instanceMethod
```

### 생성자 참조
```text
ClassName::new
```
1. 디폴트 생성자
```java
Supplier<Apple> c1 = Apple::new;
Apple a1 = c1.get();
```
* Supplier의 get 메서드를 호출해 새로운 Apple 객체 생성
2. 인자가 1개인 생성자
```java
Function<Integer, Apple> c2 = Apple::new;
Apple a2 = c2.apply(110);
```
* Apple(Integer weight) 생성자
* apply 메서드에 무게를 인수로 호출하여 새로운 객체 생성

3. 인자가 2개인 생성자
```java
BiFunction<Integer, Color, Apple> c3 = Apple::new;
Apple a3 = c3.apply(170, BLUE);
```
* Apple(Integer weight, Color color) 생성자

4. 인자가 3개인 생성자
```java
public interface TripleFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

TripleFunction<Integer, Integer, Integer, Color> colorFactory = Color::new;
```
* Color(int, int, int)
* 생성자 참조와 일치하는 시그니처를 갖는 함수형 인터페이스를 정의하여 사용

### 람다 표현식 정리
* 익명 함수의 일종 - 파라미터 리스트, 바디, 반환 형식을 가지며 예외를 던질 수 있음
* 함수형 인터페이스는 하나의 추상 메서드만을 정의하는 인터페이스
* 람다 표현식 전체가 함수형 인터페이스의 인스턴스
* 실행 어라운드 패턴을 이용하면 유연성과 재사용성 향상