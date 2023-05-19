package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    private Integer userId;
    private String shopperId;
    private Integer commentStatus;
    private Integer goodId;
    private String commentContent;
    private Integer commentId;
    private Integer commentLevel;
    private String commentDate;
    private String commentReply;
    private String orderHao;
    private Integer commentWay;
    private String userAvatar;
    private String userName;
    public Comment(Integer userId,Integer goodId,Integer commentLevel,String commentContent,Integer commentStatus,String orderHao,Integer commentWay,String commentDate){
        this.userId=userId;
        this.goodId=goodId;
        this.commentContent=commentContent;
        this.commentStatus=commentStatus;
        this.orderHao=orderHao;
        this.commentWay=commentWay;
        this.commentDate=commentDate;
        this.commentLevel=commentLevel;
    }
}
