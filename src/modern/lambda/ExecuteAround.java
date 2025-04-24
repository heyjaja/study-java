package modern.lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {

    public static void main(String[] args) throws IOException {
        String result = processFile();
        System.out.println("result = " + result);
        System.out.println("===");
        String onLine = processFile(br -> br.readLine());
        System.out.println("onLine = " + onLine);
        String twoLines = processFile(br -> br.readLine() + br.readLine());
        System.out.println("twoLine = " + twoLines);
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }

    public static String processFile() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }

    @FunctionalInterface
    public interface BufferedReaderProcessor {
        // 함수형 추상 메서드의 시그니처를 함수 디스크립터(function descriptor)라고 함
        String process(BufferedReader b) throws IOException;
    }
}
