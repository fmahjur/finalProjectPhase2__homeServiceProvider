package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.Comment;
import ir.maktab.HomeServiceProvider.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    @Override
    public void add(Comment comment) {

    }

    @Override
    public void delete(Comment comment) {

    }

    @Override
    public void update(Comment comment) {

    }

    @Override
    public List<Comment> selectAll() {
        return null;
    }
}
