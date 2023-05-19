package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.Message;
import com.pengling.beauty.entity.Page;
import com.pengling.beauty.service.MessageService;
import com.pengling.beauty.service.MessageServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;
    @RequestMapping("GET/MessagesASC/{currentPage}")
    @ResponseBody
    public String getMessagesASC(@PathVariable("currentPage") String currentPage){
        Page<Message> messagePage = messageService.selectAllByDateASC(Integer.parseInt(currentPage));
        System.out.println(messagePage);
        return JSONArray.toJSONString(messagePage);
    }
    @RequestMapping("GET/MessageDESC/{currentPage}")
    @ResponseBody
    public String getMessagesDESC(@PathVariable("currentPage") String currentPage){
        Page<Message> messagePage = messageService.selectAllByDateDESC(Integer.parseInt(currentPage));
        return JSONArray.toJSONString(messagePage);
    }
}
