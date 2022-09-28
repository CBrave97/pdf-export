package com.lzhenxing.myproject.utils;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
//import com.lzhenxing.myproject.config.SystemConfig;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.xhtmlrenderer.pdf.ITextRenderer;
import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class PDFTemplateUtil {

//    private  SystemConfig systemConfig;

    /**
     * 通过模板导出pdf文件
     * @param data 数据
     * @param templateFileName 模板文件名
     * @throws Exception
     */
    public static ByteArrayOutputStream createPDF(Map<String,Object> data, String templateFileName) throws Exception {
        // 创建一个FreeMarker实例, 负责管理FreeMarker模板的Configuration实例
        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        // 指定FreeMarker模板文件的位置
        cfg.setClassForTemplateLoading(PDFTemplateUtil.class,"/templates/freemarker/center");
        ITextRenderer renderer = new ITextRenderer();
        OutputStream out = new ByteArrayOutputStream();
        try {
            // 设置 css中 的字体样式（暂时仅支持宋体和黑体） 必须，不然中文不显示
            renderer.getFontResolver().addFont("/templates/fonts/SIMSUN.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            // 设置模板的编码格式
            cfg.setEncoding(Locale.CHINA, "UTF-8");
            // 获取模板文件
            Template template = cfg.getTemplate(templateFileName, "UTF-8");
            StringWriter writer = new StringWriter();

            // 将数据输出到html中
            template.process(data, writer);
            writer.flush();

            String html = writer.toString();
            // 把html代码传入渲染器中
            renderer.setDocumentFromString(html);

//             设置模板中的图片路径 （这里的images在resources目录下） 模板中img标签src路径需要相对路径加图片名 如<img src="images/xh.jpg"/>
//            String url = PDFTemplateUtil.class.getClassLoader().getResource("images").toURI().toString();
//            renderer.getSharedContext().setBaseURL(url);

            renderer.layout();
            renderer.createPDF(out, false);
            renderer.finishPDF();
            out.flush();
            return (ByteArrayOutputStream)out;
        } finally {
            if(out != null){
                out.close();
            }
        }
    }


//    /**
//     * 获取生成的PDF文件本地临时路径
//     *
//     * @param targetFileName 目标文件名
//     * @return 本地临时路径
//     */
//    public String getTargetFileTempPath(String targetFileName) {
//
//        String localTempPath = systemConfig.getLocalTempPath();
//        if (!localTempPath.endsWith(File.separator)) {
//            localTempPath = localTempPath + File.separator;
//        }
//        return localTempPath + targetFileName;
//    }

//    /**
//     *
//     * @Title: insertWatermarkText
//     * @Description: PDF生成水印
//     * @author mzl
//     * @param doc
//     * @param watermarkText
//     * @throws Exception
//     * @throws
//     */
//    private static void insertWatermarkText(Document doc, String watermarkText) throws Exception
//    {
//
//        Shape watermark = new Shape(doc, ShapeType.TEXT_PLAIN_TEXT);
//
//
//        //水印内容
//        watermark.getTextPath().setText(watermarkText);
//        //水印字体
//        watermark.getTextPath().setFontFamily("宋体");
//        //水印宽度
//        watermark.setWidth(500);
//        //水印高度
//        watermark.setHeight(100);
//        //旋转水印
//        watermark.setRotation(-40);
//        //水印颜色
//        watermark.getFill().setColor(Color.lightGray);
//        watermark.setStrokeColor(Color.lightGray);
//
//        watermark.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
//        watermark.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
//        watermark.setWrapType(WrapType.NONE);
//        watermark.setVerticalAlignment(VerticalAlignment.CENTER);
//        watermark.setHorizontalAlignment(HorizontalAlignment.CENTER);
//
//        Paragraph watermarkPara = new Paragraph(doc);
//        watermarkPara.appendChild(watermark);
//
//        for (Section sect : doc.getSections())
//        {
//            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_PRIMARY);
//            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_FIRST);
//            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_EVEN);
//        }
//        System.out.println("Watermark Set");
//    }
}

