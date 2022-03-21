package com.example.judgmentdoc.util.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class PdfUtil {

    private final static String FONT_PATH = "./src/main/resources/static/fonts/simsun.ttc,1";

    /**
     * 返回PDF流
     *
     * @param response 相应设置
     * @param pathName 水印文件路径和名称
     * @throws Exception 异常
     */
    public static void returnPdfStream(HttpServletResponse response, String pathName) throws Exception {
        response.setContentType("application/pdf");

        File file = new File(pathName);
        if (file.exists()) {
            FileInputStream in = new FileInputStream(file);
            OutputStream out = response.getOutputStream();
            byte[] b = new byte[1024 * 5];
            int n;
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }
            out.flush();
            in.close();
            out.close();
        }
    }

    /**
     * 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
     *
     * @param pdfPath     保存路径
     * @param document    文档
     * @param pdfPathName 文件保存路径和名称
     * @return 书写器(Writer)
     * @throws Exception
     */
    public static PdfWriter createPdfWriter(String pdfPath, Document document, String pdfPathName) throws Exception {

        //判断文件夹是否存在
        File file = new File(pdfPath);
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(pdfPathName);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        writer.setViewerPreferences(PdfWriter.PageModeUseThumbs);

        return writer;
    }

    /**
     * 设置第一标题内容
     *
     * @param title    第一标题
     * @param document 文档
     * @throws Exception 异常
     */
    public static void setPdfFirstTitle(String title, Document document) throws Exception {
        Paragraph paragraph = new Paragraph(title, getPdfChineseFont(0));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(2);
        document.add(paragraph);
    }

    /**
     * 设置正文内容
     *
     * @param text            正文内容
     * @param document        文档
     * @param alignment       对齐方式
     * @param firstLineIndent 第一行缩进
     * @param spacingAfter    之后间隔
     * @throws Exception
     */
    public static void setPdfMainBody(String text, Document document, int alignment, int firstLineIndent, int spacingAfter) throws Exception {
        Paragraph lsh = new Paragraph(text, getPdfChineseFont(1));
        lsh.setAlignment(alignment);
        lsh.setFirstLineIndent(firstLineIndent);
        lsh.setSpacingAfter(spacingAfter);
        document.add(lsh);
    }

    /**
     * 设置表格内容 并将表格加入文档中
     *
     * @param dates    数据
     * @param document 文档
     * @param table    表格
     * @throws Exception 异常
     */
    public static void setPdfTableContent(Object[][] dates, Document document, PdfPTable table) throws Exception {
        for (int i = 0; i < dates.length; i++) {
            for (int j = 0; j < dates[i].length; j++) {
                //表格的单元格
                PdfPCell pdfCell = new PdfPCell();
                //设置表格行高
                if (i > 0 && i < 3) {
                    pdfCell.setMinimumHeight(50);
                } else {
                    pdfCell.setMinimumHeight(25);
                }
                Paragraph paragraph = new Paragraph(dates[i][j] + "", getPdfChineseFont(1));
                pdfCell.setPhrase(paragraph);

                setCellStyle(pdfCell);
                table.addCell(pdfCell);
            }
        }

        document.add(table);
    }

    /**
     * 设置表格单元格样式
     *
     * @param pdfCell 单元格对象
     */
    public static void setCellStyle(PdfPCell pdfCell) {
        pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfCell.setBackgroundColor(new BaseColor(0xdd7e6b));
        pdfCell.setBorderWidthTop(0.1f);
        pdfCell.setBorderWidthBottom(0.1f);
        pdfCell.setBorderWidthLeft(0.1f);
        pdfCell.setBorderWidthRight(0.1f);
    }

    /**
     * 设置pdf字体及大小
     *
     * @param type 标题或者内容 0-标题 1-内容
     * @return 字体
     * @throws Exception 异常
     */
    public static Font getPdfChineseFont(int type) throws Exception {
        // 使用系统字体
        BaseFont bfChinese = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font;
        if (type == 1) {
            font = new Font(bfChinese, 10, Font.NORMAL);
        } else {
            font = new Font(bfChinese, 14, Font.BOLD);
        }
        return font;
    }

    /**
     * 删除文件夹
     *
     * @param folderPath 文件路基
     */
    public static void delFolder(String folderPath) {
        // 删除完里面所有内容
        delAllFile(folderPath);
        String filePath = folderPath;
        filePath = filePath.toString();
        File myFilePath = new File(filePath);
        // 删除空文件夹
        myFilePath.delete();
    }

    /**
     * 删除指定文件夹下所有文件
     *
     * @param path 文件路基
     * @return 是否成功
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                // 先删除文件夹里面的文件
                delAllFile(path + "/" + tempList[i]);
                // 再删除空文件夹
                delFolder(path + "/" + tempList[i]);
                flag = true;
            }
        }
        return flag;
    }
}
