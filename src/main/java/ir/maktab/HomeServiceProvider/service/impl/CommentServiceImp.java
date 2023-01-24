package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.Comment;
import ir.maktab.HomeServiceProvider.data.repository.CommentRepository;
import ir.maktab.HomeServiceProvider.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public void add(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        comment.setDeleted(true);
        update(comment);
    }

    @Override
    public void update(Comment comment) {
        Comment existingComment = commentRepository.findById(comment.getId()).orElse(null);
        existingComment.setComment(comment.getComment());
        existingComment.setScore(comment.getScore());
        existingComment.setExpert(comment.getExpert());
        existingComment.setDeleted(comment.isDeleted());
        commentRepository.save(existingComment);
    }

    @Override
    public List<Comment> selectAll() {
        return commentRepository.findAll();
    }
}
