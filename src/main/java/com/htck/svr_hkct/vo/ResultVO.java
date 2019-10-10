package com.htck.svr_hkct.vo;

import lombok.Data;

/**
 * @author haohan
 * 06/26/2019 - 10:45 上午
 * http返回结果对象
 */
@Data
public class ResultVO<T> {

    /** 状态码 */
    private Integer code;
    /** 提示信息 */
    private String msg;
    /** 数据 */
    private T data;

}
