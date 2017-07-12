package org.driver.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.driver.bean.TrainingRecord;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TrainingRecordUtil {
	private static final Logger logger = Logger.getLogger(TrainingRecordUtil.class);
	private static SessionFactory sessionFactory;

    static {
        Configuration cfg = new Configuration();
        cfg.configure("org/driver/util/hibernate.cfg.xml");
        sessionFactory = cfg.buildSessionFactory();
    }

    /*
    *保存练车记录
     */
    public void save(TrainingRecord m) {
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
                  删除指定id的练车记录
     */
    public void delete(int tr_id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            TrainingRecord st = (TrainingRecord) session.get(TrainingRecord.class, tr_id);
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
    更新练车记录
     */
    public void update(TrainingRecord m) {
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
    通过id获取一个练车记录
     */
    public TrainingRecord getById(String tr_id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            TrainingRecord m = (TrainingRecord) session.get(TrainingRecord.class, tr_id);//获取
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
    查询练车记录
     */
    public List<TrainingRecord> getAll(){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<TrainingRecord> list = session.createQuery("From TrainingRecord").list();
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
    public QueryResult<TrainingRecord> getTraineeList(int firstResult, int maxResult) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //查询总记录数量
            Long count = (Long) session.createQuery(
                    "select count(*) from TrainingRecord")
                    .uniqueResult();//执行查询
            //查询一段数据
            Query query = session.createQuery("From TrainingRecord");
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
            List<TrainingRecord> list = query.list();
            tx.commit();
            return new QueryResult<TrainingRecord>(list, count);
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    
    /*
     * 通过日期获取记录
     */
    public List<TrainingRecord> getRecordByDate(String fromDate,String toDate){
    	Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<TrainingRecord> list = session.createQuery("From TrainingRecord where to_days(tr_date)>=to_days('"+fromDate.trim()+"') and to_days(tr_date)<= to_days('"+toDate.trim()+"')").list();
            tx.commit();
            return list;
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
