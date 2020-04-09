package com.code.hibernate.demo;

import com.code.hibernate.entity.Course;
import com.code.hibernate.entity.Instructor;
import com.code.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourse {

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

            // get course by primary key
            int theId = 10;
            Course theCourse = session.get(Course.class, theId);

            // delete the course
            System.out.println("Deleting course: " + theCourse);
            session.delete(theCourse);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Everything are done.");
        }finally {
            factory.close();
        }
    }
}
