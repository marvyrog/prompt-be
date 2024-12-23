package com.hsl.prompt_be.controllers;

import com.hsl.prompt_be.entities.models.Order;
import com.hsl.prompt_be.entities.requests.OrderRequest;
import com.hsl.prompt_be.entities.requests.SearchOrderRequest;
import com.hsl.prompt_be.entities.requests.UpdateOrderRequest;
import com.hsl.prompt_be.entities.responses.KorapayCheckoutResponse;
import com.hsl.prompt_be.exceptions.OrderNotFoundException;
import com.hsl.prompt_be.exceptions.PrinthubException;
import com.hsl.prompt_be.exceptions.UserNotFoundException;
import com.hsl.prompt_be.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "create order endpoint")
    @PostMapping("{printerId}")
    public Order createOrder(@PathVariable UUID printerId, @RequestBody OrderRequest request) throws PrinthubException {

        return orderService.createOrder(printerId, request);
    }

    @Operation(summary = "search order endpoint", description = "can search by any property of the order")
    @PostMapping("search")
    public List<Order> searchOrders(@RequestBody SearchOrderRequest request) {

        return orderService.searchOrders(request);
    }

    @Operation(summary = "update order endpoint")
    @PutMapping("{orderId}")
    public Order updateOrder(@PathVariable UUID orderId, @RequestBody UpdateOrderRequest request) throws PrinthubException {

        return orderService.updateOrder(orderId, request);
    }

    @Operation(summary = "get order by id endpoint")
    @GetMapping("{orderId}")
    public Order getOrderById(@PathVariable UUID orderId) throws OrderNotFoundException {

        return orderService.getOrderById(orderId);
    }

    @Operation(summary = "checkout endpoint ")
    @GetMapping("payments/initiate/{orderId}")
    public KorapayCheckoutResponse onlineOrderPayment(@PathVariable UUID orderId) throws OrderNotFoundException, UserNotFoundException {

        return orderService.onlineOrderPayment(orderId);
    }
}
