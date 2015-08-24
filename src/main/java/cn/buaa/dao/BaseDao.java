package cn.buaa.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.buaa.model.Pager;
import cn.buaa.model.SystemContext;
@SuppressWarnings("unchecked")
public class BaseDao<T> implements IBaseDao<T> {
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		//这个不行return sessionFactory.openSession();
		//因为由spring管理，所有不用开启，只要得到当前的
		return sessionFactory.getCurrentSession();
	}
	private Class<T> clz;
	
	public Class<T> getClz() {
		if (clz==null) {
			clz = ((Class<T>)
					(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}
	@Override
	public void add(T t){
		this.getSession().save(t);
	}
	
	@Override
	public void update(T t){
		this.getSession().update(t);
	}
	
	@Override
	public void delete(int id){
		this.getSession().delete(this.load(id));
	}
	@Override
	public T load(int id){
		//System.out.println(this.getSession());
		T t = (T) this.getSession().load(getClz(), id);
		return t;
	}
	
	@Override
	public List<T> list(String hql,Object[] args){
		Query query = this.getSession().createQuery(hql);
		if (args!=null) {
			for(int i=0;i<args.length;i++){
				query.setParameter(i, args[i]);
			}
		}
		return query.list();
	}

	@Override
	public List<T> list(String hql) {
		
		return this.list(hql, null);
	}

	@Override
	public List<T> list(String hql, Object args) {
		
		return this.list(hql, new Object[]{args});
	}

	@Override
	public Pager<T> find(String hql, Object[] args) {
		Pager<T> pages = new Pager<T>();
		int pageOffset = SystemContext.getPageOffset();
		int pageSize = SystemContext.getPageSize();
		Query q = this.getSession().createQuery(hql);
		Query cq = this.getSession().createQuery(getCountHql(hql));
		if(args!=null) {
			int index = 0;
			for(Object arg:args) {
				q.setParameter(index, arg);
				cq.setParameter(index, arg);
				index++;
			}
		}
		long totalRecord = (Long)cq.uniqueResult();
		q.setFirstResult(pageOffset);
		q.setMaxResults(pageSize);
		List<T> datas = q.list();
		pages.setDatas(datas);
		pages.setPageOffset(pageOffset);
		pages.setPageSize(pageSize);
		pages.setTotalRecord(totalRecord);
		return pages;
	}
	
	private String getCountHql(String hql) {
		//1、获取from前面的字符串
		String f = hql.substring(0, hql.indexOf("from"));
		//2、将from前面的字符串替换为select count(*) 
		if(f.equals("")) {
			hql = "select count(*) "+hql;
		} else {
			hql = hql.replace(f, "select count(*) ");
		}
		//3、将fetch替换为""，因为抓取查询不能使用count(*)
		hql = hql.replace("fetch"," ");
		return hql;
	}

	@Override
	public Pager<T> find(String hql, Object arg) {
		return this.find(hql,new Object[]{arg});
	}

	@Override
	public Pager<T> find(String hql) {
		return this.find(hql,null);
	}
	
}
