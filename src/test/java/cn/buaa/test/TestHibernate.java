package cn.buaa.test;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import cn.buaa.model.TUser;
import cn.buaa.service.IUserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml", "classpath:spring-hibernate.xml"})
public class TestHibernate {

	private IUserService userService;
	
	public IUserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@Resource
	private SessionFactory sessionFactory;
	@Before
	public void setUp(){
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
	}
	@Test
	public void test01(){
		TUser user = new TUser(10, "www", "多得多", new Date(), new Date(), "dd");
		userService.add(user);
	}
}
