package com.code.hibernate.demo;

import com.code.hibernate.entity.Course;
import com.code.hibernate.entity.Instructor;
import com.code.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

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

            // create some courses
            Course theCourse1 = new Course("Best sing in guitar");
            Course theCourse2 = new Course("How biking in long road");
            Course theCourse3 = new Course("Build cheep house");
            Course theCourse4 = new Course("Learn coding for 4 day");

            // add courses to instructor
            instructor.add(theCourse1);
            instructor.add(theCourse2);
            instructor.add(theCourse3);
            instructor.add(theCourse4);

            // save the courses
            session.save(theCourse1);
            session.save(theCourse2);
            session.save(theCourse3);
            session.save(theCourse4);

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
