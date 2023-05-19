import com.pengling.beauty.HelloApplication;
import com.pengling.beauty.entity.Complain;
import com.pengling.beauty.service.ComplainService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloApplication.class)
public class testComplainService {
    @Autowired
    private ComplainService complainService;
    @Test
    public void insertTest(){
        Complain complain = new Complain(1,1,"垃圾",new Date());
        System.out.println(complainService.insert(complain));
    }
/*
//    查看某个商品的投诉 parm:goodid
    List<Complain> selectByGoodId(Integer goodId,Integer startNum,Integer endNum);
    //    查看某个商品的投诉 parm:goodname
    List<Complain> selectByGoodName(String goodName,Integer startNum,Integer endNum);
    int count();
    int selectByGoodIdCount(Integer goodId);
    int selectByGoodNameCount(String goodName);
 */
    @Test
    public void selectAllTest(){
        System.out.println(complainService.selectAll(1));
    }
    @Test
    public void selectByGoodId(){
        System.out.println(complainService.selectByGoodId(1,1));
    }
    @Test
    public void selectByGoodName(){
        System.out.println(complainService.selectByGoodName(1,"2"));
    }
}
