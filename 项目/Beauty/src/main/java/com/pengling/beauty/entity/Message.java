package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Integer messageId;
   private Integer userId;
   private String messageContent;
   private String messageDate;
}
