package ua.com.owu.service.impl;

import org.joda.time.LocalDate;
import org.mongodb.morphia.query.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.owu.dao.GroupDAO;
import ua.com.owu.entity.Group;
import ua.com.owu.service.GroupService;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDAO groupDAO;

    @Override
    public void save(Group group) {
        groupDAO.save(group);
    }

    @Override
    public List<Group> findAll() {
        return groupDAO.findAll();
    }

    @Override
    public Group findOne(String id) {
        return groupDAO.findOne(id);
    }

    @Override
    public List<Group> filterByCourseAndPeriod(String course, LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null && endDate.isBefore(startDate))
            return groupDAO.filterByCourseAndPeriod(course, endDate, startDate);
        return groupDAO.filterByCourseAndPeriod(course, startDate, endDate);
    }

    @Override
    public Group findOneByGroupName(String name) {
        return groupDAO.findOneByGroupName(name);
    }

    @Override
    public List<Group> findAllGroupsPossibleAddUser(String userId) {
        return groupDAO.findAllGroupsPossibleAddUser(userId);
    }

}
