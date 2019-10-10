package com.htck.svr_hkct.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author haohan
 * 06/27/2019 - 04:20 下午
 */
@Data
@Entity
public class GasSource {

    @Id
    private Integer fid;

    private Integer sourcefid;

    private Integer tid;

    private String coor;

}
