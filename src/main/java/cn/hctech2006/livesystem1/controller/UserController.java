package cn.hctech2006.livesystem1.controller;


import cn.hctech2006.livesystem1.bean.MmallUser;
import cn.hctech2006.livesystem1.common.Const;
import cn.hctech2006.livesystem1.common.ResponseCode;
import cn.hctech2006.livesystem1.common.ServerResponse;
import cn.hctech2006.livesystem1.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

/**
 * 前台用户接口设计－控制层
 * Created by Lidengyin
 */
@Api(tags = "前台用户信息接口")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;


    @RequestMapping("/page")

    public String uploadPage(){
        return "upload.html";
    }
    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */

    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "username", value = "用户名", required = true),
            @ApiImplicitParam(type = "query", name = "password", value = "密码", required = true)
    })
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<MmallUser> login(@RequestParam(name = "username",required = true) String username,
                                           @RequestParam(name = "password",required = true) String password, @ApiIgnore HttpSession session){
        //获取登录结果
        ServerResponse response =  userService.login(username,password);
        //获取登录状态
        if (response.getStatus() == ResponseCode.SUCCESS.getCode()){
            System.out.println("登录成功,在session中缓存当前用户: ");
            //在session中缓存当前用户
            session.setAttribute(Const.CURRENT_USER, response.getData());
            System.out.println(session.getAttribute(Const.CURRENT_USER));

        }
        return response;
    }

    /**
     * 用户登出
     * @param session
     * @return
     */
    @ApiOperation(value = "用户登出")
    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> logout(@ApiIgnore HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * 用户注册
     * @param
     * @return
     */
    @ApiOperation(value = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "username", value = "用户名", required = true),
            @ApiImplicitParam(type = "query", name = "password", value = "密码", required = true),
            @ApiImplicitParam(type = "query", name = "email", value = "邮箱", required = true),
            @ApiImplicitParam(type = "query", name = "phone", value = "电话", required = true),
            @ApiImplicitParam(type = "query", name = "question", value = "找回密码问题", required = true),
            @ApiImplicitParam(type = "query", name = "answer", value = "找回密码答案", required = true)
    })
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> registry(String username, String password, String email, String phone, String question, String answer){
        MmallUser user = new MmallUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setQuestion(question);
        user.setAnswer(answer);
        //user.setPassword(phone);
        System.out.println("password:"+password);
        return userService.registry(user);
    }

    /**
     * 注册时校验用户名和邮箱是否已经注册
     * @param str
     * @param type
     * @return
     */
    @ApiOperation(value = "注册时校验用户名和邮箱是否已经注册")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "str", value = "邮箱或者用户名值", required = true),
            @ApiImplicitParam(type = "query", name = "type", value = "填写username或者password", required = true)
    })
    @RequestMapping(value = "check_valid.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> registerValid(String str, String type){
        return userService.registryValid(str,type);
    }

    /**
     * 获取用户信息
     * @param session
     * @return
     */
    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<MmallUser> getUserInfo(@ApiIgnore HttpSession session){
        //从Session缓存中获取当前用户
        MmallUser user = (MmallUser) session.getAttribute(Const.CURRENT_USER);
        //判断用户是否为空，如果不为空返回当前用户，否则返回错误信息
        if(user != null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByError("用户未登录，无法获取当前用户信息");
    }

    /**
     * 返回找回密码时设置的问题
     * @param username
     * @return
     */
    @ApiOperation(value = "返回找回密码时设置的问题")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "username", value = "用户名", required = true)

    })
    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username){
        return userService.forgetGetQuestion(username);
    }
    /**
     * 验证忘记密码时回答问题是否正确，正确时返回guava找回密码验证token
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @ApiOperation(value = "验证忘记密码时回答问题是否正确，正确时返回guava找回密码验证token")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "username", value = "用户名", required = true),
            @ApiImplicitParam(type = "query", name = "question", value = "问题", required = true),
            @ApiImplicitParam(type = "query", name = "answer", value = "答案", required = true)

    })
    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username,String question, String answer){
        return userService.checkAnswer(username, question,answer);
    }

    /**
     * 忘记密码的重置密码功能
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @ApiOperation(value = "忘记密码的重置密码功能")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "username", value = "用户名", required = true),
            @ApiImplicitParam(type = "query", name = "passwordNew", value = "新密码", required = true),
            @ApiImplicitParam(type = "query", name = "forgetToken", value = "忘记密码token", required = true)

    })
    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.PUT)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken){
        return userService.forgetRestPassword(username,passwordNew,forgetToken);
    }

    /**
     * 登录状态修改密码
     * @param session
     * @param passwordOld
     * @param passwordNew
     * @return
     */
    @ApiOperation(value = "登录状态修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "username", value = "用户名", required = true),
            @ApiImplicitParam(type = "query", name = "passwordOld", value = "旧密码", required = true),
            @ApiImplicitParam(type = "query", name = "passwordNew", value = "新密码", required = true)

    })
    @RequestMapping(value = "reset_password.do", method = RequestMethod.PUT)
    @ResponseBody
    public ServerResponse<String> restPassword(@ApiIgnore HttpSession session
            , String passwordOld, String passwordNew){
        //判断当前登录用户是否为空
        MmallUser user = (MmallUser) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByError("用户未登录");
        }
        return userService.restPassword(passwordOld,passwordNew,user);

    }

    /**
     * 修改当前用户信息
     * @param session
     *
     * @return
     */
    @ApiOperation(value = "修改当前用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "username", value = "用户名", required = true),
            @ApiImplicitParam(type = "query", name = "password", value = "密码", required = true),
            @ApiImplicitParam(type = "query", name = "email", value = "邮箱", required = true),
            @ApiImplicitParam(type = "query", name = "phone", value = "电话", required = true),
            @ApiImplicitParam(type = "query", name = "question", value = "找回密码问题", required = true),
            @ApiImplicitParam(type = "query", name = "answer", value = "找回密码答案", required = true)

    })
    @RequestMapping(value = "update_information.do", method = RequestMethod.PUT)
    @ResponseBody
    public ServerResponse<MmallUser> updateInformation(@ApiIgnore HttpSession session, String email, String phone, String question, String answer){
            MmallUser user = new MmallUser();
            user.setEmail(email);
            user.setQuestion(question);
            user.setAnswer(answer);
            user.setPassword(phone);
            //判断当前登录用户是否为空
            MmallUser currentUser = (MmallUser) session.getAttribute(Const.CURRENT_USER);
            if (currentUser == null){
                return ServerResponse.createByError("用户未登录");
            }
            //防止横向越权设置要修改的用户ID为当前用户ID
            user.setId(currentUser.getId());
            //防止横向越权设置要修改的用户名为当前用户名
            user.setUsername(currentUser.getUsername());
            ServerResponse response = userService.updateInformation(user);
            //判断更新是否成功，如果成功，更新当前用户信息
            if (response.isSuccess()){
                session.setAttribute(Const.CURRENT_USER, response.getData());

            }
            return response;
    }

    /**
     * 获取当前用户信息
     * @param session
     * @return
     */
    @ApiOperation(value = "获取当前用户信息")
    @RequestMapping(value = "get_information.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<MmallUser> get_information(@ApiIgnore HttpSession session){
        //判断当前登录用户是否为空
        MmallUser currentUser = (MmallUser) session.getAttribute(Const.CURRENT_USER);

        if (currentUser == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,需要前端强制登录，status=10");
        }
        return userService.get_information(currentUser.getId());
    }
}
