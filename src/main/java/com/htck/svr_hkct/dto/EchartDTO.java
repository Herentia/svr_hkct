package com.htck.svr_hkct.dto;

import com.htck.svr_hkct.entity.ScadaPt;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author haohan
 * 07/15/2019 - 04:39 下午
 */
@Data
@NoArgsConstructor
public class EchartDTO {

    private List<ScadaPt> curData;
    private List<ScadaPt> yesData;

}
