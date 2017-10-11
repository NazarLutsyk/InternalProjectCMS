package ua.com.owu.dao.impl;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.owu.dao.CourseDAO;
import ua.com.owu.entity.Course;

import java.util.List;

@Repository
public class CourseDAOImpl implements CourseDAO {
    @Autowired
    private Datastore datastore;

    @Override
    public void save(Course course) {
        datastore.save(course);
        System.out.println("course saved");

    }

    @Override
    public List<Course> findAll() {
        List<Course> courseList = datastore.createQuery(Course.class).asList();
        System.out.println("course list found : " + courseList);
        return courseList;
    }

    @Override
    public Course findOne(String id) {
        Course course = datastore.get(Course.class, new ObjectId(id));
        System.out.println("course found : " + course);
        return course;
    }
}
