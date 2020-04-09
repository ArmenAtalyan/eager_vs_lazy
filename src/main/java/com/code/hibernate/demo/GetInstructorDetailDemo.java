package com.code.hibernate.demo;

import com.code.hibernate.entity.Instructor;
import com.code.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get the instructor's detail object
            int theId = 3;
            InstructorDetail detail = session.get(InstructorDetail.class, theId);

            // print instructor detail
            System.out.println("Found instructorDetail: " + detail);

            // print associated instructor
            System.out.println("Found associated instructor: " + detail.getInstructor());

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Everything are done.");
        }catch ( Exception e){
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
