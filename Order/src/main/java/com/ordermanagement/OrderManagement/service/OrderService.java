package com.ordermanagement.OrderManagement.service;

import com.example.inventorymanagement.InventoryManagement.dto.InventoryDTO;
import com.ordermanagement.OrderManagement.common.ErrorOrderResponse;
import com.ordermanagement.OrderManagement.common.OrderResponse;
import com.ordermanagement.OrderManagement.common.SuccessOrderResponse;
import com.ordermanagement.OrderManagement.dto.OrderDTO;
import com.ordermanagement.OrderManagement.model.Order;
import com.ordermanagement.OrderManagement.repo.OrderRepo;
import com.productmanagement.ProductManagement.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@Transactional
public class OrderService {

    private final WebClient inventoryWebClient;
    private final WebClient productWebClient;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public OrderService(WebClient inventoryWebClient, WebClient productWebClient, OrderRepo orderRepo, ModelMapper modelMapper) {
        this.inventoryWebClient = inventoryWebClient;
        this.productWebClient = productWebClient;
        this.orderRepo = orderRepo;
        this.modelMapper = modelMapper;
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepo.findAll();
        return modelMapper.map(orders, new TypeToken<List<OrderDTO>>(){}.getType());
    }

    public OrderResponse saveOrder(OrderDTO orderDto) {

        Integer itemId = orderDto.getItemId();

        try {
            InventoryDTO inventoryResponse = inventoryWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/inventory/{inventoryId}").build(itemId))
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();

            assert inventoryResponse != null;

            Integer productId = inventoryResponse.getProductId();

            ProductDTO productResponse = productWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/product/{productId}").build(productId))
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .block();

            assert productResponse != null;

            if (inventoryResponse.getQuantity() > 0) {
                if (productResponse.getForSale() == 1) {
                    orderRepo.save(modelMapper.map(orderDto, Order.class));
                } else {
                   return new ErrorOrderResponse("This item not for sale!");
                }
                return new SuccessOrderResponse(orderDto);
            } else {
                return new ErrorOrderResponse("Item out of stock, please check back later!");
            }

        } catch (WebClientResponseException e) {
            if (e.getStatusCode().is5xxServerError()) {
                return new ErrorOrderResponse("Item not found!");
            }
        }

        return null;
    }

    public OrderDTO updateOrder(OrderDTO orderDto) {
        orderRepo.save(modelMapper.map(orderDto, Order.class));
        return orderDto;
    }

    public String deleteOrder(Integer orderId) {
        orderRepo.deleteById(orderId);
        return "Order deleted successfully";
    }

    public OrderDTO getOrder(Integer orderId) {
        Order order = orderRepo.getOrderById(orderId);
        return modelMapper.map(order, OrderDTO.class);
    }

}
