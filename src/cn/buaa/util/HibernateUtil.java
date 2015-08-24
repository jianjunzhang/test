package cn.buaa.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	//建出来之后不想修改的就用final了
	private final static SessionFactory  SESSIONFACTORY = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		Configuration cfg = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
							applySettings(cfg.getProperties()).buildServiceRegistry();
		
		return cfg.buildSessionFactory(serviceRegistry);
	}
	public static SessionFactory getSessionFactory(){
		return  SESSIONFACTORY;
	}
	public static Session openSession(){
		return SESSIONFACTORY.openSession();
	}
	public static void close(Session session){
		if (session!=null) {
			session.close();
		}
	}
}
