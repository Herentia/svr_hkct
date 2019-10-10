package com.htck.svr_hkct.dao;

import com.htck.svr_hkct.entity.Pipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author haohan
 * 06/27/2019 - 03:46 下午
 */
public interface PipeDao extends JpaRepository<Pipe, Integer> {

    @Query(value = "select a.fid, a.press_o, a.tid, a.anatype, b.coor " +
            "from HKCT_TRACE_ISOLATE_PIPE a, (" +
            "select b.id, wm_concat(round(r.x, 4)||','||round(r.y, 4)) as coor from  lin b ,table(sdo_util.getvertices(b.geom)) r " +
            "where (r.id = 1 or r.id = 2) group by b.id) b " +
            "where a.fid = b.id and a.tid = ?1", nativeQuery = true)
    List<Pipe> getPipe(Integer tid);

}
