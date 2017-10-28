package ua.com.owu.dao.impl;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.joda.time.LocalDate;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.aggregation.Projection;
import org.mongodb.morphia.query.CriteriaContainer;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.stereotype.Repository;
import ua.com.owu.dao.ApplicationDAO;
import ua.com.owu.entity.Application;
import ua.com.owu.entity.Client;
import ua.com.owu.entity.Social;

import java.util.*;

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
        Query<Application> query = datastore.createQuery(Application.class);
        CriteriaContainer and = query.and(
                query.criteria("appReciveDate").greaterThanOrEq(startDate.toDate()),
                query.criteria("appReciveDate").lessThanOrEq(endDate.toDate()),
                query.criteria("source").hasAnyOf(socials)
        );
        Iterator<Document> aggregate = datastore.
                createAggregation(Application.class)
                .match(query)
                .group("source", grouping("count", accumulator("$sum", 1)))
                .aggregate(Document.class);
        List<String> documents = new ArrayList<>();
        aggregate.forEachRemaining(document -> documents.add(document.toJson()));
        System.out.println("Statistic by period " + startDate + " " + endDate + ":" + documents);
        return documents;
    }


}
