package cn.hctech2006.livesystem1.controller;

import cn.hctech2006.livesystem1.server.RealTimeWebSocketServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "WebSocket长握手管理接口")
@RestController
@RequestMapping(value = "/live")
public class liveController {
    @Autowired
    private RealTimeWebSocketServer realTimeWebSocketServer;
    @ApiOperation(value = "服务端实时发送信息到客户端")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "message", value ="需要发送的信息", required = true)
    })
    @RequestMapping(value = "/web",method = RequestMethod.POST)
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public void webSocket( String message){
        realTimeWebSocketServer.onMessage(message);
    }
}
