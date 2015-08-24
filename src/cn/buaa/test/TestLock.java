package cn.buaa.test;

import org.hibernate.Session;
import org.junit.Test;

import cn.buaa.model.Student;
import cn.buaa.util.HibernateUtil;

public class TestLock {
	
	@Test
	public void test01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			//这个是悲观锁   加LockOptions.UPGRADE
			//Student stu =(Student) session.load(Student.class, 1,LockOptions.UPGRADE);
			Student stu =(Student) session.load(Student.class, 1);
			stu.setName("王亚楠");
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
		}finally{
			HibernateUtil.close(session);
			
		}
	}
	
	@Test
	public void test02(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			//Student stu =(Student) session.load(Student.class, 1,LockOptions.UPGRADE);
			Student stu =(Student) session.load(Student.class, 1);
			//乐观锁会提示异常
			//org.hibernate.StaleObjectStateException: Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect): [cn.buaa.model.Student#1]
			stu.setSex("男");
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
		}finally{
			HibernateUtil.close(session);
			
		}
	}
}
