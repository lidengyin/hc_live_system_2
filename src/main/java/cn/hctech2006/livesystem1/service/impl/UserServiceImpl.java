package cn.hctech2006.livesystem1.service.impl;


import cn.hctech2006.livesystem1.bean.MmallUser;
import cn.hctech2006.livesystem1.common.Const;
import cn.hctech2006.livesystem1.common.ServerResponse;
import cn.hctech2006.livesystem1.common.TokenCache;
import cn.hctech2006.livesystem1.mapper.MmallUserMapper;
import cn.hctech2006.livesystem1.service.IUserService;
import cn.hctech2006.livesystem1.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private MmallUserMapper userMapper;
    @Override
    public int save(MmallUser record) {
        return 0;
    }

    @Override
    public int delete(MmallUser record) {
        return 0;
    }

    @Override
    public int delete(List<MmallUser> records) {
        return 0;
    }

    @Override
    public MmallUser findById(Long id) {
        return null;
    }

    @Override
    public int update(MmallUser record) {
        return 0;
    }

    @Override
    public int update(List<MmallUser> records) {
        return 0;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse<MmallUser> login(String username, String password) {
        //检查用户名是否存在
        int resultCount = userMapper.checkUsername(username);
        if (resultCount == 0){
            return ServerResponse.createByError("用户名不存在");
        }
        //todo 密码登录MD5
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        System.out.println("md5Password:"+md5Password);
        MmallUser user = userMapper.selectLogin(username, md5Password);
        //验证密码是否错误
        if (user == null){
            return ServerResponse.createByError("密码错误");
        }
        //通过设置NULL，JSON不再返回密码字段
        user.setPassword(null);
        return ServerResponse.createBySuccess("登录成功",user);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public ServerResponse<String> registry(MmallUser user) {
        //封装注册时校验用户名以及邮箱是否已经注册函数
        //检查用户名是否存在
        ServerResponse validResponse = registryValid(user.getUsername(), Const.USERNAME);
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        //检查邮箱是否存在
        validResponse = registryValid(user.getEmail(), Const.EMAIL);
        if (!validResponse.isSuccess()){
            return validResponse;
        }
        //设置用户角色
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //设置密码MD5加密
        System.out.println("RegisterPassword:"+user.getPassword());
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        System.out.println("RegisterM5Password:"+user.getPassword());


        int resultCount = userMapper.insert(user);
        if (resultCount == 0){
            return ServerResponse.createByError("注册失败");
        }
        return ServerResponse.createBySuccess("注册成功");
    }

    /**
     * 注册时校验用户名以及邮箱是否已经注册
     * @param str
     * @param type
     * @return
     */
    @Override
    public ServerResponse<String> registryValid(String str, String type) {
        //判断验证类型是否为空（包括空格）
        if (StringUtils.isNoneBlank(type)){
            //判断校验类型是否为用户名
            if (Const.USERNAME.equals(type)){
                //检查用户名是否存在
                int resultCount = userMapper.checkUsername(str);
                if (resultCount > 0){
                    return ServerResponse.createByError("用户名已存在，重复注册");
                }
            }
            //判断校验类型是否为邮箱
            if (Const.EMAIL.equals(type)){
                //检查邮箱是否存在
                int resultCount = userMapper.checkEmail(str);
                if(resultCount > 0){
                    return ServerResponse.createByError("邮箱已注册，重复注册");
                }
            }
        }else{
            return ServerResponse.createByError("参数错误");
        }
        return ServerResponse.createBySuccess("校验成功");

    }

    /**
     * 用户找回密码时设置的问题
     * @param username
     * @return
     */
    @Override
    public ServerResponse<String> forgetGetQuestion(String username) {
        //判断用户名是否已经被注册
        ServerResponse validResponse = registryValid(username, Const.USERNAME);
        //如果没有注册的话,
        if(validResponse.isSuccess()){
            return ServerResponse.createByError("用户不存在");
        }
        //如果已经注册的话
        String question = userMapper.selectQuestionByUsername(username);
        if (StringUtils.isNoneBlank(question)){
            return ServerResponse.createBySuccess(question);
        }
        return ServerResponse.createByError("该用户找回密码的问题是空的");
    }

    /**
     * 判断回答是否正确
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        //判断找回密码回答的问题是否正确
        int resultCount = userMapper.checkAnswer(username,question,answer);
        //如果回答正确
        if (resultCount > 0){
            //说明问题及问题答案是这个用户的，并且是正确的
            //将找回密码那令牌本地缓存
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIC+username, forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByError("问题的答案错误");
    }

    /**
     * 忘记密码的重置密码功能
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @Override
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken)){
            return ServerResponse.createByError("参数错误，token需要传递");
        }
        //判断用户名是否已经被注册
        ServerResponse validResponse = registryValid(username, Const.USERNAME);
        //如果没有注册的话,
        if(validResponse.isSuccess()){
            return ServerResponse.createByError("用户不存在");
        }
        //获取token
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIC+username);
        //判断token是否在有效期内
        if (StringUtils.isBlank(token)){
            return ServerResponse.createByError("token无效或者已经过期");
        }
        //判断token是否一致
        if (StringUtils.equals(token,forgetToken)){
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            int rowCount = userMapper.updatePasswordByUsername(username,md5Password);
            //判断修改是否成功
            if (rowCount > 0){
                return ServerResponse.createBySuccess("密码修改成功");
            }else{
                return ServerResponse.createByError("密码修改失败");
            }

        }else{
            return ServerResponse.createByError("token错误，请重新获取重置密码的token");
        }
    }

    /**
     * 登录状态修改密码
     * @param passwordOld
     * @param passwordNew
     * @param user
     * @return
     */
    @Override
    public ServerResponse<String> restPassword(String passwordOld, String passwordNew, MmallUser user) {
        //防止横向越权，要校验一下这个用户的旧密码，一定要指定是这个用户，
        // 因为我们会查询一个count(1),如果不指定id,那么结果就是true,count＞０
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld), user.getId());
        if (resultCount == 0){
            return ServerResponse.createByError("旧密码错误");

        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        //判断用户修改是否成功
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0){
            return ServerResponse.createBySuccess("密码修改成功");

        }
        return ServerResponse.createByError("密码修改失败");

    }

    /**
     * 个人信息更新
     * @param user
     * @return
     */
    @Override
    public ServerResponse<MmallUser> updateInformation(MmallUser user) {
        //username是不能被更新的
        //email也要进行一个校验，校验新的email是否已经被其他用户注册
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(), user.getId());
        if (resultCount > 0){
            return ServerResponse.createByError("email已经被其他用户注册");
        }
        MmallUser updateUser = new MmallUser();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());

        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount > 0){
            return ServerResponse.createBySuccess("更新个人信息成功", updateUser);
        }
        return ServerResponse.createByError("更新个人信息失败");
    }

    /**
     * 获取当前用户信息
     * @param userId
     * @return
     */
    @Override
    public ServerResponse<MmallUser> get_information(Integer userId) {
        MmallUser user = userMapper.selectByPrimaryKey(userId);
        if (user == null){
            return ServerResponse.createByError("找不到当前用户");
        }
        //设置密码字段为空，序列化设置不会返回空字段
        user.setPassword(null);
        return ServerResponse.createBySuccess(user);
    }

    /**
     * 校验是否拥有管理员权限
     * @param user
     * @return
     */
    @Override
    public ServerResponse checkAdminRole(MmallUser user) {
        if (user != null && user.getRole().intValue() == Const.Role.ROLE_ADMIN){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

}
