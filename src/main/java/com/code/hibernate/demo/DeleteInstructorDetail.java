package com.code.hibernate.demo;

import com.code.hibernate.entity.Instructor;
import com.code.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetail {

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

            // get instructor detail by primary key
            int theId = 5;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);

            // remove the associated object reference
            // break bi-direction link
            instructorDetail.getInstructor().setInstructorDetail(null);

            // delete instructor detail
                instructorDetail.print();
                session.delete(instructorDetail);

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
