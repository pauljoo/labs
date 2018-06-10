package info.xpanda.poi.word;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class DocxToHtml extends AbstractWordToHtml{

    public static String docxToHtml(String fileName) {
        try (InputStream in = new FileInputStream("D:\\" + fileName)) {
            XWPFDocument document = new XWPFDocument(in);
            System.out.println(handlerString(document));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String handlerString(IBody bodyElement){
        StringBuffer sb = new StringBuffer();
        for (IBodyElement iBodyElement : bodyElement.getBodyElements()) {
            if (iBodyElement instanceof XWPFParagraph) {
                //读取段落
                XWPFParagraph paragraph = (XWPFParagraph) iBodyElement;

                sb.append(PARAGRAPH_BEGIN_BEGIN);
                sb.append(STYLE_BEGIN);
                sb.append(PARAGRAPH_STYLE);
                if(paragraph.getFirstLineIndent() > 0){
                    sb.append(FIRST_LINE_INDENT);
                }
                sb.append(STYLE_END);
                sb.append(PARAGRAPH_BEGIN_END);

                for (IRunElement runElement : paragraph.getIRuns()) {
                    XWPFRun run = (XWPFRun) runElement;
                    /*
                     * 解析图片
                     */
                    for (XWPFPicture picture : run.getEmbeddedPictures()) {
                        if (paragraph.getDocument() != null) {
                            XWPFPictureData data = picture.getPictureData();
                            try (FileOutputStream fileOutputStream = new FileOutputStream("D:\\test\\" + data.getFileName())) {
                                fileOutputStream.write(data.getData());
                                sb.append("<img src='");
                                sb.append("D:\\test\\" + data.getFileName());
                                sb.append("'/><br/>");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    sb.append(getText(run.text(), run.getColor(), run.isBold(), run.isItalic(), run.getUnderline() != UnderlinePatterns.NONE));
                }
                sb.append(PARAGRAPH_END);
            }

            if (iBodyElement instanceof XWPFTable) {
                XWPFTable table = (XWPFTable) iBodyElement;
                StringBuffer sbTable = new StringBuffer();

                sbTable.append(TABLE_BEGIN);
                for (XWPFTableRow row : table.getRows()) {
                    sbTable.append("<tr>");
                    for (XWPFTableCell cell : row.getTableCells()) {
                        sbTable.append("<td>");
                        sbTable.append(handlerTableString(cell));
                        sbTable.append("</td>");
                    }
                    sbTable.append("</tr>");
                }
                sbTable.append(TABLE_END);
                sb.append(sbTable.toString());
            }
        }
        return sb.toString();
    }
    private static String handlerTableString(IBody bodyElement){
        StringBuffer sb = new StringBuffer();
        for (IBodyElement iBodyElement : bodyElement.getBodyElements()) {
            if (iBodyElement instanceof XWPFParagraph) {
                XWPFParagraph paragraph = (XWPFParagraph) iBodyElement;

                for (IRunElement runElement : paragraph.getIRuns()) {
                    XWPFRun run = (XWPFRun) runElement;
                    for (XWPFPicture picture : run.getEmbeddedPictures()) {
                        if (paragraph.getDocument() != null) {
                            XWPFPictureData data = picture.getPictureData();
                            try (FileOutputStream fileOutputStream = new FileOutputStream("D:\\test\\" + data.getFileName())) {
                                fileOutputStream.write(data.getData());
                                sb.append("<img src='");
                                sb.append("D:\\test\\" + data.getFileName());
                                sb.append("'/><br/>");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    sb.append(getText(run.text(), run.getColor(), run.isBold(), run.isItalic(), run.getUnderline() != UnderlinePatterns.NONE));
                }
            }
        }
        return sb.toString();
    }
}
