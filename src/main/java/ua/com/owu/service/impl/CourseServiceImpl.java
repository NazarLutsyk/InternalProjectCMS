package ua.com.owu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.owu.dao.CourseDAO;
import ua.com.owu.entity.Course;
import ua.com.owu.service.CourseService;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDAO courseDAO;

    @Override
    public void save(Course course) {
        courseDAO.save(course);
    }

    @Override
    public List<Course> findAll() {
        return courseDAO.findAll();
    }

    @Override
    public Course findOne(String id) {
        return courseDAO.findOne(id);
    }
}
