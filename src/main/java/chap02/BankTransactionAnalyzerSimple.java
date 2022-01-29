package chap02;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 *
 *  시작 자료
 *
 *  bank-data-simple.csv 를 읽어서 아래의 문제를 해결 해보자
 *
 *  1) 은행 입출금 내역의 총 수입과 총 지출은 각각 얼마인가? 결과가 양수인가? 음수인가?
 *  2) 특정 달엔 몇 건의 입출금 내역이 발생 했는가?
 *  3) 지출이 가장 높은 상위 10건은?
 *  4) 돈을 가장 많이 소비하는 항목은?
 *  2번 부터 해결할라면 벌써 코드 복붙 작업 들어가야 함... 아니면 루프 또 돌리던가
 *
 *  이 Simple 클래스를 SRP 원칙에 따라 리팩토링 해보기
 *  1. 입력 읽기
 *  2. 형식의 입력 파싱
 *  3. 결과 처리 (계산)
 *  4. 결과 요약 리포트
 *
 */

public class BankTransactionAnalyzerSimple {
    private static final String RESOURCES = "src/main/resources//";

    public static void main(String[] args) throws IOException {

        final Path path = Paths.get(RESOURCES + args[0]);
        final List<String> lines = Files.readAllLines(path);
        double total = 0;

        for(final String line : lines) {
            final String[] columns = line.split(",");
            final double amount = Double.parseDouble(columns[1]);
            total += amount;
        }

        System.out.println("Total transaction : " + total);

    }
}
