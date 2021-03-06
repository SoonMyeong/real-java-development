package chap02.refactoring.export;

import chap02.refactoring.domain.SummaryStatistics;
import chap02.refactoring.export.Exporter;

public class HtmlExporter implements Exporter
{
    @Override
    public String export(SummaryStatistics summaryStatistics)
    {
        String result = "<!Doctype html>";
        result += "<html lang=en>";
        result += "<head><title> Bank Transaction Report </title></head>";
        result += "<body>";
        result += "<ul>";
        result += "<li><strong>The sum is</strong>:" + summaryStatistics.getSum() + "</li>";
        result += "<li><strong>The average is</strong>:" + summaryStatistics.getAverage() + "</li>";
        result += "<li><strong>The max is</strong>:" + summaryStatistics.getMax() + "</li>";
        result += "<li><strong>The min is</strong>:" + summaryStatistics.getMin() + "</li>";
        result += "</ul>";
        result += "</body>";
        result += "</html>";

        return result;
    }
}
