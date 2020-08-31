//package com.rog.adminpermission.service.user;
//
//import com.tyn.blog.dao.admin.UserMapper;
//import com.tyn.blog.po.admin.User;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.AsyncResult;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.concurrent.Future;
//
///**
// * @author wangl
// * @Description todo
// * @date 1/7/2020 11:42 AM
// */
//@Service
//@Slf4j
//public class TestService1 {
//
//    @Autowired
//    private UserMapper userMapper;
//
//
//    //@Async的方法返回值只能是void和Future
//    @Transactional(rollbackFor = Exception.class)
//    @Async
//    public Future<String> add2() throws InterruptedException {
//        User u = new User();
//        u.setAccount("caixu");
//        u.setRemake("sb");
//        userMapper.insert(u);
//        log.info("插入id"+u.getId());
//        // if (1 == 1) throw new AccountException(ResultCode.SYSTEM_ERROR);
//        return new AsyncResult<>("完成");
//    }
//}
