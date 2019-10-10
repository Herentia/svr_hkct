package com.htck.svr_hkct.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author haohan
 * 06/26/2019 - 06:53 下午
 */
@Data
@Entity
public class Pipe {

    @Id
    private Integer fid;

    private String press_o;

    private Integer tid;

    private Integer anatype;

    private String coor;

}
