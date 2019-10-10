package com.htck.svr_hkct.dao;

import com.htck.svr_hkct.entity.Governor;
import com.htck.svr_hkct.entity.Pipe;
import com.htck.svr_hkct.entity.Riser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author haohan
 * 06/26/2019 - 10:50 上午
 */
public interface GovDao extends JpaRepository<Governor, Integer> {

    /**
     * 获取分析批次编号
     * @return 分析编号
     */
    @Query(value = "select SEQ_HKCT_PID.nextval from dual", nativeQuery = true)
    Integer getTid();

    /** 调压器 */
    @Query(value = "select a.fid, a.press_o, a.next_process, a.tid, round(b.GEOM.SDO_POINT.X, 4)||','||round(b.GEOM.SDO_POINT.Y, 4) as coor " +
            "from HKCT_TRACE_GOVERNOR a, pnt b where a.fid = b.id and a.tid = ?1", nativeQuery = true)
    List<Governor> getGov(Integer tid);

}
