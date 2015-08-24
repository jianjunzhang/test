package cn.buaa.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.buaa.model.TUser;
import cn.buaa.service.IUserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml", "classpath:spring-hibernate.xml"})
public class Test1 {
	/*
	private IUserService userService;
	
	public IUserService getUserService() {
		return userService;
	}
	@Resource
	dasdasdasdasda
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	private static final Logger logger = Logger.getLogger(Test1.class);
	@Resource
	private SessionFactory sessionFactory;
	@Before
	public void setUp(){
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
	}*/
	

	@Test
	public void test01(){
		/*这回会报org.hibernate.HibernateException: No Session found for current thread
		 * ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml", "classpath:spring-hibernate.xml" });
		IUserService userService = (IUserService) ac.getBean("userService");
		TUser user = new TUser(103, "www", "多得多", new Date(), new Date(), "dd");
		userService.add(user);*/
	}
}
