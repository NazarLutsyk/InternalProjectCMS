package ua.com.owu.dao;

import ua.com.owu.entity.Comment;

import java.util.Collection;

public interface CommentDao {
    void save(Comment comment);

    void save(Collection<Comment> comments);
}
