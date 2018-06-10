package info.xpanda.poi.word;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class DocToHtml extends AbstractWordToHtml{
    public static String docToHtml(String fileName) {
        try (InputStream in = new FileInputStream("D:\\" + fileName)) {
            HWPFDocument document = new HWPFDocument(in);
            Range r = document.getRange();
            PicturesTable pictureTable = document.getPicturesTable();

            StringBuffer sbContent = new StringBuffer();
            for (int i = 0; i < r.numParagraphs(); i++) {
                Paragraph p = r.getParagraph(i);
                i += handleParagraph(p, 0, r, pictureTable, sbContent);
            }
            System.out.println(sbContent.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int handleParagraph(Paragraph p, int parentTableLevel, Range r, PicturesTable pictureTable, StringBuffer sbContent){
        if (p.isInTable() && p.getTableLevel() > parentTableLevel
                && parentTableLevel == 0) {
            Table table = r.getTable(p);
            StringBuffer sbTable = new StringBuffer();
            sbTable.append(TABLE_BEGIN);
            for (int rn = 0; rn <table.numRows(); rn++) {
                TableRow row = table.getRow(rn);
                //tr
                sbTable.append("<tr>");
                for (int cn = 0; cn < row.numCells(); cn++) {
                    TableCell cell = row.getCell(cn);
                    //td
                    sbTable.append("<td>");
                    for (int pn = 0; pn < cell.numParagraphs(); pn++) {
                        Paragraph cellP = cell.getParagraph(pn);
                        sbTable.append(handleParagraphString(cellP, pictureTable));
                    }
                    //td
                    sbTable.append("</td>");
                }
                //tr
                sbTable.append("</tr>");
            }
            sbTable.append(TABLE_END);
            sbContent.append(sbTable.toString());
            return table.numParagraphs() - 1;
        }

        String content = handleParagraphString(p, pictureTable);
        sbContent.append(content);
        return 0;
    }

    private static String handleParagraphString(Paragraph p, PicturesTable pictureTable){
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < p.numCharacterRuns(); j++) {
            CharacterRun cr = p.getCharacterRun(j);
            if (pictureTable.hasPicture(cr)) {
                // Inline Picture
                Picture pic = pictureTable.extractPicture(cr, false);
                sb.append("<img src='");
                sb.append("D:\\test\\" + pic.suggestFullFileName());
                sb.append("'/><br/>");
                try(FileOutputStream fileOutputStream = new FileOutputStream("D:\\test\\" + pic.suggestFullFileName())){
                    fileOutputStream.write(pic.getContent());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            } else {
                if (cr.text().endsWith("\u0007")) {
                    // Strip the table cell end marker
                    sb.append(cr.text().substring(0, cr.text().length() - 1));
                }else{
                    sb.append(cr.text());
                }
            }
        }
        return sb.toString();
    }
}
