package chap02.refactoring.biz;

import chap02.refactoring.domain.BankTransaction;

import chap02.refactoring.domain.SummaryStatistics;
import chap02.refactoring.export.Exporter;
import chap02.refactoring.parser.BankStatementParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankTransactionAnalyzer {

    private static final String RESOURCES = "src/main/resources/";

    public void analyze(final String fileName,
                        final BankStatementParser bankStatementParser,
                        final Exporter exporter) throws IOException {

        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        final SummaryStatistics summaryStatistics = bankStatementProcessor.summarizeTransactions();

        System.out.println(exporter.export(summaryStatistics));

    }

}
