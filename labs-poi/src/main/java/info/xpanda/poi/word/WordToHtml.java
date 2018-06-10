package info.xpanda.poi.word;

public class WordToHtml {

    public String wordToHtml(String fileName) {
        if (null == fileName)
            return null;

        String upperFileName = fileName.toUpperCase();
        if (upperFileName.endsWith(".DOC")) {
            return DocToHtml.docToHtml(fileName);
        } else if (upperFileName.endsWith(".DOCX")) {
            return DocxToHtml.docxToHtml(fileName);
        }
        return null;
    }

    public static void main(String[] args) {
        WordToHtml w2h = new WordToHtml();
//        w2h.wordToHtml("test.doc");
//        w2h.wordToHtml("test.docx");
        w2h.wordToHtml("test.docx");
    }
}
