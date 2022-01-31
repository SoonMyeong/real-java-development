package chap04.importer;

import chap04.Document;
import chap04.TextFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static chap04.importer.Attributes.TYPE;

class LetterImporter implements Importer {
    private static final String NAME_PREFIX = "Dear ";

    // tag::importFile[]
    @Override
    public Document importFile(final File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, Attributes.PATIENT);

        final int lineNumber = textFile.addLines(2, String::isEmpty, Attributes.ADDRESS);
        textFile.addLines(lineNumber + 1, (line) -> line.startsWith("regards,"), Attributes.BODY);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "LETTER");
        return new Document(attributes);
    }
    // end::importFile[]
}
