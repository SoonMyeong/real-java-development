package chap02.refactoring;

import chap02.refactoring.domain.BankTransaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankTransactionAnalyzer {

    private static final String RESOURCES = "src/main/resources/";
    private static final BankStatementCSVParser bankStatementParser
            = new BankStatementCSVParser();

    public static void main(String[] args) throws IOException {

        final String fileName = args[0];
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }

    //TODO. 결과 리포트 메서드 main 함수 없는 클래스로 분리 시켜야 함
    private static void collectSummary(BankStatementProcessor bankStatementProcessor) {
        System.out.println("Total for all transaction :" + bankStatementProcessor.calculateTotalAmount());
        System.out.println("Total for transaction in January :" + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("Total for transaction in February :" + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
        System.out.println("Total salary received :" + bankStatementProcessor.calculateTotalForCategory("Salary"));
    }
}
