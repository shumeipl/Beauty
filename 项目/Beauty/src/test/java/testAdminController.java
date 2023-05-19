
import com.pengling.beauty.HelloApplication;
import com.pengling.beauty.controller.AdminController;
import com.pengling.beauty.entity.Admin;
import com.pengling.beauty.entity.Page;
import com.pengling.beauty.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.StyleSheet;
import java.util.List;
import java.util.Scanner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloApplication.class)

public class testAdminController {
    @Autowired
    private AdminController adminController;
//    @Test
//    public void testLogin(){
//        System.out.println(adminController.Check("1","2222"));
//    }
    @Autowired
    private AdminService adminService;
    @Test
    public void getAdmin(){
        List<Admin> admins = adminService.show(1);
        for (Admin admin: admins){
            System.out.println(admin);
        }
    }
    @Test
    public void getCount(){
        System.out.println(adminService.count());
    }
    @Test
    public void getPage(){
        String adminPage = adminController.showAdminForward(String.valueOf(1));
        System.out.println(adminPage);
    }
    @Test
    public void add(){
        Admin admin = new Admin("Pl","123","女","电话","湖南长沙");
        System.out.println(adminService.add(admin));
    }
    @Test
    public void delete(){
        System.out.println(adminService.delete(18));
    }
    @Test
    public void update(){
        Admin admin  = new Admin(1,"379934","873427394","121212","nan","efer","fef");
        System.out.println(adminService.update(admin));
    }
    @Test
    public void query(){
//        Scanner scanner = new Scanner(System.in);
        String q = "1";
        try{
            System.out.println("id");
        Integer res = Integer.valueOf(q);
       List<Admin> admin = adminService.queryById(res);
            System.out.println(admin);
        }
        catch (Exception e){
            System.out.println("name");
         List<Admin>   admin = adminService.queryByName(q);
         System.out.println(admin);
        }
    }
}
