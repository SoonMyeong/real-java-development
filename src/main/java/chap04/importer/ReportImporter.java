package chap04.importer;

import chap04.Document;
import chap04.TextFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;


class ReportImporter implements Importer {
    private static final String NAME_PREFIX = "Patient: ";

    @Override
    public Document importFile(final File file) throws IOException {
        final TextFile textFile = new TextFile(file);
        textFile.addLineSuffix(NAME_PREFIX, Attributes.PATIENT);
        textFile.addLines(2, line -> false, Attributes.BODY);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(Attributes.TYPE, "REPORT");
        return new Document(attributes);
    }
}
