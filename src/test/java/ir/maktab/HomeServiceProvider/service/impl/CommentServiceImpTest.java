package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.Comment;
import ir.maktab.HomeServiceProvider.data.model.Credit;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentServiceImpTest {
    @Autowired
    private CommentServiceImp commentService;

    GetImage image = new GetImage();

    Expert expert = new Expert(4L, "reihan", "mahjour", "kaveh@gmail.com", "Rm@123456", new Credit(1000L), image.getImage());
    Expert expert1 = new Expert(5L, "hamid", "mahjour", "mohammad@gmail.com", "Hm@123456", new Credit(1000L), image.getImage());
    Expert expert2 = new Expert(6L, "hamid", "mahjour", "ali@gmail.com", "Am@123456", new Credit(1000L), image.getImage());
    Comment comment = new Comment(4L, "Comment", 5.0, expert);
    Comment comment1 = new Comment(5L, "Comment1", 4.0, expert1);
    Comment comment2 = new Comment(6L, "Comment2", 3.0, expert2);

    @Test
    void addNewCommentTest() {
        Comment saveComment = commentService.add(comment);
        assertNotNull(saveComment);
    }

    @Test
    void testSoftDeleteComment() {
        Comment saveComment = commentService.add(comment2);
        commentService.remove(saveComment);
        Comment optionalComment = commentService.findById(saveComment.getId());
        assertThat(optionalComment.isDeleted()).isEqualTo(true);

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