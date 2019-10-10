package com.htck.svr_hkct.controller;

import com.alibaba.fastjson.JSONArray;
import com.htck.svr_hkct.dto.EchartDTO;
import com.htck.svr_hkct.entity.ScadaPt;
import com.htck.svr_hkct.service.ScadaService;
import com.htck.svr_hkct.util.ResultVOUtil;
import com.htck.svr_hkct.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author haohan
 * 07/10/2019 - 10:46 上午
 */
@RestController
@Slf4j
public class HkctScadaController {

    @Autowired
    private ScadaService scadaService;

    @PostMapping("/scada")
    public ResultVO<JSONArray> getSacada(@RequestParam("startTime") String startTime,
                                         @RequestParam("endTime") String endTime,
                                         @RequestParam("zhaddress") String zhaddress) {
        JSONArray json = scadaService.getScadaByDate(startTime, endTime, zhaddress);
        return ResultVOUtil.success(json);
    }

    @PostMapping("/echart")
    public  ResultVO<EchartDTO> getEchart(@RequestParam("pname") String pname,
                                          @RequestParam("curDate") String curDate,
                                          @RequestParam("yesDate") String yesDate) {
        EchartDTO echartData = scadaService.getEchartData(pname, curDate, yesDate);
        return ResultVOUtil.success(echartData);
    }

}
