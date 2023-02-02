package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.Comment;
import ir.maktab.HomeServiceProvider.data.model.Credit;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.validation.PictureValidator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentServiceImpTest {
    @Autowired
    private CommentServiceImp commentService;

    @Autowired
    private ExpertServiceImpl expertService;

    Expert expert = new Expert("reihan", "mahjour", "reihaneh763@gmail.com", "Rm@123456", new Credit(1000L), getImage());
    Expert expert1 = new Expert("hamid", "mahjour", "hamid@gmail.com", "Hm@123456", new Credit(1000L), getImage());
    Comment comment = new Comment("Comment", 5.0, expert, false);
    Comment comment1 = new Comment("Comment1", 4.0, expert1, false);

    @Test
    void add() {
        expertService.add(expert);
        Comment saveComment = commentService.add(comment);
        assertNotNull(saveComment);
    }

    @Test
    void remove() {
        commentService.remove(comment);
    }

    @Test
    void update() {
        expertService.add(expert1);
        commentService.add(comment1);
        comment1.setComment("update comment1");
        Comment updateComment = commentService.update(comment1);
        assertThat(updateComment.getComment()).isEqualTo("update comment1");
    }

    @Test
    void selectAll() {
        List<Comment> commentList = commentService.selectAllAvailableService();
        assertThat(commentList).isNotNull();
        assertThat(commentList.size()).isEqualTo(1);
    }

    private byte[] getImage() {
        String imageFilePath = "C:\\Users\\paage\\OneDrive\\Documents\\reihaneh\\1111.jpg";
        PictureValidator.isValidImageFile(imageFilePath);
        File file = new File(imageFilePath);
        byte[] bFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bFile;
    }
}