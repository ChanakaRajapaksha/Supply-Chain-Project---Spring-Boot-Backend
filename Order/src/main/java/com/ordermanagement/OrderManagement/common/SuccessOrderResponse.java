package com.ordermanagement.OrderManagement.common;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.ordermanagement.OrderManagement.dto.OrderDTO;
import lombok.Getter;

@Getter
public class SuccessOrderResponse implements OrderResponse {
    @JsonUnwrapped
    private final OrderDTO order;

    public SuccessOrderResponse(OrderDTO order) {
        this.order = order;
    }
}
