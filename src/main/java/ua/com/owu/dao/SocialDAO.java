package ua.com.owu.dao;

import ua.com.owu.entity.Social;

import java.util.Collection;
import java.util.List;

public interface SocialDAO {
    void save(Social social);

    void delete(Social social);

    List<Social> findAll();

    Social find(String id);

    List<Social> findAllByIds(Collection<String> ids);
}
