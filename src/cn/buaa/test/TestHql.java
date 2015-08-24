package cn.buaa.test;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import cn.buaa.model.Student;
import cn.buaa.model.StudentDto;
import cn.buaa.util.HibernateUtil;

@SuppressWarnings("unchecked")
public class TestHql {
	
	@Test
	public void test01(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			List<Student> list = session.createQuery("from Student where name like :name and sex=:sex")
					.setParameter("name", "%张%").setParameter("sex", "男").list();
			//List<Special> list = session.createQuery("from Special").list();
			for(Student stu:list){
				System.out.println(stu.getName());
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
	public void test02(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			//Long stus = (Long) session.createQuery("select count(*) from Student").uniqueResult();
			//System.out.println(stus);
			Student stu =(Student) session.createQuery("select stu from Student stu where id=:id")
					.setParameter("id", 1).uniqueResult();
			System.out.println(stu.getName());
			
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
			/**
			 * 基于投影的查询，通过在列表中存储一个对象的数组
			 */
			List<Object[]> list = session.createQuery("select stu.sex,count(*) from Student stu group by stu.sex").list();
			for(Object[] obj:list){
				System.out.println(obj[0]+"==="+obj[1]);
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
			 * 如果对象中相应的导航对象，可以直接导航完成查询
			 */
			List<Student> list = session.createQuery("from Student stu where stu.classroom.name=? and stu.name like ?")
					.setParameter(0, "计算机教育班").setParameter(1, "%张%").list();
			for(Student stu:list){
				System.out.println(stu.getName());
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
	public void test05(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			/**
			 * 可以使用in来设置基于列表的查询，此处的查询需要使用别名进行查询
			 * 特别注意，使用in的查询必须在其他的查询之后
			 */
			List<Student> list = session.createQuery("from Student stu where stu.name like ? and stu.classroom.id in(:in)")
					.setParameter(0, "%张%").setParameterList("in", new Integer[]{1,2}).list();
			for(Student stu:list){
				System.out.println(stu.getName());
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
	public void test06(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			/**
			 * 使用setFirstResult和setMaxResult可以完成分页的offset和pageSize的设置
			 */
			List<Student> list = session.createQuery("from Student stu where stu.name like ? and stu.classroom.id in(:in)")
					.setParameter(0, "%张%").setParameterList("in", new Integer[]{1,2}).setFirstResult(0).setMaxResults(5).list();
			for(Student stu:list){
				System.out.println(stu.getName());
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
	public void test07(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			/**
			 * 可以通过is null来查询为空的对象
			 */
			List<Student> list = session.createQuery("from Student stu where stu.classroom is null")
					.list();
			for(Student stu:list){
				System.out.println(stu.getName());
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
	public void test08(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			/**
			 * 使用对象的导航可以完成连接，但是是基于Cross JOIN，效率不高，可以直接使用JOIN来完成连接
			 */
			//List<Student> list = session.createQuery("select stu from Student stu left join stu.classroom cla where cla.id=2")
			List<Student> list = session.createQuery("select stu from Student stu left join stu.classroom cla where cla.id=2")
					.list();
			for(Student stu:list){
				System.out.println(stu.getName());
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
	public void test09(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			//这里用right，因为最前面显示的是select cla.name,stu.sex,中的cla.name，它是classroom里面的 
			//List<Object[]> list = session.createQuery("select cla.name,stu.sex,count(stu.id) from Student stu right join stu.classroom cla group by cla.id,stu.sex")
			List<Object[]> list = session.createQuery("select cla.name,count(stu.classroom.id) from Student stu right join stu.classroom cla group by cla.id")
					.list();
			for(Object[] stu:list){
				//System.out.println(stu[0]+","+stu[1]+","+stu[2]);
				System.out.println(stu[0]+","+stu[1]);
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
	public void test10(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			/**
			 * 直接可以使用new XXObject完成查询，注意，一定要加上Object的完整包名
			 * 这里使用的new XX，必须在对象中加入相应的构造函数
			 */
			/*List<Object[]> list = session.createQuery("select stu.id,stu.name,stu.sex,cla.name,spe.name " +
					"from Student stu left join stu.classroom cla left join cla.special spe")
					.list();
			for(Object[] stu:list){
				//System.out.println(stu[0]+","+stu[1]+","+stu[2]);
				System.out.println(stu[0]+","+stu[1]+""+stu[2]+","+stu[3]+","+stu[4]);
			}*/
			List<StudentDto> list = session.createQuery("select " +
					"new cn.buaa.model.StudentDto(stu.id as sid,stu.name as sname,stu.sex as sex,cla.name as cname,spe.name as spename) " +
					"from Student stu left join stu.classroom cla left join cla.special spe")
					.list();
			for(StudentDto sd:list){
				System.out.println(sd.getSid()+","+sd.getCname()+","+sd.getSex()+","+sd.getCname()+","+sd.getSpename());
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
	public void test11(){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			/**
			 * having是为group来设置条件的
			 */
			/*List<Object[]> stus = session.createQuery("select spe.name," +
					"(count(stu.classroom.special.id)) from Student stu right join " +
					"stu.classroom.special spe group by spe having count(stu.classroom.special.id)>150")
					.list();*/
			List<Object[]> stus = session.createQuery("select stu.sex,spe.name," +
					"(count(stu.classroom.special.id)) from Student stu right join " +
					"stu.classroom.special spe group by spe,stu.sex")
					.list();
			for(Object[] obj:stus) {
				System.out.println(obj[0]+":"+obj[1]+":"+obj[2]);
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
	
}
