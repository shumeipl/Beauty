import com.pengling.beauty.HelloApplication;
import com.pengling.beauty.service.OcrService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloApplication.class)
public class testOcrService {
    @Autowired
    private OcrService ocrService;
    @Test
    public void testOcr(){
        ocrService.ocr("https://beauty-pengling.oss-cn-zhangjiakou.aliyuncs.com/56364896b3054b17b7dfbd95fc9fccfb.jpg");
    }
}
