package com.tingyun.thrift.server;

import com.tingyun.controller.thriftdemo2.ServerMain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tingyun on 2017/5/26.
 */

@Controller
public class ThriftServerController {

    @RequestMapping(value = "/server.do")
    public void serverStart(){
        ServerMain server1=new ServerMain(7911);
        ServerMain server2=new ServerMain(7912);
        server1.start();
        server2.start();
    }
}
