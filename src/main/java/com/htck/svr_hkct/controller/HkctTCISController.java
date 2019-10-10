package com.htck.svr_hkct.controller;

import com.htck.svr_hkct.service.GetInfoFromTCIS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haohan
 * 10/10/2019 - 02:20 下午
 * 通过调压器ID从TCIS3.0获取用户详细信息
 */
@RestController
@RequestMapping("/tcis")
public class HkctTCISController {

    @Autowired
    private GetInfoFromTCIS getInfoFromTCIS;

    @PostMapping("/userinfo")
    public String getInfoFromTCIS(@RequestParam("code") String code,
                                  @RequestParam("flag") String flag) {
        return getInfoFromTCIS.getInfoFromTCIS(code, flag);
    }

}
