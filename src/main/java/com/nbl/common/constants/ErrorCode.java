package com.nbl.common.constants;

/**
 * 用于定义错误码和错误信息 <br>
 * 错误码规则：应用类型分类{2位}+错误类型分类{1位}+错误序号{3位}<br>
 * 应用类型分类：<br>
 * US——zlebank-energy-user<br>
 * 错误类型分类：<br>
 * C-信息校验类<br>     
 * B-业务信息规则类<br> 
 * D-数据库操作类<br>   
 * T-系统间通讯类<br>   
 * E-其他系统异常类
 * @version 1.0.0
 * @author AlanMa
 *
 */
public class ErrorCode {
    /**
     * 手机号[%s]已注册，不可重复注册
     */
    public static final String USC001 = "USC001|手机号[%s]已注册，不可重复注册";
    /**
     * 手机号[%s]未注册，请先注册
     */
    public static final String USC002 = "USC002|手机号[%s]未注册，请先注册";
    /**
     * 密码错误
     */
    public static final String USC003 = "USC003|密码错误";
    /**
     * 原支付密码错误，请重新输入
     */
    public static final String USC004 = "USC004|原支付密码错误，请重新输入";
    /**
     * 支付密码初始值非空
     */
    public static final String USC005 = "USC005|支付密码初始值非空";
    /**
     * 未进行实名身份校验，无法充值
     */
    public static final String USC006 = "USC006|未进行实名身份认证，无法充值";
    /**
     * 未绑定银行卡，无法充值
     */
    public static final String USC007 = "USC007|未绑定银行卡，无法充值";
    /**
     * 此用户已认证，无需重复认证
     */
    public static final String USC008 = "USC008|此用户已认证，无需重复认证";
    /**
     * 密码输入错误超过3次，账号暂时锁定，请稍后再尝试登录
     */
    public static final String USC009 = "USC009|密码输入错误超过3次，账号暂时锁定，请稍后再尝试登录";
    /**
     * 请先【设置支付密码】
     */
    public static final String USC010 = "USC010|请先【设置支付密码】";
    /**
     * 原登录密码错误，请重新输入
     */
    public static final String USC011 = "USC004|原登录密码错误，请重新输入";
    /**
     * 查询[%s]结果为空
     */
    public static final String USD001 = "USD001|查询[%s]结果为空";
    /**
     * 更新登录锁定状态异常
     */
    public static final String USD002 = "USD002|更新登录锁定状态异常";
    /**
     * 实体[%s]不存在，更新失败
     */
    public static final String USD003 = "USD003|实体[%s]不存在，更新失败";
    /**
     * 时间转换异常
     */
    public static final String USE001 = "USE001|时间转换异常";
    /**
     * 绑卡时输入参数校验是否与实名认证时参数值相同
     */
    public static final String USC012 ="USC012|输入参数与实名认证参数不同";
}
