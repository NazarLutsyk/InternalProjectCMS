package ua.com.owu.service;

import org.joda.time.LocalDate;
import org.mongodb.morphia.query.Sort;
import ua.com.owu.entity.Group;

import java.util.List;

public interface GroupService {

    void save(Group group);

    List<Group> findAll();

    Group findOne(String id);

    List<Group> filterByCourseAndPeriod(String course, LocalDate startDate, LocalDate endDate);

    Group findOneByGroupName(String name);

    List<Group> findAllGroupsPossibleAddUser(String userId);

}
