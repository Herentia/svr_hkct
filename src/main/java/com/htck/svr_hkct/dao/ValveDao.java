package com.htck.svr_hkct.dao;

import com.htck.svr_hkct.entity.Valve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author haohan
 * 06/27/2019 - 04:12 下午
 * 阀门
 */
public interface ValveDao extends JpaRepository<Valve, Integer> {

    /** 查询爆管影响阀门基本信息和坐标 */
    @Query(value = "select a.fid, a.press_o, a.next_process, a.tid, a.displayid, " +
            "round(b.GEOM.SDO_POINT.X, 4)||','||round(b.GEOM.SDO_POINT.Y, 4) as coor " +
            "from (select distinct fid, nod, press_o, next_process, tid, displayid " +
            "from HKCT_TRACE_VALVE where tid = ?1) a, " +
            "pnt b where a.fid = b.id order by next_process desc", nativeQuery = true)
    List<Valve> getValve(Integer tid);

}
