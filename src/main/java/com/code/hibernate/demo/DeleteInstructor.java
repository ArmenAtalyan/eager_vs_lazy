package com.code.hibernate.demo;

import com.code.hibernate.entity.Instructor;
import com.code.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructor {

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

            // get instructor by primary key
            int theId = 2;
            Instructor instructor = session.get(Instructor.class, theId);
            System.out.println("Found instructor: " + instructor);

            // delete the instructor
            if(instructor != null){
                System.out.println("Deleting: " + instructor);
                session.delete(instructor);
            }

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Everything are done.");
        }finally {
            factory.close();
        }
    }
}
