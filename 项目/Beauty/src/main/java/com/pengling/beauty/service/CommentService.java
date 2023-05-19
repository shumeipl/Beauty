package com.pengling.beauty.service;

import com.pengling.beauty.entity.Comment;
import com.pengling.beauty.entity.Page;
import com.pengling.beauty.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentService implements CommentServiceInt{
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public Page<Comment> selectAll(int currentPage) {
        int startNum = (currentPage-1)*10;
        int Count =commentMapper.selectAllCount();
        int pageCount=Count%10==0?Count/10:Count/10+1;
        List<Comment> commentList = commentMapper.selectAll(startNum,10);
        System.out.println(commentList);
        Page<Comment> commentPage = new Page<>(pageCount,currentPage,commentList);
        return commentPage;
    }

    @Override
    public Page<Comment> selectAllByShopperId(int currentPage, int shopperId) {
        int startNum = (currentPage-1)*10;
        int Count =commentMapper.selectAllShopperIdCount(shopperId);
        int pageCount=Count%10==0?Count/10:Count/10+1;
        Page<Comment> commentPage = new Page<>(pageCount,currentPage,commentMapper.selectAllByShopperId(startNum,10,shopperId));
        return commentPage;
    }

    @Override
    public List<Comment> selectCommentByGoodId(int goodId) {
        return commentMapper.selectCommentByGoodId(goodId);
    }

    @Override
    public int reply(String reply) {
        return commentMapper.reply(reply);
    }

    @Override
    public List<Comment> selectCommentByGoodIdAndShopperId(int shopperId, int goodId) {
        return commentMapper.selectCommentByGoodIdAndShopperId(shopperId,goodId);
    }

    @Override
    public int generateComment(Comment comment) {
        return commentMapper.generateComment(comment);
    }

    @Override
    public List<Comment> getAllComment(Integer goodId) {
        return commentMapper.getAllComment(goodId);
    }

    @Override
    public List<Comment> getNewestComment(Integer goodId) {
        return commentMapper.getNewestComment(goodId);
    }

    @Override
    public List<Comment> getGreatComment(Integer goodId) {
        return commentMapper.getGreatComment(goodId);
    }

    @Override
    public List<Comment> getMiddleComment(Integer goodId) {
        return commentMapper.getMiddleComment(goodId);
    }

    @Override
    public List<Comment> getBadComment(Integer goodId) {
        return commentMapper.getBadComment(goodId);
    }

    @Override
    public int getCount(Integer goodId) {
        return commentMapper.getCount(goodId);
    }

    @Override
    public int getGreatCount(Integer goodId) {
        return commentMapper.getGreatCount(goodId);
    }

    @Override
    public int getMiddleCount(Integer goodId) {
        return commentMapper.getMiddleCount(goodId);
    }

    @Override
    public int getBadCount(Integer goodId) {
        return commentMapper.getBadCount(goodId);
    }

    @Override
    public Page<Comment> getCommentByShopperId(Integer shopperId, Integer currentPage) {
        int startNum = (currentPage-1)*10;
        int Count =commentMapper.count(shopperId);
        int pageCount=Count%10==0?Count/10:Count/10+1;
        List<Comment> commentList = commentMapper.getCommentByShopperId(shopperId,startNum,10);
        System.out.println(commentList);
        Page<Comment> commentPage = new Page<>(pageCount,currentPage,commentList);
        return commentPage;
    }
}
