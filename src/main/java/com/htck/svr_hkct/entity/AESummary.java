package com.htck.svr_hkct.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author haohan
 * 11/22/2019 - 10:57 上午
 * 爆管分析分析结果总表
 */
@Data
@Entity
@Table(name = "HKCT_TRACE_SUMMARY")
public class AESummary {
    private Long pipefid;                           //爆管管线ID
    @Id
    private Integer tid;                            //分析编号
    private Long x;                                 //爆管点X坐标
    private Long y;                                 //爆管点Y坐标
    private Date tracedate;                         //分析日期
    private Integer needclosenum;                   //闭关阀个数
    private Integer domesticnum;                    //民用户数
    private Integer candinum;                       //工商用户数
    private Integer domesticconsume;                //民用气量
    private Integer candiconsume;                   //工商用气量
    private Integer risernum;                       //立管个数
    private Integer governornum;                    //调压器个数
    private Integer totallen;                       //总长度
    private Integer pipevolume;                     //总容积量


}
