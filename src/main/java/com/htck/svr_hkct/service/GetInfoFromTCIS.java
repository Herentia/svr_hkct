package com.htck.svr_hkct.service;

/**
 * @author haohan
 * 10/10/2019 - 02:11 下午
 * 根据调压器ID从TCIS3.0获取该设备下关联的用户数信息
 */
public interface GetInfoFromTCIS {

    /**
     * 通过调压器获取信息
     * @return  string
     */
    String getInfoFromTCIS(String stationrisercode, String flag);

}
