package com.htck.svr_hkct.service.impl;

import com.htck.svr_hkct.dao.*;
import com.htck.svr_hkct.dto.AeDTO;
import com.htck.svr_hkct.dto.PPara;
import com.htck.svr_hkct.entity.*;
import com.htck.svr_hkct.service.AeTraceService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.List;

/**
 * @author haohan
 * 06/26/2019 - 11:01 上午
 */
@Service
@Slf4j
public class AeTraceServiceImpl implements AeTraceService {

    @Autowired
    private GovDao govDao;
    @Autowired
    private RiserDao riserDao;
    @Autowired
    private PipeDao pipeDao;
    @Autowired
    private ValveDao valveDao;
    @Autowired
    private GasSourceDao gasSourceDao;
    @Autowired
    private GasUsersDao gasUsersDao;
    @Autowired
    private EntityManager entityManager;

    @Override
    public Integer getTid() {
        return govDao.getTid();
    }

    @Override
    public AeDTO getAeCurRes(Integer tid) {
        List<Governor>  governors       = govDao.getGov(tid);
        List<Riser>     risers          = riserDao.getRiser(tid);
        List<Pipe>      pipes           = pipeDao.getPipe(tid);
        List<Valve>     valves          = valveDao.getValve(tid);
        List<GasSource> gasSources      = gasSourceDao.getGasSource(tid);
//        List<GasUsers> gasUser         = gasUsersDao.getGasUser();
        List<GasUsers> gasUser          = null;

        AeDTO           aeDTO           = new AeDTO();
        aeDTO.setGovlist(governors);
        aeDTO.setRiserlist(risers);
        aeDTO.setPipeList(pipes);
        aeDTO.setValveList(valves);
        aeDTO.setGasSourceList(gasSources);
        aeDTO.setGasUser(gasUser);
        return aeDTO;
    }

    /** 调用存储过程 */
    @Override
    public Integer doTrace(PPara pPara) {
        /** 由于Hibernate5.1不支持调用数据库函数，所有使用jdbc方法调用 */
        Session session = entityManager.unwrap(Session.class);
        int result = session.doReturningWork(
            connection -> {
                try(CallableStatement cs = connection.prepareCall("{? = call PKG_HKCT_TRACE.HKCT_GET_PATH(?, ?, ?, ?)}")) {
                    cs.registerOutParameter(1, Types.INTEGER);
                    cs.setInt(2, pPara.getPipeid());
                    cs.setLong(3, pPara.getTid());
                    cs.setDouble(4, pPara.getX());
                    cs.setDouble(5, pPara.getY());
                    cs.execute();
                    return cs.getInt(1);
                }
            }
        );
        return result;
    }

    /** 测试方法 */
    @Override
    public String test(String inParam2) {
        // 向存储过程传递参数并返回值
//        String inParam1 = "Hi Im an inputParam";
//        String outParam1 = iftPeopleDao.inAndOutTest(inParam1);
//        log.info(outParam1, "Woohoo Im an outparam, and this is my inparam Hi Im an inputParam");
        // 向存储过程传递参数不返回值
//        iftPeopleDao.inOnlyTest(inParam1);
        Query query = entityManager.createNativeQuery("{call test_pkg.in_and_out_test(?, ?)}");
        query.setParameter(1, "haha");
        Object singleResult = query.getSingleResult();
        log.info("哈哈" + singleResult);
//        return storedProcedureQuery.getResultList();
        return "asasa";
    }

}
