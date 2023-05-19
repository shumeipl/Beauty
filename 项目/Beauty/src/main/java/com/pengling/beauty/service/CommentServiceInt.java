package com.pengling.beauty.service;

import com.pengling.beauty.entity.Comment;
import com.pengling.beauty.entity.Page;

import java.util.List;

public interface CommentServiceInt {
    Page<Comment> selectAll(int currentPage);
    Page<Comment> selectAllByShopperId(int currentPage,int shopperId);
    List<Comment> selectCommentByGoodId(int goodId);
    int reply(String reply);
    List<Comment> selectCommentByGoodIdAndShopperId(int shopperId,int goodId);
    int generateComment(Comment comment);
    List<Comment> getAllComment(Integer goodId);
    List<Comment> getNewestComment(Integer goodId);
    List<Comment> getGreatComment(Integer goodId);
    List<Comment> getMiddleComment(Integer goodId);
    List<Comment> getBadComment(Integer goodId);
    int getCount(Integer goodId);
    //    好评数量
    int getGreatCount(Integer goodId);
    int getMiddleCount(Integer goodId);
    int getBadCount(Integer goodId);
    Page<Comment> getCommentByShopperId(Integer shopperId,Integer currentPage);
}
