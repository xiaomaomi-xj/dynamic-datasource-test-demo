package com.yuansheng.dao;


import java.util.List;
import java.util.Map;

public interface ToggleTestDao {

    /**
     * 查询user表，查看是否实现了不同数据用的不同数据源
     *
     * @return 数据
     */
    List<Map<String, Object>> getContext();

    /**
     * 根据id删除信息
     *
     * @param id 要删除的id
     * @return
     */
    int removeContext(String id);

    int removeContextDao(String id);
}
