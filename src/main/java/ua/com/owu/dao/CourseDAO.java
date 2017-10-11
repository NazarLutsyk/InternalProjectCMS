package ua.com.owu.dao;

import ua.com.owu.entity.Course;

import java.util.List;

public interface CourseDAO {
    public void save(Course course);

    List<Course> findAll();

    Course findOne(String id);
}
