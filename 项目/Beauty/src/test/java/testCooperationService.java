import com.pengling.beauty.HelloApplication;
import com.pengling.beauty.entity.Cooperation;
import com.pengling.beauty.service.CooperationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloApplication.class)
public class testCooperationService {
    @Autowired
    private CooperationService cooperationService;

    @Test
    public void selectAllTest(){
        System.out.println(cooperationService.selectAll(1));
    }
    @Test
    public void acceptTest(){
        System.out.println(cooperationService.accept(1));
    }
    @Test
    public void refuseTest(){
        System.out.println(cooperationService.refuse(1));
    }
}
