package ua.com.owu.service;

import org.bson.Document;
import org.joda.time.LocalDate;
import ua.com.owu.entity.Application;
import ua.com.owu.entity.Client;
import ua.com.owu.entity.Group;
import ua.com.owu.entity.enums.Social;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ApplicationService {
    void save(Application application);

    List<Application> findAll();

    Application findOne(String id);

    List<Application> findAllByClient(Client client);

    void save(Collection<Application> applications);

    public void reverseChecker(String appId);

    List<String> getSocialStatisticByPeriod(LocalDate startDate, LocalDate endDate);


}
