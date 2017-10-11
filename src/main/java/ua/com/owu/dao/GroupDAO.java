package ua.com.owu.dao;

import org.joda.time.LocalDate;
import org.mongodb.morphia.query.Sort;
import ua.com.owu.entity.Group;

import java.util.List;

public interface GroupDAO {
    public void save(Group group);

    List<Group> findAll();

    Group findOne(String id);

    List<Group> filterByCourseAndPeriod(String course, LocalDate startDate, LocalDate endDate);
}
