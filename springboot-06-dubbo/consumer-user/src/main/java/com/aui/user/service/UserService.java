package com.aui.user.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aui.ticket.service.TicketService;

import org.springframework.stereotype.Service;

/**
 * @author acui
 */
@Service
public class UserService {

    @Reference
    TicketService ticketService;

    public void hello() {
        String ticket = ticketService.getTicket();
        System.out.println("买到票了："+ticket);
    }


}
