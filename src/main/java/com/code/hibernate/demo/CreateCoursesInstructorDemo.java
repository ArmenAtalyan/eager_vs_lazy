package com.code.hibernate.demo;

import com.code.hibernate.entity.Course;
import com.code.hibernate.entity.Instructor;
import com.code.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesInstructorDemo {

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

            // get the instructor from db
            int theId = 1;
            Instructor instructor = session.get(Instructor.class, theId);
            System.out.println("Instructor: " + instructor);

            // get course for instructor
            System.out.println("Courses: " + instructor.getCourseList());

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
