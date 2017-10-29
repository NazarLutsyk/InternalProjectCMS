package ua.com.owu.dao.impl;

import org.bson.types.ObjectId;
import org.joda.time.LocalDate;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.owu.dao.GroupDAO;
import ua.com.owu.entity.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class GroupDAOImpl implements GroupDAO {
    @Autowired
    private Datastore datastore;

    @Override
    public void save(Group group) {
        datastore.save(group);
        System.out.println("Group was saved");

    }

    @Override
    public List<Group> findAll() {
        List<Group> groups = datastore.find(Group.class).asList();
        System.out.println("found list of groups:" + groups);
        return groups;
    }

    @Override
    public Group findOne(String id) {
        Group group = datastore.get(Group.class, new ObjectId(id));
        System.out.println("found group:" + group);
        return group;
    }

    @Override
    public Group findOneByGroupName(String name) {
        Group group = datastore.find(Group.class)
                .field("groupIdentifier")
                .equal(name)
                .get();
        System.out.println("Found group by group name:" + group);
        return group;
    }

    @Override
    public List<Group> filterByCourseAndPeriod(String course, LocalDate startDate, LocalDate endDate) {
        List<Group> groups;
        Query<Group> query = datastore.createQuery(Group.class);
        Query<Course> courseQuery;

        if (course.equals(""))
            courseQuery = datastore.find(Course.class);
        else
            courseQuery = datastore.createQuery(Course.class)
                    .field("courseTitle").containsIgnoreCase(course);

        if (startDate != null && endDate != null) {
            query.and(
                    query.criteria("course").hasAnyOf(courseQuery.asList()),
                    query.criteria("startDate").greaterThanOrEq(startDate.toDate()),
                    query.criteria("startDate").lessThanOrEq(endDate.toDate())
            );
        } else if ((startDate == null || endDate == null) && (startDate != null || endDate != null)) {
            if (startDate != null)
                query.and(
                        query.criteria("course").hasAnyOf(courseQuery.asList()),
                        query.criteria("startDate").lessThanOrEq(startDate.toDate())
                );
            else
                query.and(
                        query.criteria("course").hasAnyOf(courseQuery.asList()),
                        query.criteria("startDate").greaterThanOrEq(endDate.toDate())
                );
        } else {
            query.criteria("course").hasAnyOf(courseQuery.asList());
        }
        groups = query.asList();
        System.out.println("Found filtered groups:" + groups);
        return groups;

    }

    @Override
    public List<Group> findAllGroupsPossibleAddUser(String userId) {
        Query<Client> clientQuery = datastore
                .createQuery(Client.class)
                .field("id")
                .equal(new ObjectId(userId));
        List<Application> applications = clientQuery.get().getApplications();
        Set<Course> courses = new HashSet<>();
        applications.forEach(application -> courses.add(application.getCourse()));
        Query<Group> groupQuery = datastore.createQuery(Group.class);
        groupQuery.and(
                groupQuery.criteria("clients").notEqual(clientQuery.get()),
                groupQuery.criteria("course").hasAnyOf(courses)
        );
        List<Group> groups = groupQuery.asList();
        System.out.println("Find all groups whitch allow add user:" + groups);
        return groups;
    }


}
