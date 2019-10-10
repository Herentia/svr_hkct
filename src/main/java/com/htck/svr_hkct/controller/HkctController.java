package com.htck.svr_hkct.controller;

import com.htck.svr_hkct.dto.AeDTO;
import com.htck.svr_hkct.dto.PPara;
import com.htck.svr_hkct.dto.User;
import com.htck.svr_hkct.service.AeTraceService;
import com.htck.svr_hkct.util.ResultVOUtil;
import com.htck.svr_hkct.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haohan
 * 05/30/2019 - 04:26 下午
 */
@RestController
@Slf4j
public class HkctController {

    @Autowired
    private AeTraceService aeTraceService;

    @GetMapping("/getuser")
    public User getUser(@RequestParam(value = "name", defaultValue = "小明") String name) {
        User user = new User();
        user.setName(name);
        user.setAge(18);
        return user;
    }

    /** 返回分析编号 */
    @GetMapping("gettid")
    public ResultVO<Map<String, Integer>> getTid() {
        Integer tid = aeTraceService.getTid();
        Map<String, Integer> map = new HashMap<>();
        map.put("tid", tid);
        return ResultVOUtil.success(map);
    }

    /** 获取分析结果 */
    @GetMapping("getAe")
    public ResultVO<Map<String, AeDTO>> getAeCurRes() {
        AeDTO aeCurRes = aeTraceService.getAeCurRes(3010);
        Map<String, AeDTO> map = new HashMap<>();
        map.put("AeCurRes", aeCurRes);
        return ResultVOUtil.success(map);
    }

    @PostMapping("dotrace")
    public ResultVO<Map<String, Integer>> doTrace(@Valid PPara pPara, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.error(400, "参数错误");
        }
        //通过seq获取id
        Integer tid = aeTraceService.getTid();
        pPara.setTid(tid);

        //执行分析过程
        Integer result = aeTraceService.doTrace(pPara);
        Map<String, AeDTO> map = new HashMap<>();
        //判断分析结果
        if(result != 1) {
            //获取分析结果
            AeDTO aeCurRes = aeTraceService.getAeCurRes(tid);

            map.put("AeCurRes", aeCurRes);
            return ResultVOUtil.error(201, "存在不可关闭链路", map);
        }

        //获取分析结果
        AeDTO aeCurRes = aeTraceService.getAeCurRes(tid);

        map.put("AeCurRes", aeCurRes);

        return ResultVOUtil.success(map);
    }



    /** 测试接口 */
    @PostMapping("test")
    public ResultVO<Map<String, String>> test() {
        String str = aeTraceService.test("搞么事");
        Map<String, String> map = new HashMap<>();
        map.put("测试", str);
        return ResultVOUtil.success(map);
    }

}
