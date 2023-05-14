import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

import org.hibernate.Hibernate;

public class Dao {

    private SessionFactory factory;

    public Dao() {
        factory = new Configuration()
        .configure("hibernate.cfg.xml")
        .addAnnotatedClass(Customer.class)
        .addAnnotatedClass(Order.class)
        .addAnnotatedClass(Address.class)
        .buildSessionFactory();
    }


    /*CUSTOMER METHODS*/

    //get
    public List<Customer> getCustomers(String name) {
        Session session = factory.openSession();
        Transaction transaction = null;
        List<Customer> customers = null;
    
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer where name = :name");
            query.setParameter("name", name);
            customers = query.list();
            for (Customer customer : customers) {
                Hibernate.initialize(customer.getAddress());
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    
        return customers;
    }
    

    //save
    public void saveCustomer(Customer customer) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //delete
    public void deleteCustomer(String name) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE Customer WHERE name = :customerName");
            query.setParameter("customerName", name);
            int result = query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

     //get ALL
     public List<Customer> getAllCustomers() {
        Session session = factory.openSession();
        Transaction transaction = null;
        List<Customer> customers = null;
    
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer", Customer.class);
            customers = query.list();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    
        return customers;
    }

    //update
    public void updateCustomer(Customer customer) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /*ORDER METHODS*/

    //update
    public void updateOrder(Order order) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(order);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //get
    public List<Order> getOrder(int num) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Order> orders = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Order WHERE number = :orderNumber");
            query.setParameter("orderNumber", num);
            orders = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return orders;
    }

    //save
    public void saveOrder(Order order) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(order);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //delete
    public void deleteOrder(int number) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE Order WHERE number = :orderNumber");
            query.setParameter("orderNumber", number);
            int result = query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
