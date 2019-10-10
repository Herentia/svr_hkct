package com.htck.svr_hkct.service.impl;

import com.htck.svr_hkct.service.GetInfoFromTCIS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author haohan
 * 10/10/2019 - 02:48 下午
 */
@Service
public class GetInfoFromTCISImpl implements GetInfoFromTCIS {

    private static final String TCIS_UTL =
            "http://10.72.156.62:8080/tcip/query_stationriserinfo?access_token=PjkoUyNEJXa0FoL1RrcnZicGdqY0cwWXZCQm1mQUswLjYyMjU0OTQyOTcxOTQ0ODk=";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getInfoFromTCIS(String stationrisercode, String flag) {
        HttpHeaders headers = new HttpHeaders();
        //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
        headers.setContentType(MediaType.APPLICATION_JSON);
        //RestTemplate带参传的时候要用HttpEntity<?>对象传递
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stationrisercode", stationrisercode);
        map.put("flag", flag);
        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(map, headers);
        ResponseEntity<String> entity = restTemplate.postForEntity(TCIS_UTL, request, String.class);

        //获取3方接口返回的数据通过entity.getBody();它返回的是一个字符串；
        String body = entity.getBody();
        if(body != null) {
            return body;
        }
        return "failed";
    }

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
