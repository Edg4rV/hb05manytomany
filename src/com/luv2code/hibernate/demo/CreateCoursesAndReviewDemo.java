package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCoursesAndReviewDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
        .configure("hibernate.cfg.xml")
        .addAnnotatedClass(Instructor.class)
        .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
        .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            Course tempCourse = new Course("Pacman -How To Score one Million Points");

            tempCourse.addReview(new Review("Great course ... loved it!"));
            tempCourse.addReview(new Review("Cool course , job well done!"));
            tempCourse.addReview(new Review("What a dumb course, you are an idiot! ... loved it!"));
            tempCourse.addReview(new Review("Second Great course ... loved it!"));

            System.out.println("Saving the course");

            System.out.println(tempCourse);

            System.out.println(tempCourse.getReview());

            session.save(tempCourse);
            session.getTransaction().commit();

            System.out.println("Done");


        }
        finally {
            session.close();
            factory.close();
        }

    }
}
