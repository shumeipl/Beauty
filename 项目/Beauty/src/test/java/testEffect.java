import com.pengling.beauty.HelloApplication;
import com.pengling.beauty.service.EffectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloApplication.class)
public class testEffect {
    @Autowired
    private EffectService effectService;
    @Test
    public void testGetEffect(){

        System.out.println(effectService.getAllEffect());
    }
}
