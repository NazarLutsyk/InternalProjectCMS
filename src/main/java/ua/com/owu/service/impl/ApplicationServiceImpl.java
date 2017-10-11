package ua.com.owu.service.impl;

import org.bson.Document;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.owu.dao.ApplicationDAO;
import ua.com.owu.entity.Application;
import ua.com.owu.entity.Client;
import ua.com.owu.entity.enums.Social;
import ua.com.owu.service.ApplicationService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationDAO applicationDAO;


    @Override
    public void save(Application application) {
        applicationDAO.save(application);
    }

    @Override
    public List<Application> findAll() {
        return applicationDAO.findAll();
    }

    @Override
    public Application findOne(String id) {
        return applicationDAO.findOne(id);
    }

    @Override
    public List<Application> findAllByClient(Client client) {
        return applicationDAO.findAllByClient(client);
    }

    @Override
    public void save(Collection<Application> applications) {
        applicationDAO.save(applications);
    }

    @Override
    public void reverseChecker(String appId) {
        applicationDAO.reverseChecker(appId);
    }

    @Override
    public List<String>getSocialStatisticByPeriod(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)){
            return applicationDAO.getSocialStatisticByPeriod(endDate,startDate);
        }
        return applicationDAO.getSocialStatisticByPeriod(startDate,endDate);
    }

}
