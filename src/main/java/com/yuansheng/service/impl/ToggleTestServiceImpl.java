package com.yuansheng.service.impl;

import com.yuansheng.dao.ToggleTestDao;
import com.zhuyahui.annotation.ZyhDataSourceRead;
import com.zhuyahui.annotation.ZyhDataSourceWrite;
import com.zhuyahui.annotation.ZyhService;
import com.zhuyahui.util.MyAloneHandlerReadWrite;
import com.zhuyahui.util.TransactionConfig;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;
import java.util.Map;


@ZyhService
@ZyhDataSourceRead
public class ToggleTestServiceImpl {

    private final ToggleTestDao toggleTestDao;

    public ToggleTestServiceImpl(ToggleTestDao toggleTestDao) {
        this.toggleTestDao = toggleTestDao;
    }

    @ZyhDataSourceRead
    public List<Map<String, Object>> getContext() {
        return toggleTestDao.getContext();
    }

    @ZyhDataSourceWrite(isolation = Isolation.REPEATABLE_READ)
    public void removeContext(String id) {
        toggleTestDao.removeContext(id);
        int i=1/0;
    }

    /**
     * 无注解
     *
     * @return
     */
    public List<Map<String, Object>> findContext() {
        return toggleTestDao.getContext();
    }

    /**
     * 无注解
     *
     * @param id
     */
    public void delContext(String id) {
        toggleTestDao.removeContext(id);
        int i=1/0;
    }

    /**
     * 无注解，含有方法
     *
     * @return
     */
    public List<Map<String, Object>> findContextMethod() {
        return MyAloneHandlerReadWrite.read(toggleTestDao::getContext,List.class);
    }

    /**
     * 无写注解，含有方法
     *
     * @param id
     * @return
     */
    public void delContextMethod(String id) {

        TransactionConfig transactionConfig = new TransactionConfig(Propagation.REQUIRED, Isolation.REPEATABLE_READ);
        transactionConfig.setTransactionManager("transactionManager");
//
        int b=MyAloneHandlerReadWrite.write(()->{
            int i = toggleTestDao.removeContext(id);
            int a=1/0;
            return i;
        },Integer.class,transactionConfig);
//


    }

    /**
     * 一个写操作，可能需要都多次来判断
     *
     * @param id
     */
    public void updateAndGet(String id){
        //        //读（从库）
        System.out.println(MyAloneHandlerReadWrite.read(toggleTestDao::getContext, List.class));
//        //写,（主库）
        MyAloneHandlerReadWrite.write(()->toggleTestDao.removeContext(id),Integer.class);
    }

    /**
     * 无写注解，无方法，dao层处理事务
     *
     * @param id
     */
    public void delContextDao(String id) {
        toggleTestDao.removeContextDao(id);
    }


}
