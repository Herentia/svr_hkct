package com.htck.svr_hkct.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author haohan
 * 06/27/2019 - 04:53 下午
 */
@Data
@Entity
@NoArgsConstructor
//@NamedStoredProcedureQuery(name = "AE_TRACE_HKCT", procedureName = "PKG_HKCT_TRACE.HKCT_GET_PATH", parameters = {
//        @StoredProcedureParameter(name = "PIPEID", mode = ParameterMode.IN, type = Integer.class),
//        @StoredProcedureParameter(name = "T", mode = ParameterMode.IN, type = Integer.class),
//        @StoredProcedureParameter(name = "X", mode = ParameterMode.IN, type = Double.class),
//        @StoredProcedureParameter(name = "Y", mode = ParameterMode.IN, type = Double.class),
//        @StoredProcedureParameter(name = "RT", mode = ParameterMode.OUT, type = Integer.class)
//})
public class GasUsers implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)//自动增递
    private Long id;

    private String name;

    private String email;

}
