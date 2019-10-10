package com.htck.svr_hkct.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author haohan
 * 06/26/2019 - 10:25 上午
 * 调压器实体类
 */
@Data
@Entity
public class Governor {

    @Id
    private Integer fid;

    private String press_o;

    private Integer next_process;

    private Integer tid;

    private String coor;


}
