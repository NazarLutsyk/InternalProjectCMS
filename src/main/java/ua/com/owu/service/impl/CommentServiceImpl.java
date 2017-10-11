package ua.com.owu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.owu.dao.CommentDao;
import ua.com.owu.entity.Comment;
import ua.com.owu.service.CommentService;

import java.util.Collection;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void save(Collection<Comment> comments) {
        commentDao.save(comments);
    }
}
