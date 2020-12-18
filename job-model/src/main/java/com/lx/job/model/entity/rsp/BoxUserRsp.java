package com.lx.job.model.entity.rsp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/11/14 20:36
 * @Version: 1.0
 */

@Data
public class BoxUserRsp implements Serializable {
    private static final long serialVersionUID = -7764467585928765851L;

    /**
     * 箱子ID
     */
    private Long box;
    /**
     * 箱子主名称
     */
    private String boxName;
    /**
     * 地址
     */
    private String address;
    /**
     * 箱子所在楼层的所有用户
     */
    private List<String> openIds;
}
