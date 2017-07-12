package org.driver.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.driver.bean.Coach;
import org.driver.bean.DriverCar;
import org.driver.bean.Trainee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DriverCarUtil {
	private static final Logger logger = Logger.getLogger(DriverCarUtil.class);
	private static SessionFactory sessionFactory;

    static {
        Configuration cfg = new Configuration();
        cfg.configure("org/driver/util/hibernate.cfg.xml");
        sessionFactory = cfg.buildSessionFactory();
    }

    /*
    *保存车辆
     */
    public void save(DriverCar m) {
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
                  删除指定id的车辆
     */
    public void delete(int dc_id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            DriverCar st = (DriverCar) session.get(DriverCar.class, dc_id);
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
    更新车辆
     */
    public void update(DriverCar m) {
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
    通过id获取一个车辆
     */
    public DriverCar getById(String dc_id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            DriverCar m = (DriverCar) session.get(DriverCar.class, dc_id);//获取
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
    查询全部车辆
     */
    public List<DriverCar> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<DriverCar> list = session.createQuery("From DriverCar").list();
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
    public QueryResult<DriverCar> getDriverCarList(int firstResult, int maxResult) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //查询总记录数量
            Long count = (Long) session.createQuery(
                    "select count(*) from DriverCar")
                    .uniqueResult();//执行查询
            //查询一段数据
            Query query = session.createQuery("From DriverCar");
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
            List<DriverCar> list = query.list();
            tx.commit();
            return new QueryResult<DriverCar>(list, count);
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
