package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.OrderDTO;
import br.edu.utfpr.pb.pw44s.server.mapper.OrderMapper;
import br.edu.utfpr.pb.pw44s.server.model.Order;
import br.edu.utfpr.pb.pw44s.server.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final IOrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(IOrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    // POST http://localhost:8080/orders  (precisa de token) - finalizar compra
    @PostMapping
    public ResponseEntity<OrderDTO> save(@RequestBody @Valid OrderDTO orderDTO) {
        Order orderSaved = orderService.save(orderMapper.toEntity(orderDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.toDTO(orderSaved));
    }

    // GET http://localhost:8080/orders  (precisa de token) - pedidos do usuário logado
    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        return ResponseEntity.ok(
                orderService.findByAuthenticatedUser()
                        .stream()
                        .map(orderMapper::toDTO)
                        .collect(Collectors.toList())
        );
    }

    // GET http://localhost:8080/orders/1  (precisa de token) - só se o pedido for do usuário
    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
        Order order = orderService.findByIdAndAuthenticatedUser(id);
        if (order != null) {
            return ResponseEntity.ok(orderMapper.toDTO(order));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}