package ua.com.owu.service;

import org.joda.time.LocalDate;
import ua.com.owu.entity.Application;
import ua.com.owu.entity.Client;
import ua.com.owu.entity.Course;
import ua.com.owu.entity.Social;

import java.util.Collection;
import java.util.List;

public interface ApplicationService {
    void save(Application application);

    List<Application> findAll();

    List<Application> findAllByIds(Collection<String> ids);

    Application findOne(String id);

    List<Application> findAllByClient(Client client);

    void save(Collection<Application> applications);

    void reverseChecker(String appId);

    List<String> getSocialStatisticByPeriod(LocalDate startDate, LocalDate endDate, Collection<Social> socials);

    long getApplicationStatistic(LocalDate startDate, LocalDate endDate, Collection<Course> courses);

    List<Application> findNotPaidApps();


}
