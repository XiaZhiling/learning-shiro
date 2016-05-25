/*
 * Licensed to the personal for xzl
 * https://github.com/XiaZhiling
 * See the License for the specific language governing permissions and limitations
 * under the License.
 * @version v1.0
 */
package com.ling.learning.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @ClassName: ShiroTest 
* @Description: Shiro 测试相关 
* @author xzl<l576477316@foxmail.com>
* @date 2016年5月25日 下午6:30:48  
*/
public class ShiroTest {
	
	private static Logger log = LoggerFactory.getLogger(ShiroTest.class);
	
	@Test
	public void testAuthentication(){

		//1.获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//2.得到SecurityManager实例 并绑定给SecurityUtils 
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//3.得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		
		try{
			subject.login(token);
		}catch(Exception e){
			log.error(e.getMessage());
		}
		if(log.isInfoEnabled()){
			log.info("Subject is authenticated:{}", subject.isAuthenticated());
		}
		Assert.assertEquals(true, subject.isAuthenticated());
	}
	
	/**
	 * 自定义Reaml测试
	 */
	@Test
	public void testMyRealm1(){
		log.info("Start");

		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro1-realm.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123",false);
		try{
			subject.login(token);
		}catch(Exception e){
			log.error(e.getMessage());
		}
		if(log.isInfoEnabled()){
			log.info("Subject is authenticated:{}", subject.isAuthenticated());
		}
	}
}
