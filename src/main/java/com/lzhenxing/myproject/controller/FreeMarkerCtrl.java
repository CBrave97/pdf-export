package com.lzhenxing.myproject.controller;

import com.lzhenxing.myproject.domain.PDFData;
import com.lzhenxing.myproject.domain.PDFDataT;
import com.lzhenxing.myproject.domain.PDFDataTest;
import com.lzhenxing.myproject.domain.Request;
import com.lzhenxing.myproject.utils.PDFTemplateUtil;
import com.lzhenxing.myproject.utils.Resource ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/ftl")
public class FreeMarkerCtrl {

    @Autowired
    private Resource resource;

    @RequestMapping(value = "index")
    public String index(ModelMap map){
        map.addAttribute("resource",resource);
        return "freemarker/index";
    }

    @RequestMapping(value ="center")
    public String  center(ModelMap map){
        map.put("users",parseUsers());
        map.put("title","用户列表");
        return "freemarker/center/center";
    }

    @RequestMapping(value ="/show")
    public String  show(ModelMap map, HttpServletRequest Request,HttpServletResponse response) throws Exception {
        ByteArrayOutputStream baos = null;
        OutputStream out = null;
        try {
            // 模板中的数据，实际运用从数据库中查询
//            Map<String,Object> map = new HashMap<>();
            map.put("prescription", "XY201904280024");
            map.put("departments", "全科");
            map.put("date", "2019-04-28");
            map.put("name", "王子庆");
            map.put("sex","男");
            map.put("age","10岁3个月");
            map.put("telephone","16645206668");
            map.put("address","海湾");
            map.put("diagnose","支气管炎");
            map.put("allergy","头孢硫脒单磷不知道哪一瓶");
            map.put("doctor","张海明");
            map.put("money","193.60元");
            List<PDFDataT> list = new ArrayList<>();
            list.add(new PDFDataT("初诊：","咳嗽咽痛发热呼吸音尚可，舌红"));
            list.add(new PDFDataT("复诊：","37，晨起干咳，咽痒，呼吸音粗"));
            list.add(new PDFDataT("三诊：","昨夜咳嗽，呼吸音粗，鼻腔红"));
            list.add(new PDFDataT("四诊：","晚间不咳嗽，晨起咳嗽，咽红充血，呼吸音粗，舌红"));
            list.add(new PDFDataT("五诊：","2019年4月23日：患儿发热，38.1-37，布洛芬退热，痰多舌淡，苔白腻，双肺呼吸音粗，食欲差，大便尚可28kg"));
            list.add(new PDFDataT("六诊：","好转"));
            list.add(new PDFDataT("七诊：","下午四时发热，38摄氏度，舌红，咽红，苔黄腻"));
            list.add(new PDFDataT("八诊：","完全恢复"));


            List<PDFData> detailList = new ArrayList<>();
            detailList.add(new PDFData("4.5%葡萄糖注射液/250ml*1瓶/瓶","250ml","共一瓶","用法：静脉滴注  1次/一天  共1天"));
            detailList.add(new PDFData("派奇针（0.25）0.25g*1支/支","0.25g","共一支","用法：口服  一袋  一次/天"));
            detailList.add(new PDFData("6.沛怡优佳口服输液（1支*1支/支）","","共一袋","用法：静脉滴注  1次/一天  共1天"));
            detailList.add(new PDFData("5.0%葡萄糖浆注射液/250ml*1瓶/瓶","250ml","共一瓶","用法：静脉滴注  1次/一天  共1天"));
            detailList.add(new PDFData("6.0%葡萄糖注射液/250ml*1瓶/瓶","250ml","共一瓶","用法：静脉滴注  1次/一天  共1天"));
            detailList.add(new PDFData("7.0%葡萄糖注射液/250ml*1瓶/瓶","250ml","共一瓶","用法：静脉滴注  1次/一天  共1天"));
            map.put("detailList", detailList);
            map.put("list", list);
            baos = PDFTemplateUtil.createPDF(map, "pdf.ftl");;
            // 设置响应消息头，告诉浏览器当前响应是一个下载文件
//            response.setContentType( "application/x-msdownload");
            // 告诉浏览器，当前响应数据要求用户干预保存到文件中，以及文件名是什么 如果文件名有中文，必须URL编码
//            String fileName = URLEncoder.encode("处方模板.pdf", "UTF-8");
//            response.setHeader( "Content-Disposition", "attachment;filename=" + fileName);
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

        return "freemarker/center/pdf";
    }

    @RequestMapping("/export")
    public void exportPdf(HttpServletResponse response) throws Exception{
        ByteArrayOutputStream baos = null;
        OutputStream out = null;
        try {
            // 模板中的数据，实际运用从数据库中查询
            Map<String,Object> map = new HashMap<>();
            map.put("prescription", "XY201904280024");
            map.put("departments", "全科");
            map.put("date", "2019-04-28");
            map.put("name", "王子庆");
            map.put("sex","男");
            map.put("age","10岁3个月");
            map.put("telephone","16645206668");
            map.put("address","海湾");
            map.put("diagnose","支气管炎");
            map.put("allergy","头孢硫脒单磷不知道哪一瓶");
            map.put("doctor","张海明");
            map.put("money","193.60元");
            List<PDFDataT> list = new ArrayList<>();
            list.add(new PDFDataT("初诊：","咳嗽咽痛发热呼吸音尚可，舌红"));
            list.add(new PDFDataT("复诊：","37，晨起干咳，咽痒，呼吸音粗"));
            list.add(new PDFDataT("三诊：","昨夜咳嗽，呼吸音粗，鼻腔红"));
            list.add(new PDFDataT("四诊：","晚间不咳嗽，晨起咳嗽，咽红充血，呼吸音粗，舌红"));
            list.add(new PDFDataT("五诊：","2019年4月23日：患儿发热，38.1-37，布洛芬退热，痰多舌淡，苔白腻，双肺呼吸音粗，食欲差，大便尚可28kg"));
            list.add(new PDFDataT("六诊：","好转"));
            list.add(new PDFDataT("七诊：","下午四时发热，38摄氏度，舌红，咽红，苔黄腻"));
            list.add(new PDFDataT("八诊：","完全恢复"));


            List<PDFData> detailList = new ArrayList<>();
            detailList.add(new PDFData("4.5%葡萄糖注射液/250ml*1瓶/瓶","250ml","共一瓶","用法：静脉滴注  1次/一天  共1天"));
            detailList.add(new PDFData("派奇针（0.25）0.25g*1支/支","0.25g","共一支","用法：口服  一袋  一次/天"));
            detailList.add(new PDFData("6.沛怡优佳口服输液（1支*1支/支）","","共一袋","用法：静脉滴注  1次/一天  共1天"));
            detailList.add(new PDFData("5.0%葡萄糖浆注射液/250ml*1瓶/瓶","250ml","共一瓶","用法：静脉滴注  1次/一天  共1天"));
            detailList.add(new PDFData("6.0%葡萄糖注射液/250ml*1瓶/瓶","250ml","共一瓶","用法：静脉滴注  1次/一天  共1天"));
            detailList.add(new PDFData("7.0%葡萄糖注射液/250ml*1瓶/瓶","250ml","共一瓶","用法：静脉滴注  1次/一天  共1天"));
            map.put("detailList", detailList);
            map.put("list", list);
            baos = PDFTemplateUtil.createPDF(map, "pdf.ftl");;
//             设置响应消息头，告诉浏览器当前响应是一个下载文件
            response.setContentType( "application/x-msdownload");
//             告诉浏览器，当前响应数据要求用户干预保存到文件中，以及文件名是什么 如果文件名有中文，必须URL编码
            String fileName = URLEncoder.encode("处方模板.pdf", "UTF-8");
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

    private List<Map> parseUsers(){
        List<Map> list= new ArrayList<>();
        for(int i=0;i<10;i++){
            Map map= new HashMap();
            map.put("name","kevin_"+i);
            map.put("age",10+i);
            map.put("phone","1860291105"+i);
            list.add(map);
        }
        return list;
    }
}

