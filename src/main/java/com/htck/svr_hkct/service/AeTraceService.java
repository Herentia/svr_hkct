package com.htck.svr_hkct.service;

import com.htck.svr_hkct.dto.AeDTO;
import com.htck.svr_hkct.dto.PPara;

/**
 * @author haohan
 * 06/26/2019 - 10:59 上午
 */
public interface AeTraceService {

    /** 获取分析编号 */
    Integer getTid();

    /** 获取所有分析结果 */
    AeDTO getAeCurRes(Integer tid);

    /** 调用分析存储过程 */
    Integer doTrace(PPara pPara);

    String test(String inPara1);


}
