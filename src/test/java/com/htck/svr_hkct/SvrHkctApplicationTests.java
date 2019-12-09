package com.htck.svr_hkct;

import com.htck.svr_hkct.dao.AESummaryDao;
import com.htck.svr_hkct.dao.GSUserInfoDao;
import com.htck.svr_hkct.entity.AESummary;
import com.htck.svr_hkct.entity.GSUserInfo;
import com.htck.svr_hkct.service.GetInfoFromTCIS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SvrHkctApplicationTests {

    @Autowired
    private GSUserInfoDao userInfoDao;
    @Autowired
    private AESummaryDao summaryDao;
    @Autowired
    private GetInfoFromTCIS infoFromTCIS;

    @Test
    public void contextLoads() {
    }

    /**
     * 测试工商用户信息
     */
    @Test
    public void userInfoTest() {
        List<GSUserInfo> userInfoList = new ArrayList<>();
        GSUserInfo gsUserInfo = new GSUserInfo();
        gsUserInfo.setUserid("1");
        gsUserInfo.setUsername("测试");
        gsUserInfo.setMobile("123213");
        gsUserInfo.setMobile("1232131");

        GSUserInfo gsUserInfo2 = new GSUserInfo();
        gsUserInfo2.setUserid("2");
        gsUserInfo2.setUsername("测试");
        gsUserInfo2.setMobile("22222");
        gsUserInfo2.setMobile("22222222222");

        userInfoList.add(gsUserInfo);
        userInfoList.add(gsUserInfo2);
//        List<GSUserInfo> userInfo = userInfoDao.findAll();
//        GSUserInfo userInfo = userInfoDao.findByTid("1233");
//        GSUserInfo userInfo = userInfoDao.save(gsUserInfo);
        List<GSUserInfo> list = userInfoDao.saveAll(userInfoList);
        System.out.println(list);
    }

    /**
     * 测试爆管分析结果总表
     */
    @Test
    public void AESummaryTest() {
//        AESummary summary = summaryDao.getOne(Long.valueOf(35899));
//        AESummary summary = summaryDao.findByTid(Long.valueOf(2874));
//        summary.setNeedclosenum(100);
//        summary.setDomesticnum(200);
//        summary = summaryDao.save(summary);
//        System.out.println(summary);
    }

    @Test
    public void AETrace() {
//        infoFromTCIS.getInfoFromTCIS("98065", "0", 2380);
//        infoFromTCIS.getInfoFromTCIS("30867", "0", 2380);
        String str = "1";
        String[] s = str.split(",");
        System.out.println(s[0]);
    }

}
