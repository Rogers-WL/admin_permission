//package com.rog.adminpermission.service.user;
//
//import com.tyn.blog.dao.admin.UserMapper;
//import com.tyn.blog.dao.admin.temp.RoleMapper;
//import com.tyn.blog.dao.common.AdminXMLMapper;
//import com.tyn.blog.exception.AccountException;
//import com.tyn.blog.po.admin.User;
//import com.tyn.blog.po.common.AdminXML;
//import com.tyn.blog.utils.SpringUtils.SpringContextUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.interceptor.TransactionAspectSupport;
//import tk.mybatis.mapper.entity.Example;
//
///**
// * @author wangl
// * @Description todo
// * @date 12/20/2019 10:50 AM
// */
//@Service
//@Slf4j
//public class TestService {
//
//    @Autowired
//    private AdminXMLMapper adminXMLMapper;
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private RoleMapper roleMapper;
//
//    @Autowired
//    TestService1 testService1;
//
//    //    @Autowired  // 也可以用此方式注入上下文对象获取bean
//    //    private ApplicationContext context;
//
//
//    public String test(String s) {
//        if (1 == 1) {
//            throw new AccountException(2100, "账号已被封禁");
//        }
//        return s;
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    public void del() {
//        adminXMLMapper.deleteByPrimaryKey(2);
//        throw new RuntimeException();
//    }
//
//    public void getOne() {
//        AdminXML t = new AdminXML();
//        t.setId(2);
//        //t.setAccount("sdfasdf");
//        System.out.println(adminXMLMapper.selectOne(t).toString());//只能查询单条件
//    }
//
//    public void getByExampkle() {
//        Example example = new Example(AdminXML.class);
//        //Criteria在最终结果中以括号形式展现,默认使用and    一个example可以多个createCriteria
//        Example.Criteria criteria = example.createCriteria();
//        example.setOrderByClause("id Desc");
//        //criteria.andLike("name","%虚%");
//        criteria.andEqualTo("account", "sdfasdf");
//        Example.Criteria criteria1 = example.createCriteria();
//        //or用法
//        example.or(criteria1);
//        criteria1.andEqualTo("sex", "1");
//        adminXMLMapper.selectByExample(example).forEach(u -> System.out.println(u.toString()));
//    }
//
//
//    //自增ID不被事务化,innodb的auto_increament的计数器记录的当前值是保存在存内 存中的，并不是存在于磁盘
//    //此异常无法处理，包含两个数据库，事务管理器只对单个数据库有效
//    //需要后续学习分布式多数据源数据一致性的处理
//    //内部调用本类add2方法add2的事务不会生效
//    //@Transactional(rollbackFor = Exception.class)
//    public String add() throws InterruptedException {
////        add1();
////        Future<String> s = testService1.add2();
////        System.out.println(s.isCancelled());
////        System.out.println(s.isDone());
////        if (s.isDone()){
////            return  "插入成功";
////        }else {
////            return "插入失败";
////        }
//
//    SpringContextUtil.getBean(com.tyn.blog.service.TestService.class).add2();
////        Role role = new Role();
////        role.setName("caixu");
////        roleMapper.insert(role);
//    return "插入成功";
//    }
//    // if(1==1) throw new AccountException(ResultCode.SYSTEM_ERROR);
//    //非主数据源必须要在@Transactional注解中指定数据源，否则事务不起作用。主数据库不需要。
//    @Transactional(rollbackFor = Exception.class, value = "db1TransactionManager")
//    public void add1() {
//        AdminXML t = new AdminXML();
//        t.setAccount("sdfasdf");
//        t.setRemake("sb");
//        t.setSex("1");
//        adminXMLMapper.insert(t);
//        System.out.println(t.getId());
//
//    }
//
//    @Async
//    @Transactional(rollbackFor = Exception.class)
//    public void add2() throws InterruptedException {
////            User u = new User();
////            u.setAccount("caixu");
////            u.setRemake("sb");
////            int a = userMapper.insert(u);
////            log.info("插入id"+u.getId());
////
////            Thread.sleep(10000);
////            System.out.println(a);
//        add3();
//           // if (1 == 1) throw new AccountException(ResultCode.SYSTEM_ERROR);
//    }
//
//    //@Transactional(rollbackFor = Exception.class)
//    public void add3() {
//        try {
//            User u = new User();
//            u.setAccount("caixu");
//            u.setRemake("sb");
//            userMapper.insert(u);
//            log.info("插入id"+u.getId());
//            int sb = 3/0;
//            throw new RuntimeException();
//        }catch (Exception e){
//            log.warn(e.getMessage());
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            throw e;
//        }
//
//    }
//
//}
//
//
