package com.htck.svr_hkct.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author haohan
 * 06/27/2019 - 05:06 下午
 * 爆管分析传入参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PPara implements Serializable {

    private static final long serialVersionUID = 2964999238213179309L;

    /** 爆管管线ID */
    private Integer pipeid;
    /** 分析编号 */
    private Integer tid;
    /** 爆管点坐标X */
    private Double x;
    /** 爆管点坐标Y */
    private Double y;

}
