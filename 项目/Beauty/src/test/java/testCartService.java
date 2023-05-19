import com.pengling.beauty.HelloApplication;
import com.pengling.beauty.entity.Cart;
import com.pengling.beauty.service.CartInfoService;
import com.pengling.beauty.service.CartService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloApplication.class)
public class testCartService {
    @Autowired
    private CartInfoService cartInfoService;
//   测试购物车查询
    @Test
    public void testQueryCart(){
        System.out.println(cartInfoService.getCartInfo(15));
    }
    @Autowired
    private CartService cartService;
    @Test
    public void testAddGoodToCart(){
        Date date = new Date();
        Cart cart = new Cart(5,date,1,1,1,1,1);
        System.out.println(cartService.addGoodToCart(cart));
    }
    @Test
    public void testDescGoodToCart(){
        System.out.println(cartService.descGoodNum(15,5));
    }
    @Test
    public void testDeleteGoodToCart(){
        System.out.println(cartService.deleteGoodNum(1,5));
    }
    @Test
    public void testUpdateGoodNumToCart(){
        System.out.println(cartService.updateGoodNum(15,5,10));
    }
}
