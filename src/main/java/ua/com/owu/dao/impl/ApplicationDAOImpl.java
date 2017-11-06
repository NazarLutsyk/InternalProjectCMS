package ua.com.owu.dao.impl;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.joda.time.LocalDate;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.CriteriaContainer;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.owu.dao.ApplicationDAO;
import ua.com.owu.entity.Application;
import ua.com.owu.entity.Client;
import ua.com.owu.entity.Course;
import ua.com.owu.entity.Social;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.mongodb.morphia.aggregation.Accumulator.accumulator;
import static org.mongodb.morphia.aggregation.Group.grouping;

@Repository
public class ApplicationDAOImpl implements ApplicationDAO {


    @Autowired
    private Datastore datastore;

    public void save(Application application) {
        datastore.save(application);
        System.out.println("application saved");
    }

    @Override
    public List<Application> findAllByIds(Collection<String> ids) {
        List<Application> apps = new ArrayList<>();
        ids.forEach(id -> apps.add(findOne(id)));
        System.out.println("All applications by ids:" + apps);
        return apps;
    }

    public List<Application> findAll() {
        List<Application> applicationList = datastore.createQuery(Application.class).asList();
        System.out.println("found list : " + applicationList);
        return applicationList;
    }

    public Application findOne(String id) {
        Application application = datastore.get(Application.class, new ObjectId(id));
        System.out.println("found application : " + application);
        return application;
    }

    @Override
    public List<Application> findAllByClient(Client client) {
        List<Application> applications = datastore
                .find(Application.class)
                .field("client")
                .equal(client)
                .asList();
        System.out.println("found application : " + applications);
        return applications;
    }

    @Override
    public void save(Collection<Application> applications) {
        datastore.save(applications);
        System.out.println("Applications saved");
    }

    @Override
    public void reverseChecker(String appId) {
        Query<Application> query = datastore
                .find(Application.class)
                .field("id")
                .equal(new ObjectId(appId));

        Application application = query.get();

        UpdateOperations<Application> ops = datastore
                .createUpdateOperations(Application.class)
                .set("checked", !application.isChecked());

        System.out.println("Checked");
        datastore.update(query, ops);
    }

    @Override
    public List<String> getSocialStatisticByPeriod(LocalDate startDate, LocalDate endDate, Collection<Social> socials) {
        Query<Application> queryApp = datastore.createQuery(Application.class);
        CriteriaContainer and = queryApp.and(
                queryApp.criteria("appReciveDate").greaterThanOrEq(startDate.toDate()),
                queryApp.criteria("appReciveDate").lessThanOrEq(endDate.toDate()),
                queryApp.criteria("source").hasAnyOf(socials)
        );
        Query<Social> socialApp = datastore
                .createQuery(Social.class)
                .field("applications")
                .hasAnyOf(queryApp.asList());
        Iterator<Document> aggregate = datastore
                .createAggregation(Social.class)
                .match(socialApp)
                .group("name", grouping("count", accumulator("$sum", 1)))
                .aggregate(Document.class);
        List<String> documents = new ArrayList<>();
        aggregate.forEachRemaining(document -> documents.add(document.toJson()));
        System.out.println("Statistic by period " + startDate + " " + endDate + ":" + documents);
        return documents;
    }

    @Override
    public long getApplicationStatistic(LocalDate startDate, LocalDate endDate, Collection<Course> courses) {
        Query<Application> queryApp = datastore.createQuery(Application.class);
        CriteriaContainer and = queryApp.and(
                queryApp.criteria("appReciveDate").greaterThanOrEq(startDate.toDate()),
                queryApp.criteria("appReciveDate").lessThanOrEq(endDate.toDate()),
                queryApp.criteria("course").hasAnyOf(courses)
        );
        long count = queryApp.count();
        System.out.println("App stat by period " + startDate + " " + endDate + ":" + count);
        return count;
    }

    @Override
    public List<Application> findNotPaidApps() {
        List<Application> applications = datastore.createQuery(Application.class)
                .field("leftToPay").greaterThan(0)
                .asList();
        System.out.println("Found not paid apps:"+ applications);
        return applications;
    }


}
