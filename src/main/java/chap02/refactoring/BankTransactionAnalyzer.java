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

    public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {

        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }

    private void collectSummary(BankStatementProcessor bankStatementProcessor) {
        System.out.println("Total for all transaction :" + bankStatementProcessor.calculateTotalAmount());
        System.out.println("Total for transaction in January :" + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("Total for transaction in February :" + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
        System.out.println("Total salary received :" + bankStatementProcessor.calculateTotalForCategory("Salary"));
    }
}
