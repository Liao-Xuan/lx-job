package com.lx.job.model.url;

/**
 * @Description: system 服务相关地址
 * @Author: liaoxuan
 * @Date: 2020/11/15 11:12
 * @Version: 1.0
 */

public interface SystemUrl {

    /**
     * 查询所有箱子ID
     */
    String QUERY_ALL_BOX_ID = "/notice/all/boxId";
    /**
     * 查询所有营业中的箱子所在楼层的所有用户
     */
    String QUERY_ALL_OPEN_BOX_NEAR_USER = "/notice/all/openBox/nearUser";
}
