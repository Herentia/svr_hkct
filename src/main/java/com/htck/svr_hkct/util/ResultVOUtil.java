package com.htck.svr_hkct.util;

import com.htck.svr_hkct.vo.ResultVO;

/**
 * @author haohan
 * 06/26/2019 - 11:08 上午
 * 返回结果工具类
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(200);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO error(Integer code, String msg, Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

}
