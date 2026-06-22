package com.elarsenaldecamisetas.app.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.elarsenaldecamisetas.app.dto.OrderDTO;
import com.elarsenaldecamisetas.app.entity.Order;

@Component
public class OrderMapper {
    private final ModelMapper mapper = new ModelMapper();

    public OrderMapper() {
        mapper.typeMap(Order.class, OrderDTO.class).addMappings(m -> {
            m.map(src -> src.getUser().getId(), OrderDTO::setUserId);
            m.map(src -> src.getUser().getName(), OrderDTO::setUserName);
        });
    }

    public OrderDTO toDTO(Order order) {
        return mapper.map(order, OrderDTO.class);
    }

    public Order toEntity(OrderDTO orderDTO) {
        return mapper.map(orderDTO, Order.class);
    }
}
