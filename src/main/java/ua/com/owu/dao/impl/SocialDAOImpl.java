package ua.com.owu.dao.impl;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.owu.dao.SocialDAO;
import ua.com.owu.entity.Social;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class SocialDAOImpl implements SocialDAO{
    @Autowired
    Datastore datastore;

    @Override
    public void save(Social social) {
        datastore.save(social);
        System.out.println("Social saved");
    }

    @Override
    public void delete(Social social) {
        datastore.delete(social);
        System.out.println("Social deleted");
    }

    @Override
    public List<Social> findAll() {
        List<Social> socials = datastore.find(Social.class).asList();
        System.out.println("Found list of socials:" + socials);
        return socials;
    }

    @Override
    public Social find(String id) {
        Social social = datastore.get(Social.class, new ObjectId(id));
        System.out.println("Found social:" + social);
        return social;
    }

    @Override
    public List<Social> findAllByIds(Collection<String> ids) {
        List<Social> socials = new ArrayList<>();
        ids.forEach(s -> socials.add(find(s)));
        System.out.println("Found list of socials by ids:" + socials);
        return socials;
    }
}
