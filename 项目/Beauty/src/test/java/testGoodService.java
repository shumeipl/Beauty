import com.pengling.beauty.HelloApplication;
import com.pengling.beauty.entity.Good;
import com.pengling.beauty.service.GoodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloApplication.class)
public class testGoodService {
    @Autowired
    private GoodService goodService;
//    @Test
//    public void testInsert(){
//        Good good = new Good("",2,"2",2.0,"2","2","2",2,"精选分类","香水","2","2");
//        System.out.println(goodService.insert(good));
//    }
@Test
public void testGetByShopperId(){
    System.out.println(goodService.selectAll(1,1));
}
    @Test
    public void testShangjia(){
        System.out.println(goodService.shangJiaGood(1));
    }
    @Test
    public void testXiajia(){
        System.out.println(goodService.xiaJiaGood(1));
    }
    @Test
    public void selectById(){
        System.out.println(goodService.selectByIdGood(1));
    }
    @Test
    public void selectByGoodSelected(){
        System.out.println(goodService.selectByGoodSelected(1,1));
    }
}
