package info.xpanda.poi.word;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;

public class AbstractWordToHtml {
    protected final static String STYLE_BEGIN = "style='";
    protected final static String STYLE_END = "'";

    protected final static String PARAGRAPH_BEGIN_BEGIN = "<div ";
    protected final static String PARAGRAPH_BEGIN_END = ">";

    protected final static String PARAGRAPH_STYLE = "margin-top:10px;margin-bottom:10px;";

    protected final static String PARAGRAPH_END = "</div>";

    protected final static String FIRST_LINE_INDENT = "text-indent: 28px;";

    protected final static String BOLD = "font-weight:bold;";

    protected final static String COLOR_BEGIN = "color:";

    protected final static String COLOR_END = ";";

    protected final static String UNDERLINE = "text-decoration: underline;";

    protected final static String ITALIC = "font-style: italic;";

    protected final static String TABLE_BEGIN = "<table style='border: 1px solid #000;' border='1' cellspacing='1' cellpadding='0'>";

    protected final static String TABLE_END = "</table>";

    protected static String getStyle(String... styles){
        StringBuffer sb = new StringBuffer();
        sb.append(STYLE_BEGIN);
        for(String style : styles){
            sb.append(style);
        }
        sb.append(STYLE_END);
        return sb.toString();
    }

    protected static String getText(String text, String color, boolean isBold, boolean isItalic, boolean isUnderline){
        if(color == null && !isBold && !isItalic && !isUnderline){
            return text;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("<font ");
        sb.append(STYLE_BEGIN);
        if(color != null){
            sb.append(COLOR_BEGIN);
            sb.append(color);
            sb.append(COLOR_END);
        }
        if(isBold){
            sb.append(BOLD);
        }
        if(isItalic){
            sb.append(ITALIC);
        }
        if(isUnderline) {
            sb.append(UNDERLINE);
        }
        sb.append(STYLE_END);
        sb.append(">");
        sb.append(text);
        sb.append("</font>");
        return sb.toString();
    }
}
