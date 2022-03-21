package com.example.judgmentdoc.blImpl.editor;

import com.example.judgmentdoc.bl.editor.EditorService;
import com.example.judgmentdoc.util.pdf.PdfUtil;
import com.example.judgmentdoc.vo.DocInfoVO;
import com.example.judgmentdoc.vo.MemberVO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EditorServiceImpl implements EditorService {

    private final static String PDF_TEMPLATE_PATH = "./src/main/resources/static/DocInfoVO.pdf";

    @Override
    public String exportPdf(DocInfoVO docInfoVO) {
        Document document = new Document();
        try {
            //创建书写器,写入磁盘
            String pdfPathName = PDF_TEMPLATE_PATH;
            PdfWriter writer = PdfUtil.createPdfWriter(pdfPathName, document, pdfPathName);
            //设置A4
            document.setPageSize(PageSize.A4);
            document.open();
            //设置法院名
            PdfUtil.setPdfFirstTitle(docInfoVO.getCourtName(), document);
            //设置文书名字
            PdfUtil.setPdfFirstTitle(docInfoVO.getName(), document);
            //设置案号
            PdfUtil.setPdfMainBody(docInfoVO.getNumber(), document, Element.ALIGN_RIGHT, 0, 0);
            //设置正文
            String[] contents = docInfoVO.getContent().split("\n");
            for (String para : contents) {
                PdfUtil.setPdfMainBody(para.replace((char) 12288, ' ').replace((char) 160, ' ').trim(), document, Element.ALIGN_LEFT, 20, 0);
            }
            //设置落款人员
            List<MemberVO> members = docInfoVO.getMembers();
            Collections.sort(members);
            for (MemberVO member : members) {
                String m = member.getStatus();
                if (member.getName().length() == 2) {
                    m = m + "　" + member.getName().charAt(0) + "　" + member.getName().charAt(1);
                } else {
                    m = m + "　" + member.getName();
                }
                PdfUtil.setPdfMainBody(m, document, Element.ALIGN_RIGHT, 0, 0);
            }
            //设置落款日期
            PdfUtil.setPdfMainBody(dateToChinese(docInfoVO.getDate()), document, Element.ALIGN_RIGHT, 0, 0);

            document.close();
            writer.close();
            return pdfPathName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String dateToChinese(String date) {
        try {
            String[] dates = date.split("-");
            StringBuilder dateBuilder = new StringBuilder(dates[0] + "年");
            // 年，直接改就行
            for (int i = 0; i < 4; i++) {
                dateBuilder.setCharAt(i, chineseNum(dateBuilder.charAt(i)));
            }
            // 月，十、十一、十二前面加十，十月不用加个位
            if (dates[1].charAt(0) == '1') {
                dateBuilder.append('十');
            }
            if (dates[1].charAt(1) != '0') {
                dateBuilder.append(chineseNum(dates[1].charAt(1)));
            }
            dateBuilder.append('月');
            // 日
            if (dates[2].charAt(0) == '2' || dates[2].charAt(0) == '3') {
                dateBuilder.append(chineseNum(dates[2].charAt(0)) + "十");
            } else if (dates[2].charAt(0) == '1') {
                dateBuilder.append("十");
            }
            if (dates[2].charAt(1) != '0') {
                dateBuilder.append(chineseNum(dates[1].charAt(1)));
            }
            dateBuilder.append('日');
            return dateBuilder.toString();
        } catch (Exception e) {
            return "";
        }
    }

    private char chineseNum(char n) {
        switch (n) {
            case '0':
                return '〇';
            case '1':
                return '一';
            case '2':
                return '二';
            case '3':
                return '三';
            case '4':
                return '四';
            case '5':
                return '五';
            case '6':
                return '六';
            case '7':
                return '七';
            case '8':
                return '八';
            case '9':
                return '九';
            default:
                return '〇';
        }
    }
}
