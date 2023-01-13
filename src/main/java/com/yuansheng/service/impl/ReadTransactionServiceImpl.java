package com.yuansheng.service.impl;

import com.yuansheng.dao.ToggleTestDao;
import com.zhuyahui.annotation.ZyhDataSourceRead;
import com.zhuyahui.annotation.ZyhDataSourceWrite;
import com.zhuyahui.annotation.ZyhService;
import com.zhuyahui.util.MyAloneHandlerReadWrite;
import com.zhuyahui.util.TransactionConfig;
import org.springframework.transaction.annotation.Isolation;

import java.util.concurrent.TimeUnit;

/**
 * 这里功能测试在test里面
 */
@ZyhService
public class ReadTransactionServiceImpl {

    private final ToggleTestDao toggleTestDao;
    public ReadTransactionServiceImpl(ToggleTestDao toggleTestDao) {
        this.toggleTestDao = toggleTestDao;
    }

    /**
     * 注解的方式
     * 如果事务隔离级别是可重复读(rr),而你的业务需要多个查询语句，那么查询方法也需要加上事务
     * 这样就算别的线程提交了修改的事务，也不会影响
     */
    //@ZyhDataSourceRead   还是和之前一样，不指定就是随机或轮询
    @ZyhDataSourceRead("slave3")
    @ZyhDataSourceWrite(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public void getContextOnTransactionOnAnnotation() {
        //你就可以这样使用，来保证数据
        System.out.println(toggleTestDao.getContext());
        System.out.println(toggleTestDao.getContext());
        try {
            /**
             * 在等待的时间在sql终端执行这条语句，下面两条语句的输出依然和上面的输出结果一致
             SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
             begin;
             update user set name='slave1-1' where id=1;
             commit;
             */
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(toggleTestDao.getContext());
        System.out.println(toggleTestDao.getContext());
    }

    /**
     * 方法的方式
     * 如果事务隔离级别是可重复读(rr),而你的业务需要多个查询语句，那么查询方法也需要加上事务
     * 这样就算别的线程提交了修改的事务，也不会影响
     */
    public void getContextOnTransactionOnMethod() {
        TransactionConfig transactionConfig = new TransactionConfig();
        transactionConfig.setIsolation(Isolation.REPEATABLE_READ);
        transactionConfig.setReadOnly(true);
        System.out.println(MyAloneHandlerReadWrite.readOnTransaction(() -> {
            System.out.println(toggleTestDao.getContext());
            System.out.println(toggleTestDao.getContext());
            try {
                /**
                 * 在等待的时间在sql终端执行这条语句，下面两条语句的输出依然和上面的输出结果一致
                 SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
                 begin;
                 update user set name='slave1-1' where id=1;
                 commit;
                 */
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(toggleTestDao.getContext());
            return toggleTestDao.getContext();
        }, transactionConfig, "slave1"));
    }

}
