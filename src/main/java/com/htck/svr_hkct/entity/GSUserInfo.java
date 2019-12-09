package com.htck.svr_hkct.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author haohan
 * 11/21/2019 - 02:33 下午
 */
@Data
@Entity
@Table(name = "HKCT_TRACE_REPORT_GSUSERINFO")
public class GSUserInfo {

    @Id
    private String userid;                          //用户号
    private String username;                        //用户名
    private String mobile;                          //手机号码
    private String contactphone;                    //联系电话
    private String usertype;                        //用户性质
    private String userstate;                       //用户状态
    private Long useramount;                        //年用气量
    private String useraddr;                        //用户地址
    private Integer tid;                             //爆管分析编号

}
