package com.htck.svr_hkct.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author haohan
 * 06/27/2019 - 04:00 下午
 * 阀门
 */
@Data
@Entity
public class Valve {

    @Id
    private Integer fid;

    private String press_o;

    private Integer next_process;

    private Integer tid;

    private Integer displayid;

    private String coor;

}
