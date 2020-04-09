package com.code.hibernate.demo;

import com.code.hibernate.entity.Course;
import com.code.hibernate.entity.Instructor;
import com.code.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazylDemo {

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
            // start a transaction
            session.beginTransaction();

            // get the instructor's detail object
            int theId = 1;
            Instructor instructor = session.get(Instructor.class, theId);

           // print the instructor
            System.out.println("My code: Instructor: " + instructor);

            // commit transaction
            session.getTransaction().commit();

            session.close();

            // get courses for the instructor
            System.out.println("My code: Courses: " + instructor.getCourseList());

            System.out.println("My code: Everything are done.");
        }catch ( Exception e){
            e.printStackTrace();
        } finally {

            factory.close();
        }
    }
}
