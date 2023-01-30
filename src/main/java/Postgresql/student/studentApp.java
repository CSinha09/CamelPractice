package Postgresql.student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class studentApp {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        // To get data from the table
        student s1 = em.find(student.class, 1);
        System.out.println(s1);

        // To insert data into table
        student s2 = new student();
        s2.setId(4);
        s2.setName("Random");
        s2.setAge(25);

        em.getTransaction().begin();
        em.persist(s2);
        em.getTransaction().commit();
        System.out.println(s2);

    }

}
