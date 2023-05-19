import com.pengling.beauty.HelloApplication;
import com.pengling.beauty.entity.Comment;
import com.pengling.beauty.entity.Page;
import com.pengling.beauty.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloApplication.class)
public class testComment {
    @Autowired
    private CommentService commentService;
    @Test
    public void testSelectAll(){
        Page<Comment> commentPage = commentService.selectAll(1);
        System.out.println(commentPage);
    }
    @Test
    public void testSelectAllByShopperId(){
        Page<Comment> commentPage = commentService.selectAllByShopperId(1,2);
        System.out.println(commentPage);
    }
    @Test
    public void testselectCommentByGoodId(){
        List<Comment> commentPage = commentService.selectCommentByGoodId(1);
        System.out.println(commentPage);
    }
    @Test
    public void reply(){
        int commentPage = commentService.reply("1122");
        System.out.println(commentPage);
    }
    @Test
    public void testselectCommentByGoodIdAndShopperId(){
        List<Comment> commentPage = commentService.selectCommentByGoodIdAndShopperId(2,1);
        System.out.println(commentPage);
    }

}
