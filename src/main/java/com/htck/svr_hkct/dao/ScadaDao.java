package com.htck.svr_hkct.dao;

import com.htck.svr_hkct.entity.ScadaName;
import com.htck.svr_hkct.entity.ScadaPt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author haohan
 * 07/09/2019 - 05:18 下午
 */
public interface ScadaDao extends JpaRepository<ScadaPt, Long> {

    /**
     * 获取选择时间区间的scada站点数据
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param zhaddress 站点中文名
     * @return  List<ScadaPt>
     */
    @Query(value = "select s.id, s.pname, s.pvalue, s.record_time from scadajl.hkcg_scada_pt_value_history s " +
            "where s.record_time between ?1 and ?2 and pname in " +
            "(select pname from scadajl.hkcg_scada_pt where zhaddress = ?3) order by s.record_time", nativeQuery = true)
    List<ScadaPt> getScadaPtByDate(String startTime, String endTime, String zhaddress);

    /**
     * 根据站点英文名查询折线图所有数据
     * @param pname     站点英文名
     * @param dateTime  日期
     * @return  List<ScadaPt>
     */
    @Query(value = "select a.id, a.pname, a.pvalue, a.record_time from " +
            "(select h.id, h.pname, h.pvalue, h.record_time from scadajl.hkcg_scada_pt_value_history h where h.record_time like '%:00:00'and h.pname = ?1 order by h.record_time) a " +
            "where a.record_time like ?2", nativeQuery = true)
    List<ScadaPt> getCurAndYesData(String pname, String dateTime);

    /** 获取scada_pt表中的关键数据 */
    @Query(value = "select h.id, h.pname, h.paddress from hkct_scada_pt h where zhaddress = ?1",
            nativeQuery = true)
    List<ScadaName> getScadaName(String zhaddress);

}
