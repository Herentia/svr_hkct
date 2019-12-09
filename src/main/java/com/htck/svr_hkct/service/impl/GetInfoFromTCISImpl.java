package com.htck.svr_hkct.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.htck.svr_hkct.dao.AESummaryDao;
import com.htck.svr_hkct.dao.GSUserInfoDao;
import com.htck.svr_hkct.entity.AESummary;
import com.htck.svr_hkct.entity.GSUserInfo;
import com.htck.svr_hkct.service.GetInfoFromTCIS;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author haohan
 * 10/10/2019 - 02:48 下午
 */
@Service
public class GetInfoFromTCISImpl implements GetInfoFromTCIS {

    private static final String TCIS_UTL =
            "http://10.72.156.62:8080/tcip/query_stationriserinfo?access_token=uydywetyrjiikutlhsfgdt56eriGFUHgtruyolftyhulolipotuyuouiy";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GSUserInfoDao userInfoDao;
    @Autowired
    private AESummaryDao summaryDao;

    @Override
    public String getInfoFromTCIS(String stationrisercode, String flag, Integer tid) {
        ReentrantLock lock = new ReentrantLock();
        String[] codes = stationrisercode.split(",");
        HttpHeaders headers = new HttpHeaders();
        //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
        headers.setContentType(MediaType.APPLICATION_JSON);
        //RestTemplate带参传的时候要用HttpEntity<?>对象传递
        Map<String, Object> map = new HashMap<String, Object>();
        for (String code : codes) {
            map.put("stationrisercode", code);
            map.put("flag", flag);
            HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(map, headers);
            ResponseEntity<String> entity = restTemplate.postForEntity(TCIS_UTL, request, String.class);

            //获取3方接口返回的数据通过entity.getBody();它返回的是一个字符串；
            String body = entity.getBody();
            //将返回的json字符串转换成json对象
            JSONObject json = JSONObject.parseObject(body);
            JSONObject ret = json.getJSONObject("body");
            if(json.getInteger("result") == 0) {
                //todo 将获取TCIS3.0中的工商用户总数、民用户总数、用气量等信息存入爆管分析结果总表
                lock.lock();
                AESummary summary = summaryDao.findByTid(tid);
                lock.unlock();
                if (summary.getDomesticnum() != null) {
                    if (ret.containsKey("mycount"))
                        summary.setDomesticnum(summary.getDomesticnum() + Integer.parseInt(ret.getString("mycount")));
                }
                else {
                    if (ret.containsKey("mycount"))
                        summary.setDomesticnum(Integer.parseInt(ret.getString("mycount")));
                }
                if (summary.getCandinum() != null) {
                    if (ret.containsKey("gscount"))
                        summary.setCandinum(summary.getCandinum() + ret.getInteger("gscount"));
                }
                else {
                    if (ret.containsKey("gscount"))
                        summary.setCandinum(ret.getInteger("gscount"));
                }
                if (summary.getDomesticconsume() != null) {
                    if (ret.containsKey("myamount"))
                        summary.setDomesticconsume(summary.getDomesticconsume() + ret.getInteger("myamount"));
                }
                else {
                    if (ret.containsKey("myamount"))
                        summary.setDomesticconsume(ret.getInteger("myamount"));
                }
                if (summary.getCandiconsume() != null) {
                    if (ret.containsKey("gsamount"))
                        summary.setCandiconsume(summary.getCandiconsume() + ret.getInteger("gsamount"));
                }
                else {
                    if (ret.containsKey("gsamount"))
                        summary.setCandiconsume(ret.getInteger("gsamount"));
                }
                //更新分析结果总表中的用户数信息和用气量
                summaryDao.save(summary);

                //todo 获取TCIS3.0中工商用户详细信息并保存
                if(ret.containsKey("gsuserinfo")) {
                    JSONArray gsUsers = ret.getJSONArray("gsuserinfo");
                    List<GSUserInfo> gsUserInfoList = new ArrayList<>();
                    for(int i = 0; i < gsUsers.size(); i++) {
                        GSUserInfo userInfo = new GSUserInfo();
                        JSONObject user = gsUsers.getJSONObject(i);
                        userInfo.setUserid(user.getString("userid"));
                        userInfo.setUsername(user.getString("username"));
                        userInfo.setMobile(user.getString("mobile"));
                        userInfo.setContactphone(user.getString("contactphone"));
                        userInfo.setUsertype(user.getString("usertype"));
                        userInfo.setUserstate(user.getString("userstate"));
                        userInfo.setUseramount(Long.parseLong(user.getString("useramount")));
                        userInfo.setUseraddr(user.getString("useraddr"));
                        userInfo.setTid(tid);
                        gsUserInfoList.add(userInfo);
                    }
                    //保存工商用户信息
                    userInfoDao.saveAll(gsUserInfoList);
                }
                //清空map结合
                map.clear();
            }
        }
        return "SUCCESS";
    }

    //字符串转json对象
    //    //然后把str转换成JSON再通过getJSONObject()方法获取到里面的result对象，因为我想要的数据都在result里面
//    //下面的strToJson只是一个str转JSON的一个共用方法；
//    JSONObject json = StringUtil.strToJson(body);
//        if(json != null){
//        JSONObject user_json = json.getJSONObject("result");
//        //这里考虑 到result也可能为null的情况，因为字符串转json会把字段为null的过滤掉；
//        if(user_json != null && !"{}".equals(user_json.toString())){
//            //调用JSONObject.toJavaObject()把JSON转成java对象最后抛出数据即可
//            User user = JSONObject.toJavaObject(user_json, User.class);
//            //最后抛出json数据
//            Json.toJson(new Result(true, 0, "获取信息成功", user), response);
//            return;
//        }else{
//            Json.toJson(new Result(false, 1, "没有信息", null), response);
//            return;
//        }
//    }else{
//        Json.toJson(new Result(false, 1, "没有信息", null), response);
//    }

}
