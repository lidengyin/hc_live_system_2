package cn.hctech2006.livesystem1.mapper;


import cn.hctech2006.livesystem1.bean.MmallUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MmallUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MmallUser record);

    MmallUser selectByPrimaryKey(Integer id);

    List<MmallUser> selectAll();

    int updateByPrimaryKey(MmallUser record);

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     */
    int checkUsername(String username);

    /**
     * 返回当前登录对象
     * @param username
     * @param password
     * @return
     */
    MmallUser selectLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 检查邮箱是否存在
     * @param email
     * @return
     */
    int checkEmail(String email);

    /**
     * 获取该用户找回密码设置的问题
     * @param username
     * @return
     */
    String selectQuestionByUsername(String username);

    /**
     * 回答找回密码设置的问题
     * @param username
     * @param question
     * @param answer
     * @return
     */
    int checkAnswer(String username, String question, String answer);

    /**
     * 通过用户名更新密码
     * @param username
     * @param passwordNew
     * @return
     */
    int updatePasswordByUsername(String username, String passwordNew);

    /**
     * 检查密码防止横向越权
     * @param password
     * @param userId
     * @return
     */
    int checkPassword(@Param(("password")) String password, @Param("userId") Integer userId);

    /**
     * 选择性更新字段
     * @param user
     * @return
     */
    int updateByPrimaryKeySelective(MmallUser user);

    /**
     * 检测邮箱是否被其他用户注册
     * @param email
     * @param userId
     * @return
     */
    int checkEmailByUserId(String email, Integer userId);


}