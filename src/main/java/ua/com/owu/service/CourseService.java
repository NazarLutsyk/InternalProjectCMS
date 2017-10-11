package ua.com.owu.service;

import ua.com.owu.entity.Course;

import java.util.List;

public interface CourseService {
    void save(Course course);

    List<Course> findAll();

    Course findOne(String id);
}
