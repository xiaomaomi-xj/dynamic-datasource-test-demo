package com.yuansheng.controller;

import com.yuansheng.service.impl.ToggleTestServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/toggle")
@Api(tags = "测试")
public class ToggleTestControll {

    private final ToggleTestServiceImpl toggleTestServiceImpl;

    public ToggleTestControll(ToggleTestServiceImpl toggleTestServiceImpl) {
        this.toggleTestServiceImpl = toggleTestServiceImpl;
    }


    @GetMapping("/get-context")
    @ApiOperation(value = "有读注解")
    public List<Map<String, Object>> getContext(){
        return toggleTestServiceImpl.getContext();
    }

    @GetMapping("/del-context")
    @ApiOperation(value = "有写注解")
    public void delContext(String id){
        toggleTestServiceImpl.removeContext(id);

    }

    @GetMapping("/class-get-context")
    @ApiOperation(value = "没有注解")
    public List<Map<String, Object>> classGetContext(){
        return toggleTestServiceImpl.findContext();
    }

    @GetMapping("/class-del-context")
    @ApiOperation(value = "没有注解")
    public void classDelContext(String id){
        toggleTestServiceImpl.delContext(id);

    }

    @GetMapping("/method-get-context")
    @ApiOperation(value = "没有注解，用的方法读")
    public List<Map<String, Object>> methodGetContext(){
        return toggleTestServiceImpl.findContextMethod();
    }

    @GetMapping("/method-del-context")
    @ApiOperation(value = "没有注解，用的方法写")
    public void methodDelContext(String id){
        toggleTestServiceImpl.delContextMethod(id);

    }

    @GetMapping("/dao-del-context")
    @ApiOperation(value = "没有注解，dao层有方法写")
    public void daoDelContext(String id){
        toggleTestServiceImpl.delContextDao(id);

    }

    @GetMapping("/update-get-context")
    @ApiOperation(value = "一个方法有读有写")
    public void updateAndGet(String id){
        toggleTestServiceImpl.updateAndGet(id);
    }
}
