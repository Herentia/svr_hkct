package com.htck.svr_hkct.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.htck.svr_hkct.dao.ScadaDao;
import com.htck.svr_hkct.dto.EchartDTO;
import com.htck.svr_hkct.entity.ScadaName;
import com.htck.svr_hkct.entity.ScadaPt;
import com.htck.svr_hkct.service.ScadaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haohan
 * 07/10/2019 - 10:43 上午
 */
@Service
@Slf4j
public class ScadaServiceImpl implements ScadaService {

    @Autowired
    private ScadaDao scadaDao;

    /** 根据日期时间获取站点数据 */
    @Override
    public JSONArray getScadaByDate(String startTime, String endTime, String zhaddress) {
        List<ScadaPt> list = scadaDao.getScadaPtByDate(startTime, endTime, zhaddress);
        JSONArray json = this.getScadaForJson(list);
        return json;
    }

    /** 将scada数据转换成为所需要的json数据 */
    private JSONArray getScadaForJson(List<ScadaPt> scadaPtList) {
        String dateTime = "";
        StringBuffer str = new StringBuffer();      //拼接json字符串
        if(scadaPtList != null && scadaPtList.size() > 0) {
            //设置scada数据时间为本次查询日期最开始的时间
            dateTime = scadaPtList.get(1).getRecord_time();
            str.append("[{\"");
            str.append("date\":");
            str.append("\"");
            str.append(dateTime);
            str.append("\"");
            str.append(",");
        } else {
            return null;
        }

        for(ScadaPt scada : scadaPtList) {
            if(!dateTime.equals(scada.getRecord_time())) {
                //对象新的记录开始时先删除最后的结束的逗号
                str.deleteCharAt(str.length() - 1);
                str.append("},");
                str.append("{");
                str.append("\"");
                str.append("date\":");
                str.append("\"");
                str.append(scada.getRecord_time());
                str.append("\"");
                str.append(",");
                //时间改变时更新时间，并开始新的一条记录的循环
                dateTime = scada.getRecord_time();
            }
            //时间一样的所有值为一条记录
            if(dateTime.equals(scada.getRecord_time())) {
                str.append("\"");
                String pname = scada.getPname();
                pname = pname.substring(pname.indexOf("/") + 1, pname.length());
                pname = pname.replace(".", "_");
                str.append(pname);
                str.append("\"");
                str.append(":");
                str.append(scada.getPvalue());
                str.append(",");
            }
        }
        str.deleteCharAt(str.length() - 1);
        str.append("}]");
        JSONArray jsonArray = JSON.parseArray(str.toString());
        return jsonArray;
    }


    /** 获取当前和昨天日期指定站点数据 */
    @Override
    public EchartDTO getEchartData(String pname, String curDate, String YesDate) {
        List<ScadaPt> curData = scadaDao.getCurAndYesData(pname, curDate);
        List<ScadaPt> yesData = scadaDao.getCurAndYesData(pname, YesDate);
        EchartDTO echartDTO = new EchartDTO();
        echartDTO.setCurData(curData);
        echartDTO.setYesData(yesData);
        return echartDTO;
    }

    @Override
    public List<ScadaName> getScadaName(String zhaddress) {
        List<ScadaName> scadaNameList = scadaDao.getScadaName(zhaddress);
        return scadaNameList;
    }

    /**
     * 导出Excel
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param zhaddress 站点
     */
    @Override
    public void reportExcel(String startTime, String endTime, String zhaddress) {
        //获取站点信息
        List<ScadaName> scadaNameList = this.getScadaName(zhaddress);
        //获取指定站点时间区间内的数据
        JSONArray scadaByDate = this.getScadaByDate(startTime, endTime, zhaddress);
        ArrayList<String> head = new ArrayList<>();
        //取出中文表头
        scadaNameList.stream().forEach(
                item -> head.add(item.getPaddress())
        );

    }
}
