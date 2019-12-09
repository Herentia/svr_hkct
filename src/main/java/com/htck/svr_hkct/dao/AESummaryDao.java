package com.htck.svr_hkct.dao;

import com.htck.svr_hkct.entity.AESummary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author haohan
 * 11/22/2019 - 11:19 上午
 * 爆管分析结果总表
 */
public interface AESummaryDao extends JpaRepository<AESummary, Integer> {

    /**
     * 通过ID获取分析结果
     * @param id    ID
     * @return  分析结果
     */
    AESummary getOne(Integer id);

    /**
     * 通过分析编号获取分析结果
     * @param tid   分析编号
     * @return  分析结果
     */
    AESummary findByTid(Integer tid);

    /**
     * 保存/更新分析结果
     * @param summary   需要保存或更新的分析结果
     * @return  最新的分析结果
     */
    AESummary save(AESummary summary);

}
