package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> selectAll(int startNum,int endNum);
    int selectAllCount();
    List<Comment> selectAllByShopperId(int startNum,int endNum,int shopperId);
    int selectAllShopperIdCount(int shopperId);
    List<Comment> selectCommentByGoodId(int goodId);
    int reply(String reply);
    List<Comment> selectCommentByGoodIdAndShopperId(int shopperId,int goodId);
    int generateComment(Comment comment);
//
    List<Comment> getAllComment(Integer goodId);
    List<Comment> getNewestComment(Integer goodId);
    List<Comment> getGreatComment(Integer goodId);
    List<Comment> getMiddleComment(Integer goodId);
    List<Comment> getBadComment(Integer goodId);
//    总条数
    int getCount(Integer goodId);
//    好评数量
    int getGreatCount(Integer goodId);
    int getMiddleCount(Integer goodId);
    int getBadCount(Integer goodId);
//    商品评价 商家用
    List<Comment> getCommentByShopperId(Integer shopperId,Integer startNum,Integer endNum);
    int count(Integer shopperId);
}
