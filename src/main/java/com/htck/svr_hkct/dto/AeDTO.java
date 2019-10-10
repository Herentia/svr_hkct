package com.htck.svr_hkct.dto;

import com.htck.svr_hkct.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author haohan
 * 06/26/2019 - 11:37 上午
 * 爆管分析结果传输实体类
 */
@Data
@NoArgsConstructor
public class AeDTO {

    /** 爆管分析所有受影响的管线 */
    private List<Governor> govlist;

    /** 受影响立管 */
    private List<Riser> riserlist;

    /** 受影响管线 */
    private List<Pipe> pipeList;

    /** 受影响阀门 */
    private List<Valve> valveList;

    /** 爆管直连气源 */
    private List<GasSource> gasSourceList;

    /** 爆管影响用户 */
    private List<GasUsers> gasUser;


}
