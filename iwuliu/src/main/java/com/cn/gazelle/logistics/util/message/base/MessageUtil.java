package com.cn.gazelle.logistics.util.message.base;

/**
 * Created by CYH on 2015/12/15.
 */
public class MessageUtil {
    //提示信息
    public static final String loginTip = "已经入登录页面";
    public static final String seacheTip = "已查到用户信息：";
    public static final String seacheInfo = "查询所有用户完成！";
    public static final String seacheInfoError = "询所有用户信息异常：";
    public static final String saveInfo = "保存用户信息完成！";
    public static final String saveInfoError = "保存数据异常:";
    public static final String delInfoError = "删除用户异常:";
    public static final String delInfo = "删除用户信息完成！";
    public static final String selectUserCount = "用户总记录数查询完成！";
    public static final String getSelectUserCountError = "用户总记录数查询失败！";
    public static final String saveDoubleInfoError = "保存重复信息！";

    //登录验证常量代码
    public static final String systemErrorNUM = "0";                                 // 系统错误
    public static final String verifyCodeNullNUM = "1";                              // 验证码为空
    public static final String verifyCodeErrorNUM = "2";                             // 手机号或验证码错误
    public static final String verifyUserPassNullNUM = "3";                          // 用户名密码为空
    public static final String verifyUserPassErrorNUM = "4";                         // 用户名密码错误
    public static final String verifyUserPassSuccessNUM = "5";                       // 用户名密码验证通过
    public static final String editPassSuccessNUM = "6";                             // 修改密码成功
    public static final String unRegisteredNUM = "7";                                // 手机号码未注册
    public static final String sendMsmNUM = "8";                                     // 短信已发送
    public static final String noSendMsmNUM = "9";                                   // 未获取验证码
    public static final String editPassErrorNUM = "10";                              // 未获取验证码
    public static final String pwsRepeatNUM = "11";                                  // 重置密码与原密码重合
    public static final String codeOutTimeNUM = "12";                                // 验证码超时

    public static final String systemError = "系统错误";                             // 系统错误
    public static final String verifyCodeNull = "验证码为空";                        // 验证码为空
    public static final String verifyCodeError = "验证码错误";                       // 手机号或验证码错误
    public static final String verifyUserPassNull = "用户名或密码为空";              // 用户名密码为空
    public static final String verifyUserPassError = "用户名或密码错误";             // 用户名密码错误
    public static final String verifyUserPassSuccess = "用户名密码验证通过";         // 用户名和密码验证通过
    public static final String editPassSuccess = "修改密码成功";                     // 修改密码成功
    public static final String editPassError = "修改密码失败";                       // 修改密码失败
    public static final String unRegisteredError = "手机号码未注册";                 // 修改密码成功
    public static final String sendMsmSuccess = "短信发送成功";                      // 短信发送成功
    public static final String noSendMsmError = "未获取验证码";                      // 未获取验证码
    public static final String pwsRepeatError = "重置密码与原密码重合";              // 未获取验证码
    public static final String codeOutTimeError = "验证码超时";                     // 验证码超时

    public static final String getSelectUserGroupCountError = "用户组总记录数查询失败！";
    public static final String getSelectAuthorityCountError = "权限总记录数查询失败！";


    public MessageUtil() {
    }
}
