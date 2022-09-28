package com.lzhenxing.myproject.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.lzhenxing.myproject.domain.PDFData;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lzhenxing.myproject.domain.PDFDataTest ;
import com.lzhenxing.myproject.utils.PDFTemplateUtil ;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @RequestMapping("/export")
    public void exportPdf(HttpServletResponse response) throws Exception{
        ByteArrayOutputStream baos = null;
        OutputStream out = null;
        try {
            // 模板中的数据，实际运用从数据库中查询
            Map<String,Object> data = new HashMap<>();
            data.put("curr", 1);
            data.put("one", 2);
            data.put("two", 1);
            data.put("three", 6);

            List<PDFData> detailList = new ArrayList<>();
            detailList.add(new PDFData("4.5%葡萄糖注射液/250ml*1瓶/瓶","250ml","共一瓶","用法：静脉滴注  1次/一天  共1天"));
            detailList.add(new PDFData("派奇针（0.25）0.25g*1支/支","0.25g","共一瓶","用法：口服  一袋  一次/天"));
            detailList.add(new PDFData("6.沛怡优佳口服输液（1支*1支/支）",null,"共一瓶","用法：静脉滴注  1次/一天  共1天"));
            detailList.add(new PDFData("4.5%葡萄糖注射液/250ml*1瓶/瓶","250ml","共一瓶","用法：静脉滴注  1次/一天  共1天"));
            detailList.add(new PDFData("4.5%葡萄糖注射液/250ml*1瓶/瓶","250ml","共一瓶","用法：静脉滴注  1次/一天  共1天"));
            detailList.add(new PDFData("4.5%葡萄糖注射液/250ml*1瓶/瓶","250ml","共一瓶","用法：静脉滴注  1次/一天  共1天"));
            detailList.add(new PDFData("4.5%葡萄糖注射液/250ml*1瓶/瓶","250ml","共一瓶","用法：静脉滴注  1次/一天  共1天"));
            data.put("detailList", detailList);

            baos = PDFTemplateUtil.createPDF(data, "pdf.ftl");
            // 设置响应消息头，告诉浏览器当前响应是一个下载文件
            response.setContentType( "application/x-msdownload");
            // 告诉浏览器，当前响应数据要求用户干预保存到文件中，以及文件名是什么 如果文件名有中文，必须URL编码
            String fileName = URLEncoder.encode("处方笺.pdf", "UTF-8");
            response.setHeader( "Content-Disposition", "attachment;filename=" + fileName);
            out = response.getOutputStream();
            baos.writeTo(out);
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("导出失败：" + e.getMessage());
        } finally{
            if(baos != null){
                baos.close();
            }
            if(out != null){
                out.close();
            }
        }
    }

    @RequestMapping(value ="/show")
    public String  center(ModelMap map){
        // 模板中的数据，实际运用从数据库中查询
//        map.put("prescription", "XY201904280024");
//        map.put("departments", "全科");
//        map.put("date", "2019-04-28");
//        map.put("name", "王子庆");
//        map.put("sex","男");
//        map.put("age","10岁3个月");
//        map.put("telephone","16645206668");
//        map.put("address","海湾");
//        map.put("diagnose","支气管炎");
//        map.put("allergy","头孢硫脒单磷不知道哪一瓶");
//        map.put("doctor","张海明");
//        map.put("money","193.60元");

//        List<PDFDataTest> detailList = new ArrayList<>();
//        detailList.add(new PDFDataTest(123456,"测试","测试","测试","测试"));
//        detailList.add(new PDFDataTest(11111"共一瓶","测试","测试","测试","测试"));
//        detailList.add(new PDFDataTest(222222,"测试","测试","测试","测试"));
//        map.put("detailList", detailList);
//        map.put("detailList",map);
        return "freemarker/center/pdf";
    }
}

