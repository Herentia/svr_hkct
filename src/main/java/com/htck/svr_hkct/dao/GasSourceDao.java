package com.htck.svr_hkct.dao;

import com.htck.svr_hkct.entity.GasSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author haohan
 * 06/27/2019 - 04:24 下午
 */
public interface GasSourceDao extends JpaRepository<GasSource, Integer> {

    /** 获取爆管直接相连的气源和坐标 */
    @Query(value = "select a.fid, a.major, a.minor, a.sourcefid, a.tid, " +
            "round(b.GEOM.SDO_POINT.X, 4)||','||round(b.GEOM.SDO_POINT.Y, 4) as coor " +
            "from HKCT_TRACE_SOURCE a, pnt b where a.fid = b.id and tid = ?1", nativeQuery = true)
    List<GasSource> getGasSource(Integer tid);

}
