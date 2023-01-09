package com.yuansheng.dao;


import com.zhuyahui.util.MyAloneHandlerReadWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ToggleTestDaoImpl implements ToggleTestDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getContext() {
        //select id, name from user
        return jdbcTemplate.queryForList("select id, name from user");
    }

    @Override
    public int removeContext(String id) {
        //
        return jdbcTemplate.update("delete  from  user  where id = 2");
    }

    @Override
    public int removeContextDao(String id) {
        return MyAloneHandlerReadWrite.write(()->{
            int update = jdbcTemplate.update("delete  from  user  where id = ?",2);
            int i=1/0;
            return update;
        },Integer.class);
    }
}
