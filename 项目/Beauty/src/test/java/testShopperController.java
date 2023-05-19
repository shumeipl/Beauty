import com.pengling.beauty.HelloApplication;
import com.pengling.beauty.controller.AdminController;
import com.pengling.beauty.controller.ShopperController;
import com.pengling.beauty.entity.Shopper;
import com.pengling.beauty.service.ShopperService;
import com.pengling.beauty.service.ShopperServiceInt;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloApplication.class)
public class testShopperController {
    @Autowired
    private ShopperService shopperService;
    @Test
    public void testInsert(){
        Date date = new Date();
        Shopper shopper = new Shopper("头像","姓名","电话","123456","身份证号码","地址","描述",new Date());
        int  i = shopperService.insertByShopper(shopper);
        System.out.println(i);
    }
//    @Test
//    public void update(){
//        Shopper shopper = new Shopper(2,"头像1","姓名","电话","123456","身份证号码","地址","描述",new Date());
//        int res = shopperService.updateByShopperId(shopper);
//        System.out.println(1);
//    }
    @Test
    public void changePassword(){
        int res =shopperService.updateByPassword(2,"xinmima");
    }
}
