import com.pengling.beauty.HelloApplication;
import com.pengling.beauty.entity.User;
import com.pengling.beauty.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloApplication.class)
public class testUserService {
    @Autowired
    private UserService userService;
    @Test
    public void selectUserAsc(){
        System.out.println(userService.selectAllASC(1));
    }
    @Test
    public void selectUserDesc(){
        System.out.println(userService.selectAllDESC(1).getResultList());
    }
//    @Test
//    public void selectUserId(){
//        System.out.println(userService.selectByOpenid(1));
//    }
    @Test
    public void selectUserName(){
        System.out.println(userService.selectByUserName("四"));
    }
//    @Test
//    public void selectUserOpenId(){
//        System.out.println(userService.selectUserByUserOpenid("1"));
//    }
    @Test
    public void addUser(){
        System.out.println(userService.addUser(new User("新增用户1","2","1","1")));
    }
    @Test
    public void changeUserNickname(){
        System.out.println(userService.changeUserNickname("2",1));
    }
    @Test
    public void changeUserTel(){
        System.out.println(userService.updateTel("1",1));
    }
//    积分测试
    @Test
    public void query(){
        System.out.println(userService.queryPoint(15));
    }
    @Test
    public void add(){
        System.out.println(userService.addPoint(15));
    }
    @Test
    public void desc(){
        System.out.println(userService.descPoint(15,3));
    }
}
