package com.lx.job.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {

	SUCCESS(200, "OK"),
	
	SYSTEM_ERR(10001, "系统错误"),
	PERMISSION_DENIED(10002, "权限不足"),
	USER_TYPE_ERROR(10003, "用户类型错误"),
	ENCODER_ERR(10004, "url路径编码失败"),
	
	UNSIGN_ERR(20002, "未登录"),
	PASSWORD_ERR(20003, "用户名或密码错误"),
	SMS_SIGN_ERR(20004, "短信验证错误"),
	UNKOWN_USER_ERR(20005, "用户名或密码错误"),
	USER_EXIST_ERR(20006, "用户已存在"),
	SMS_SIGN_EXPIRE_ERR(20007, "短信验证码已过期"),
	USER_DISABLED_ERR(20008, "该用户被禁用"),
	
	PARAMS_ERR(30001, "参数错误"),
	PARAMS_SIGN_ERR(30002, "参数校验错误"),
	NAMED_PARAMS_SIGN(30003, "参数非法命名"),
	
	GET_WX_PAY_CODE_ERR(40001, "获取微信支付流水号错误"),
	UNMATCH_ORDER_TYPE(40002, "未匹配到订单类型"),
	GET_WX_AT_ERR(40003, "获取微信小程序accessToken失败"),
	SEND_WX_MESSAGE_ERR(40004, "发送微信小程序订阅消息失败"),
	
	SQL_REPEAT_ERR(50001, "主键重复"),
	
	VISIT_WX_FAIL(60001, "访问微信接口失败"),
	GET_OPENID_FAIL(60002, "获取用户openid失败"),
	
	PRASE_ERR(70001, "数据转换失败"),
	
	PAY_ERR(80001, "支付失败"),
	UNKOWN_PAY_TYPE(80002, "没有匹配到支付类型"),
	UNKOWN_NOTIFY_URL(80003, "获取系统内通知地址失败，该订单已经失效");
	
	private Integer code;
	private String message;
}
