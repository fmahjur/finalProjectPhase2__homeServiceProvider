package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.Comment;
import ir.maktab.HomeServiceProvider.data.model.Credit;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.repository.CommentRepository;
import ir.maktab.HomeServiceProvider.validation.PictureValidator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentServiceImpTest {
    @Autowired
    private CommentServiceImp commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ExpertServiceImpl expertService;

    GetImage image = new GetImage();

    Expert expert = new Expert("reihan", "mahjour", "kaveh@gmail.com", "Rm@123456", new Credit(1000L), image.getImage());
    Expert expert1 = new Expert("hamid", "mahjour", "mohammad@gmail.com", "Hm@123456", new Credit(1000L), image.getImage());
    Expert expert2 = new Expert("hamid", "mahjour", "ali@gmail.com", "Am@123456", new Credit(1000L), image.getImage());
    Comment comment = new Comment("Comment", 5.0, expert, false);
    Comment comment1 = new Comment("Comment1", 4.0, expert1, false);
    Comment comment2 = new Comment("Comment2", 3.0, expert2, false);

    @Test
    void addNewCommentTest() {
        Comment saveComment = commentService.add(comment);
        assertNotNull(saveComment);
    }

    @Test
    void testSoftDeleteComment() {
        Comment saveComment = commentService.add(comment2);
        commentService.remove(saveComment);
        Optional<Comment> optionalComment = commentRepository.findById(saveComment.getId());
        assertThat(optionalComment.get().isDeleted()).isEqualTo(true);

    }

    @Test
    void testUpdateComment() {
        Comment saveComment = commentService.add(comment1);
        saveComment.setComment("update comment1");
        Comment updateComment = commentService.update(saveComment);
        assertThat(updateComment.getComment()).isEqualTo("update comment1");
    }

    @Test
    void testSelectAllComment() {
        List<Comment> commentList = commentService.selectAllAvailableService();
        assertThat(commentList).isNotNull();
        assertThat(commentList.size()).isEqualTo(2);
    }
}