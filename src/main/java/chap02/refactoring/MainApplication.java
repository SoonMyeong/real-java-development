package chap02.refactoring;

import chap02.refactoring.biz.BankTransactionAnalyzer;
import chap02.refactoring.export.Exporter;
import chap02.refactoring.export.HtmlExporter;
import chap02.refactoring.parser.BankStatementCSVParser;
import chap02.refactoring.parser.BankStatementParser;
import java.io.IOException;

public class MainApplication {

    public static void main(String[] args) throws IOException {
        final BankTransactionAnalyzer bankTransactionAnalyzer = new BankTransactionAnalyzer();
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();
        final Exporter exporter = new HtmlExporter();

        bankTransactionAnalyzer.analyze(args[0],bankStatementParser, exporter);
    }
}
