package org.driver.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.driver.bean.Manager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManagerUtil {
	private static final Logger logger = Logger.getLogger(ManagerUtil.class);
	private static SessionFactory sessionFactory;

    static {
        Configuration cfg = new Configuration();
        cfg.configure("org/driver/util/hibernate.cfg.xml");
        sessionFactory = cfg.buildSessionFactory();
    }

    /*
    *保存负责人
     */
    public void save(Manager m) {
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
                  删除指定id的负责人
     */
    public void delete(int m_id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Manager st = (Manager) session.get(Manager.class, m_id);
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
    更新负责人
     */
    public void update(Manager m) {
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
    通过id获取一个负责人
     */
    public Manager getById(String m_id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Manager m = (Manager) session.get(Manager.class, m_id);//获取
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
    查询全部。负责人
     */
    public List<Manager> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Manager> list = session.createQuery("From Manager").list();
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
     *登陆验证
     */
    public int check(String m_account, String m_pwd) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Manager> list = session.createQuery("From Manager where m_account=? and m_pwd=?").setString(0, m_account.trim()).setString(1, m_pwd.trim()).list();//位置参数锁定
            int num = list.size();
            tx.commit();
            return num;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

      public static void main(String[] args) {
    	  ManagerUtil mUtil=new ManagerUtil();
    	  int m= mUtil.check("15522151220", "123www");
    	  System.out.println();
//    	  m.setM_account("15522151220");
//    	  m.setM_id(GenerateSequenceUtil.generateSequenceNo());
//    	  m.setM_name("cxd");
//    	  m.setM_pwd("123www");
    	  
    	 
        
    }
}
