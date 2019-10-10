package com.htck.svr_hkct.service;

import com.alibaba.fastjson.JSONArray;
import com.htck.svr_hkct.dto.EchartDTO;
import com.htck.svr_hkct.entity.ScadaName;
import com.htck.svr_hkct.entity.ScadaPt;

import java.util.List;

/**
 * @author haohan
 * 07/10/2019 - 10:40 上午
 */
public interface ScadaService {

    /** 通过日期和站中文名获取站点数据 */
    JSONArray getScadaByDate(String startTime, String endTime, String zhaddress);

    /** 获取指定时间的折线图数据 */
    EchartDTO getEchartData(String pname, String curDate, String YesDate);

    List<ScadaName> getScadaName(String zhaddress);

    void reportExcel(String startTime, String endTime, String zhaddress);

}
