package com.code.hibernate.demo;

import com.code.hibernate.entity.Course;
import com.code.hibernate.entity.Instructor;
import com.code.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create the objects
            Instructor instructor = new Instructor("Sandra", "Bullock", "SandBul@gmail.com");
            InstructorDetail detail = new InstructorDetail("TheGame/youtube.com", "gaming");

            // associate the objects
            instructor.setInstructorDetail(detail);

            // start a transaction
            session.beginTransaction();

            // save the Instructor and also save detail
            // object because of CascadeType.ALL
            System.out.println("Saving instructor " + instructor);
            session.save(instructor);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Everything are done.");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
    }
}
