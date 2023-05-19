import com.pengling.beauty.HelloApplication;
import com.pengling.beauty.entity.Address;
import com.pengling.beauty.mapper.AddressMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloApplication.class)
public class testAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Test
    public void add(){
        Address address = new Address(15,"pl","1752696277","东城区",1,1,"中国","北京市","北京","beijing");
        System.out.println(addressMapper.addAddress(address));
    }
    @Test
    public void delete(){
       System.out.println(addressMapper.deleteAddress(2));
    }
    @Test
    public void update(){
        Address address = new Address(15,"pl","1","东城区",1,1,5,"中国","北京市","北京","beijing");
        System.out.println(addressMapper.updateAddress(address));
    }
    @Test
    public void query(){
        System.out.println(addressMapper.getAddress(15));
    }
}
