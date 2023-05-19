import com.pengling.beauty.HelloApplication;
import com.pengling.beauty.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloApplication.class)
public class testOrder {
    @Autowired
    private OrderService orderService;
    @Test
    public void testSelectByOrderAfterSH(){
        System.out.println(orderService.selectByOrderAfterSH(1));
    }
    @Test
    public void testSelectByOrderBeforeSH(){
        System.out.println(orderService.selectByOrderBeforeSH(1));
    }
    @Test
    public void testSelectByOrderBeforeFH(){
        System.out.println(orderService.selectByOrderBeforeFH(1));
    }
//
    @Test
    public void TestselectByOrderAfterSHByOrderId01(){
        System.out.println(orderService.selectByOrderAfterSHByOrderId(1));
    }
    @Test
    public void TestselectByOrderAfterSHByOrderId02(){
        System.out.println(orderService.selectByOrderAfterSHByOrderId(2));
    }
    @Test
    public void TestselectByOrderAfterSHByOrderId03(){
        System.out.println(orderService.selectByOrderAfterSHByOrderId(3));
    }
//
@Test
public void TestselectByOrderBeforeSHByOrderId01(){
    System.out.println(orderService.selectByOrderBeforeSHByOrderId(1));
}
    @Test
    public void TestselectByOrderBeforeSHByOrderId02(){
        System.out.println(orderService.selectByOrderBeforeSHByOrderId(2));
    }
    @Test
    public void TestselectByOrderBeforeSHByOrderId03(){
        System.out.println(orderService.selectByOrderBeforeSHByOrderId(3));
    }
//
@Test
public void TestselectByOrderBeforeFHByOrderId01(){
    System.out.println(orderService.selectByOrderBeforeFHByOrderId(1));
}
    @Test
    public void TestselectByOrderBeforeFHByOrderId02(){
        System.out.println(orderService.selectByOrderBeforeFHByOrderId(2));
    }
    @Test
    public void TestselectByOrderBeforeFHByOrderId03(){
        System.out.println(orderService.selectByOrderBeforeFHByOrderId(3));
    }
//    @Test
//    public void TestSH(){
//        System.out.println(orderService.changeOrderStatus("241682060483000",24));
//    }


}
