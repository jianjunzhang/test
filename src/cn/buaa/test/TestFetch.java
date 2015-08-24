package cn.buaa.test;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import cn.buaa.model.Classroom;
import cn.buaa.model.Student;
import cn.buaa.util.HibernateUtil;
@SuppressWarnings("unchecked")
public class TestFetch {
	
	@Test
	public void test01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			Student stu = (Student) session.load(Student.class, 1);
			System.out.println(stu.getName()+","+stu.getClassroom().getName()+","+stu.getClassroom().getSpecial().getName());
			
			
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
	public void test03(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			List<Student> list = session.createQuery("from Student").list();
			for(Student stu:list){
				System.out.println(stu.getName()+","+stu.getClassroom());
			}
					
			
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
	public void test04(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			/**
			 * 2. 另一种方案在HQL中使用fetch来指定抓取
			 * 特别注意，如果使用了join fetch就无法使用count(*)
			 * 要是用count(*)，格式化sql把fetch替换成空就行了
			 */
			List<Student> list = session.createQuery("select stu from Student stu join fetch stu.classroom").list();
			for(Student stu:list){
				System.out.println(stu.getName()+","+stu.getClassroom());
			}
			
			
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
	public void test06() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			
			List<Classroom> clas = session.createQuery("from Classroom").list();
			for(Classroom cla:clas) {
				System.out.println(cla.getName());
				/*
				 * 对于通过HQL取班级列表并且获取相应的学生列表时，fecth=join就无效了
				 * 第一种方案可以设置set的batch-size来完成批量的抓取
				 * 第二中方案可以设置fetch=subselect,使用subselect会完成根据查询出来的班级进行一次对学生对象的子查询
				 */
				for(Student stu:cla.getStus()) {
					System.out.print(stu.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
	}
	
}
