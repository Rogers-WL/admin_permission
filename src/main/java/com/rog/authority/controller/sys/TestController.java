package com.rog.adminpermission.controller.sys;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangl
 * @Description todo
 * @date 12/12/2019 2:55 PM
 */
@Api(tags = {"测试用户接口"})
@RestController
public class TestController {

    //与直接注释@Slf4j作用类似，底层框架必须包含 org.slf4j.impl.StaticLoggerBindder,slf4j将会主动去寻找这个类
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    private static  final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);



//    @Autowired
//    private TestAsync util;
//    @Autowired
//    private TestService testService;

    @Value("${god}")
    private String god;

    //tags用在方法上会在swagger首页显示这个接口
    @ApiOperation(value = "获取首页信息方法",notes = "这是提示信息note")
    @PostMapping("/home")
    public void home(){
        fixedThreadPool.execute(()->{
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

//    //tags用在方法上会在swagger首页显示这个接口
//    @ApiOperation(value = "获取首页信息方法",notes = "这是提示信息note")
//    @PostMapping("/home")
//    public Role home(@RequestBody @ApiParam(value = "英雄",required = true)  Role hero){
//
//        return  hero ;
//    }
//
//    @ApiOperation(value = "区域测试")
//    @PostMapping("/hero")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="area",value="区域",dataType="string", paramType = "query"  )})
//    public String hero( String area){
//        if(1==1) {
//            throw new AccountException(ResultCode.USER_HAS_EXISTED);
//        }
//        return area + god ;
//    }
//
//    @ApiOperation("区域排行测试")
//    @PostMapping("/hero1")
//    public String hero1(String area, String rank){
//        int a = 1/0;
//        return area + god + rank ;
//    }
//
//    @ApiOperation("测试全局异常")
//    @GetMapping("/test")
//    public String test(String s){
//        return testService.test(s);
//    }
//
//    @ApiOperation("测试参数校验")
//    @PostMapping("/test1")
//    public TestValidation test1(@RequestBody @Validated TestValidation t){
//        return t;
//    }
//
//    @ApiOperation("测试assert参数")
//    @GetMapping("/test2")
//    public String test2(String s){
//        Assert.hasText(s,"s不能为空");
//        return  s;
//    }
//
//    @ApiOperation("查询测试")
//    @GetMapping("/select")
//    //@ApiImplicitParam(name="name",value="用户名",dataType="String", paramType = "query")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query"),
//            @ApiImplicitParam(name="id",value="用户id",dataType="int", paramType = "query",example = "1")})
//    //传入独立参数
//    public String select(int id,String name){
//        return id+name;
//    }
//
//    @ApiOperation("插入数据测试")
//    @GetMapping("/add")
//    public String add(String sb) throws InterruptedException {
//        System.out.println(sb);
//        return testService.add();
//
//    }
//    @ApiOperation("删除数据测试")
//    @GetMapping("/del")
//    public void del(){
//        testService.del();
//    }
//
//    @ApiOperation("查询数据测试")
//    @GetMapping("/get")
//    public void get(){
//        testService.getOne();
//        testService.getByExampkle();
//    }
//
//
//
//
//    //@PathVariable可以把参数以占位符的方式映射到方法中
//    @ApiIgnore
//    @GetMapping("/as1/{area}")
//    public String as1(@PathVariable("area") String area){
//        util.addTest();
//        return "successful==="+area;
//    }
//
//    //没有ApiIgnore的方法都会显示在swagger中
//    @ApiIgnore
//    @GetMapping("/as2")
//    public String as2(){
//        util.addPoll();
//        return  "change  successful";
//    }
}
