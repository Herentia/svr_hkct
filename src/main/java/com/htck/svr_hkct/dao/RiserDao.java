package com.htck.svr_hkct.dao;

import com.htck.svr_hkct.entity.Riser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author haohan
 * 06/27/2019 - 03:46 下午
 */
public interface RiserDao extends JpaRepository<Riser, Integer> {

    /** 立管 */
    @Query(value = "select a.fid, a.press_o, a.tid, round(b.GEOM.SDO_POINT.X, 4)||','||round(b.GEOM.SDO_POINT.Y, 4) as coor " +
            "from HKCT_TRACE_RISER a, pnt b where a.fid = b.id and a.tid = ?1", nativeQuery = true)
    List<Riser> getRiser(Integer tid);

}
