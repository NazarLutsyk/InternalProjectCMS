package ua.com.owu.service.impl;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.owu.dao.ApplicationDAO;
import ua.com.owu.entity.Application;
import ua.com.owu.entity.Client;
import ua.com.owu.entity.Social;
import ua.com.owu.service.ApplicationService;

import java.util.Collection;
import java.util.List;

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
    public List<String>getSocialStatisticByPeriod(LocalDate startDate, LocalDate endDate, Collection<Social> socials) {
        if (endDate.isBefore(startDate)){
            return applicationDAO.getSocialStatisticByPeriod(endDate,startDate,socials);
        }
        return applicationDAO.getSocialStatisticByPeriod(startDate,endDate,socials);
    }

}
