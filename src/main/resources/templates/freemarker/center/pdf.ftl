<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title></title>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body{
            font-family: SimSun;
        }
        section{
            display:block;
            margin: 20px 10px;
        }
        .title{
            text-align: center;
        }
        .preface p{
            line-height: 30px;
        }
        .preface p.content{
            text-indent: 2em;
        }
        section > table{
            table-layout: fixed;
            width: 100%;
            margin: 20px 0px;
            text-align:left;
            word-wrap:break-word;
        }
        section table td{
            padding:5px 10px;
        }

        table td {
            border-top: 0;
            border-right: 1px solid BLACK;
            border-bottom: 1px solid BLACK;
            border-left: 0;
        }
        table tr td:last-child{
            border-right: 0;
        }
        .tablebox{
            border:0;
            padding:0px 0px;

        }
        .tablebox td{
            border:0;
            padding:0px 0px;

        }
        /*relative*/
        table.pos_abs{
            position:absolute;
            left:15px;
            top:180px
        }
        table.footer{
            height: 100px;
            width: 100%;
            position: fixed;
            bottom: 0;
        }
        table.footer td{
            /*background-color: #ddd;*/
        }
    </style>
</head>
<body>
<!-- 标题 start -->
<section class="title">
    <!--&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp-->
    <h2>春播云诊所处方笺</h2>
</section>
<!-- 标题 end -->

<!--前言 start
<section class="preface">
   <p>尊敬的用户：</p>
   <p class="content">内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容</p>
</section>-->
<!-- 前言 end -->

<!-- 汇总统计信息 start -->
<!--<section class="count-info">
    <h4>汇总统计信息</h4>
    <table border="1" cellspacing="0" cellpadding="0">
        <tr>
            <td>本月笔数</td>
            <td>近三个月数量对比</td>
        </tr>
        <tr>
            <td></td>
            <td>
                <table width="80%" border="1" cellspacing="0" cellpadding="0" style="margin: 5px auto;">
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</section>-->
<!-- 汇总统计信息 end -->

<!-- 明细 start -->
<section class="detail">
    <!--  <h4>明细</h4>-->
    <table align="center" border="0" cellspacing="0" cellpadding="0" style="margin-top:0px">
        <tr >
            <td width="34%" style="border-right: none !important;">处方号：${prescription}</td>
            <td width="33%" style="border-right: none !important;">科室：${departments}</td>
            <td width="33%" style="border-right: none !important;">就诊日期：${date}</td>
            <!-- <td>列5</td>-->
        </tr>
        <tr>
            <td >姓名：${name}</td>
            <td>性别：${sex}</td>
            <td>年龄：${age}</td>
        </tr>
        <tr >
            <td>电话：${telephone}</td>
            <td colspan = "2">住址：${address}</td>
        </tr>
        <!--<tr >
        	<td align="right" width="10%" >主诉</td>
            <td colspan = "2">主诉：咳嗽咽痛发热呼吸音尚可，舌红
            						复诊：37，晨起干咳，咽痒，呼吸音粗
            						三诊：昨夜咳嗽，呼吸音粗，鼻腔红
        </tr>-->
   </table>
    <table class="tablebox" border="0" cellspacing="0" cellpadding="0" style="margin-top:-20px;">
<#--        <tr >-->
<#--            <td align="left" width="15%" >主诉：</td>-->
<#--&lt;#&ndash;            <td align="left" >${frequency}${principal}</td>&ndash;&gt;-->
<#--        </tr>-->


        <tr >
            <td align="left" width="15%" >主诉：</td>
            <td width="80%" colspan="2"></td>
        </tr>

        <#list list as ad>
        <tr>
            <td></td>
            <td align="left" colspan="2">${ad.frequency}${ad.principal}</td>

        </tr>
        </#list>


    </table >
    <table align="center" border="0" cellspacing="0" cellpadding="0" style="margin-top:0px">
        <tr>
            <td colspan = "3" >诊断：${diagnose}</td>
        </tr>
        <tr>
            <td  colspan = "3" >过敏史：${allergy}</td>
        </tr>
    </table>
    <table class="tablebox" border="0" cellspacing="0" cellpadding="0" style="margin-top:-20px;">

<#--        <tr >-->
<#--            <td  colspan="3"><h2>RP:</h2></td>-->
<#--        </tr>-->
<#--        <h2>RP:</h2><br/>-->
        <#list detailList as ad>
        <tr >
            <td align="left"  width="70%" >${ad_index+1}.${ad.medicine}</td>
            <td align="right" width="15%">${ad.quality}</td>
            <td align="right" width="15%">${ad.number}</td>
        </tr>
<#--        <tr >-->
<#--            <td align="left"  >${ad.column1}</td>-->
<#--            <td align="right" >${ad.column1}</td>-->
<#--            <td align="right" >共一支</td>-->
<#--        </tr>-->
        <tr>
            <td colspan="3">用法：${ad.usage}</td>
        </tr>
<#--        <tr >-->
<#--            <td align="left" colspan="2" >5.修益生（1袋*1袋/袋）</td>-->
<#--            <td align="right">共一袋</td>-->

<#--        </tr>-->
<#--        <tr>-->
<#--            <td colspan="3">用法：口服  一袋 一次/天</td>-->
<#--        </tr>-->
<#--        <tr >-->
<#--            <td align="left" colspan="2" >6.沛怡优佳口服输液（1支*1支/支）</td>-->
<#--            <td align="right">共一支</td>-->
<#--        </tr>-->
<#--        <tr>-->
<#--            <td colspan="3">用法：口服  一支 一次/天</td>-->
<#--        </tr>-->

        </#list>
    </table>
    <P></P>
    <P></P>
    <P></P>
    <P></P>
    <P></P>
    <P></P>
    <table class="footer" align="center" border="0" cellspacing="0" cellpadding="0">
        <tr style="border-right: none !important;">
            <td align="left" width="30%" style="border-top: 1px solid BLACK !important;">医师：${doctor}</td>
            <td align="left" width="40%" style="border-top: 1px solid BLACK !important;">医师签名（盖章）：</td>
            <td align="left" width="15%" style="border-top: 1px solid BLACK !important;">金额：</td>
            <td align="left" width="15%" style="border-top: 1px solid BLACK !important;">${money}</td>
        </tr>
        <tr>
            <td >
                <table class="tablebox" width="100%" cellspacing="0" cellpadding="0" style="border:0;"  >
                    <tr>
                        <td align="left" width="50%">审核：</td>
                        <td align="left" width="50%"></td>
                    </tr>
                </table>
            </td>
            <td >
                <table class="tablebox" width="100%"  cellspacing="0" cellpadding="0" >
                    <tr>
                        <td align="left" width="35%">调配：</td>
                        <td align="left" width="15%"></td>
                        <td align="left" width="35%">核对：</td>
                        <td align="left" width="15%"></td>

                    </tr>
                </table>

            </td>
            <td align="left" >发药：</td>
            <td align="left" ></td>
        </tr>
    </table>

</section>
<!-- 明细 end -->
</body>
</html>
