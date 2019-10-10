package com.htck.svr_hkct.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author haohan
 * 07/16/2019 - 04:13 下午
 * scada 站名类----用来通过英文名获取对应的中文名
 */
@Entity
@Data
public class ScadaName {

    @Id
    private Long id;
    private String pname;
    private String paddress;

}
