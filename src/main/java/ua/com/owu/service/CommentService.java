package ua.com.owu.service;

import ua.com.owu.entity.Comment;

import java.util.Collection;

public interface CommentService {
    void save(Comment comment);

    void save(Collection<Comment> comments);

}
