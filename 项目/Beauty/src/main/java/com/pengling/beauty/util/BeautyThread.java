package com.pengling.beauty.util;

import com.pengling.beauty.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class BeautyThread extends Thread{
    private Integer userId;
    private String userCode;
    @Autowired
    private UserService userService;
    @Override
    public void run() {
        try {
            sleep(3000000);
            userService.changeUserCode(userId,userCode);
            System.out.println("验证码已经重置");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
