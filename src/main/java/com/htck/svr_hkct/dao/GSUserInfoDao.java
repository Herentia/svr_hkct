package com.htck.svr_hkct.dao;

import com.htck.svr_hkct.entity.GSUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author haohan
 * 11/21/2019 - 02:40 下午
 */
public interface GSUserInfoDao extends JpaRepository<GSUserInfo, String> {

    List<GSUserInfo> findAll();

    GSUserInfo findByTid(String tid);

    /**
     * 保存单个工商用户信息
     * @param gsUserInfo   工商用户信息
     * @return gsUserInfo
     */
    GSUserInfo save(GSUserInfo gsUserInfo);

    /**
     * 批量保存工商用户信息
     * @param userInfoList  工商用户信息
     * @return  工商用户信息
     */
//    List<GSUserInfo> save(Iterable<GSUserInfo> userInfoList);

}
