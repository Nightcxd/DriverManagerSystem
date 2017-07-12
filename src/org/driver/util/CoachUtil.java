package org.driver.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.driver.bean.Coach;
import org.driver.bean.Trainee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CoachUtil {
	private static final Logger logger = Logger.getLogger(CoachUtil.class);
	private static SessionFactory sessionFactory;

    static {
        Configuration cfg = new Configuration();
        cfg.configure("org/driver/util/hibernate.cfg.xml");
        sessionFactory = cfg.buildSessionFactory();
    }

    /*
    *保存教练
     */
    public void save(Coach m) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(m);//保存
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /*
                  删除指定id的教练
     */
    public void delete(int c_id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Coach st = (Coach) session.get(Coach.class, c_id);
            session.delete(st);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /*
    更新教练
     */
    public void update(Coach m) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(m);//保存
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /*
    通过id获取一个教练
     */
    public Coach getById(String c_id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Coach m = (Coach) session.get(Coach.class, c_id);//获取
            tx.commit();
            return m;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    

    /*
    查询全部教练
     */
    public List<Coach> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Coach> list = session.createQuery("From Coach").list();
            tx.commit();
            return list;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        
    }
    /*
    分页的查询
     */
    public QueryResult<Coach> getCoachList(int firstResult, int maxResult) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //查询总记录数量
            Long count = (Long) session.createQuery(
                    "select count(*) from Coach")
                    .uniqueResult();//执行查询
            //查询一段数据
            Query query = session.createQuery("From Coach");
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
            List<Coach> list = query.list();
            tx.commit();
            return new QueryResult<Coach>(list, count);
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

   
      public static void main(String[] args) {
    	 
        
    }
}
