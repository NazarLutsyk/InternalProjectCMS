package ua.com.owu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.owu.dao.SocialDAO;
import ua.com.owu.entity.Social;
import ua.com.owu.service.SocialService;

import java.util.Collection;
import java.util.List;

@Service
public class SocialServiceImpl implements SocialService{
    @Autowired
    SocialDAO socialDAO;

    @Override
    public void save(Social social) {
        socialDAO.save(social);
    }

    @Override
    public void delete(Social social) {
        socialDAO.delete(social);
    }

    @Override
    public List<Social> findAll() {
        return socialDAO.findAll();
    }

    @Override
    public Social find(String id) {
        return socialDAO.find(id);
    }

    @Override
    public List<Social> findAllByIds(Collection<String> ids) {
        return socialDAO.findAllByIds(ids);
    }
}
