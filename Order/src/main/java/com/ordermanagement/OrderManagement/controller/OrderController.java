package com.ordermanagement.OrderManagement.controller;

import com.base.base.dto.OrderEventDTO;
import com.ordermanagement.OrderManagement.common.OrderResponse;
import com.ordermanagement.OrderManagement.dto.OrderDTO;
import com.ordermanagement.OrderManagement.kafka.OrderProducer;
import com.ordermanagement.OrderManagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProducer orderProducer;

    @GetMapping("/")
    public List<OrderDTO> getOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/createorder")
    public OrderResponse addOrder(@RequestBody OrderDTO orderDto) {
        OrderEventDTO orderEventDTO = new OrderEventDTO();
        orderEventDTO.setMessage("Order is committed!");
        orderEventDTO.setStatus("Pending...");
        orderProducer.sendMessage(orderEventDTO);

        return orderService.saveOrder(orderDto);
    }

    @PutMapping("/updateorder")
    public OrderDTO editOrder(@RequestBody OrderDTO orderDto) {
        return orderService.updateOrder(orderDto);
    }

    @DeleteMapping("/deleteorder/{orderId}")
    public String deleteOrder(@PathVariable Integer orderId) {
        return orderService.deleteOrder(orderId);
    }

    @GetMapping("/order/{orderId}")
    public OrderDTO getOrder(@PathVariable Integer orderId) {
        return orderService.getOrder(orderId);
    }

}
