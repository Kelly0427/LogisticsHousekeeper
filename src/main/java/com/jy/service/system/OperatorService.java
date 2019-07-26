package com.jy.service.system;

import com.jy.domain.system.Operator;

public interface OperatorService {
    //注册
	public Operator addOperator(Operator operator);
	//获取当前用户姓名
	String operatorRealName();
	//获取当前用户
	String operatorName();
	//获取当前用户手机号
	String operatorPhone();
	//获取当前用户身份
	String operatorRole();
}
