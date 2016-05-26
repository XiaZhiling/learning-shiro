package com.ling.learning.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @ClassName: MyRealm1 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author xzl<l576477316@foxmail.com>
* @date 2016年5月26日 上午10:30:16  
*/
public class MyRealm1 implements Realm{
	
	private static Logger log = LoggerFactory.getLogger(MyRealm1.class);
	@Override
	public String getName() {
		return "myRealm1";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		log.info("userName={},password={}",userName,password);
		if(!"xzl".equals(userName)){
			throw new UnknownAccountException();
		}
		if(!"xzl".equals(password)){
			throw new IncorrectCredentialsException();
		}
		
		return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), getName());
		
	}

}
