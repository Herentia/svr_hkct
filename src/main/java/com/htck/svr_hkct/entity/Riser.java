package com.htck.svr_hkct.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author haohan
 * 06/27/2019 - 03:07 下午
 * 立管
 */
@Data
@Entity
public class Riser {

    @Id
    private Integer fid;

    private String press_o;

    private Integer tid;

    private String coor;

}
