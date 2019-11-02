package util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;


 public class Utilities {
    // Constants for application icon.
    public static final String ICON_FILE_NAME = "/images/iconApplication.jpg";
    // Constants for connection.
    public static final String USER = "JUSDORGE";
    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String PASSWORD = "04021976";
    public static final String URL = "jdbc:mysql://localhost:3306/c1";
    public static final String URL_C = "jdbc:mysql://localhost:3306/b4";
    public static final String URL_TEST = "jdbc:mysql://localhost:3306/mysql";
    //Constants for default fonts
    static final String DEFAULT_FONT_NAME = "Tahoma";
    static final int DEFAULT_FONT_SIZE = 9;
    static final int DEFAULT_FONT_TYPE = Font.PLAIN;
    // Constants for table font's.
    static final String HEAD_FONT_NAME = "Times new roman";
    static final int HEAD_FONT_SIZE = 24;
    static final int HEAD_FONT_TYPE = Font.BOLD;
    static final String TABLE_FONT_NAME = "Times new roman";
    static final int TABLE_FONT_SIZE = 24;
    static final int TABLE_FONT_TYPE = Font.PLAIN;

    //titleLabel constants.
    static final String TITLE_LABEL_FONT_NAME = "ARIAL";
    static final int TITLE_LABEL_FONT_SIZE = 50;
    static final int TITLE_LABEL_FONT_TYPE = Font.BOLD;
    static final Color TITLE_LABEL_COLOR = Color.WHITE;
    static final Color TITLE_LABEL_BACKGROUND_COLOR =
                    Color.CYAN;
    static final Color SUBTITLE_LABEL_COLOR = Color.WHITE;
    static final Color SUBTITLE_LABEL_BACKGROUND_COLOR =
                    Color.BLUE;
    //Methods for Image ressoureces.
    public static Image setIconImage(JFrame frame){
        Image result = Toolkit.getDefaultToolkit().
                getImage(frame.getClass().getResource(ICON_FILE_NAME));

        return result;
    }
    public static Image setIconImage(JDialog frame){
        Image result = Toolkit.getDefaultToolkit().
                getImage(frame.getClass().getResource(ICON_FILE_NAME));

        return result;
    }

    //Methods dor fonts.
    static public Font headTablFont(){
            return new Font(HEAD_FONT_NAME, 
                            HEAD_FONT_TYPE, 
                            HEAD_FONT_SIZE);
    }
    static public Font tableFont(){
            return new Font(TABLE_FONT_NAME, 
                            TABLE_FONT_TYPE, 
                            TABLE_FONT_SIZE);
    }
    static public int fontHeight(){
            return HEAD_FONT_SIZE;
    }
    static public Font getTitleLabelFont(){
        return new Font(TITLE_LABEL_FONT_NAME,
                        TITLE_LABEL_FONT_TYPE,
                        TITLE_LABEL_FONT_SIZE);
    }
    static public int getTitleLabelFontHeight(){
        return TITLE_LABEL_FONT_SIZE;
    }
    static public Color getTitleLabelForeground(){
        return TITLE_LABEL_COLOR;
    }
    static public Color getTitleLabelBackGround(){
        return TITLE_LABEL_BACKGROUND_COLOR;
    }
    static public Color getSubTitleLabelForeground(){
        return SUBTITLE_LABEL_COLOR;
    }
    static public Color getSubTitleLabelBackGround(){
        return SUBTITLE_LABEL_BACKGROUND_COLOR;
    }
    static public Font getDefaultFont(){
        return new Font(DEFAULT_FONT_NAME,
                        DEFAULT_FONT_TYPE,
                        DEFAULT_FONT_SIZE);
    }
    static public int getDefaultHeight(){
        return DEFAULT_FONT_SIZE;
    }
    static public String revertDate(String date){
        String result = "";
        result += date.substring(6, 8);
        result += "-";
        result += date.substring(3, 5);
        result += "-";
        result += date.substring(0,2);
        return result;
    }
    
 }
