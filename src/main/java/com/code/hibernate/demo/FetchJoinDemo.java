package com.code.hibernate.demo;

import com.code.hibernate.entity.Course;
import com.code.hibernate.entity.Instructor;
import com.code.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {

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

            // option 2: Hibernate query witch HQL


            // get the instructor's detail object
            int theId = 1;

            Query<Instructor> query =
                    session.createQuery("SELECT i FROM Instructor i " +
                                           "JOIN FETCH i.courseList WHERE " +
                                           "i.id=:theInstructorId",
                                            Instructor.class);

           // set parameter on query
            query.setParameter("theInstructorId", theId);

            // execute query and get instructor
            Instructor instructor = query.getSingleResult();
            System.out.println("My code: Instructor: " + instructor);

            // commit transaction
            session.getTransaction().commit();

            session.close();
            System.out.println("Session now is close");

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
