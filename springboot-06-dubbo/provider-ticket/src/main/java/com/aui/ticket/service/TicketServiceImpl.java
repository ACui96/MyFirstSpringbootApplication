package com.aui.ticket.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;


/**
 * @author acui
 */
@Component
@Service//将服务发布出去
public class TicketServiceImpl implements TicketService{

    @Override
    public String getTicket() {
        return "《厉害了，我的国》";
    }
}
