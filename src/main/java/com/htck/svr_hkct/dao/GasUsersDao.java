package com.htck.svr_hkct.dao;

import com.htck.svr_hkct.entity.GasUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author haohan
 * 06/27/2019 - 05:05 下午
 * 调用爆管分析存储过程
 */
public interface GasUsersDao extends JpaRepository<GasUsers, Long> {

//    List<GasUsers> getGasUser();

//    @Procedure(name = "AE_TRACE_HKCT")
//    Integer doTrace(@Param("PIPEID") Integer pipeid,
//                    @Param("T") Integer tid,
//                    @Param("X") Double x,
//                    @Param("Y") Double y);

}
