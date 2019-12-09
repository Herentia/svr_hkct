package com.htck.svr_hkct.controller;

import com.htck.svr_hkct.service.GetInfoFromTCIS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author haohan
 * 10/10/2019 - 02:20 下午
 * 通过调压器ID从TCIS3.0获取用户详细信息
 */
@RestController
@RequestMapping("/tcis")
//@CrossOrigin
public class HkctTCISController {

    @Autowired
    private GetInfoFromTCIS getInfoFromTCIS;

    @PostMapping("/userinfo")
    @CrossOrigin(allowCredentials="true", maxAge = 3600)
    public String getInfoFromTCIS(@RequestParam("codes") String codes,
                                  @RequestParam("flag") String flag,
                                  @RequestParam("tid") Integer tid) {
        return getInfoFromTCIS.getInfoFromTCIS(codes, flag, tid);
    }

}
