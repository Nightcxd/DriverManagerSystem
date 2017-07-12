package org.driver.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.driver.bean.Manager;
import org.driver.bean.Trainee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TraineeUtil {
	private static final Logger logger = Logger.getLogger(TraineeUtil.class);
	private static SessionFactory sessionFactory;

    static {
        Configuration cfg = new Configuration();
        cfg.configure("org/driver/util/hibernate.cfg.xml");
        sessionFactory = cfg.buildSessionFactory();
    }

    /*
    *保存学员
     */
    public void save(Trainee m) {
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
                  删除指定id的学员
     */
    public void delete(int t_id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Trainee st = (Trainee) session.get(Trainee.class, t_id);
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
    更新学员
     */
    public void update(Trainee m) {
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
    通过id获取一个学员
     */
    public Trainee getById(String t_id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Trainee m = (Trainee) session.get(Trainee.class, t_id);//获取
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
    查询全部
     */
    public List<Trainee> getAll(){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Trainee> list = session.createQuery("From Trainee").list();
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
    public QueryResult<Trainee> getTraineeList(int firstResult, int maxResult) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //查询总记录数量
            Long count = (Long) session.createQuery(
                    "select count(*) from Trainee")
                    .uniqueResult();//执行查询
            //查询一段数据
            Query query = session.createQuery("From Trainee");
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
            List<Trainee> list = query.list();
            tx.commit();
            return new QueryResult<Trainee>(list, count);
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

   
      public static void main(String[] args) {
    	 TraineeUtil traineeUtil=new TraineeUtil();
    	 System.out.println(traineeUtil.getTraineeList(0, 10).getList().get(0).getT_name());
        
    }
}
