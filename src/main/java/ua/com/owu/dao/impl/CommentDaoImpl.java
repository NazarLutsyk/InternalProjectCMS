package ua.com.owu.dao.impl;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.owu.dao.CommentDao;
import ua.com.owu.entity.Comment;

import java.util.Collection;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    Datastore datastore;

    @Override
    public void save(Comment comment) {
        datastore.save(comment);
        System.out.println("Comment was saved");
    }

    @Override
    public void save(Collection<Comment> comments) {
        datastore.save(comments);
        System.out.println("Collection of comments was saved");
    }
}
