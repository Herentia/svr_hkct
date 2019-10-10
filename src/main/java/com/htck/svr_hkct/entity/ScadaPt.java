package com.htck.svr_hkct.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author haohan
 * 07/09/2019 - 05:14 下午
 * scada_pt_value_history
 */
@Data
@Entity
public class ScadaPt {

    @Id
    private Long id;
    private String pname;
    private Double pvalue;
    private String record_time;

}
