package com.elarsenaldecamisetas.app.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.elarsenaldecamisetas.app.dto.OrderDetailDTO;
import com.elarsenaldecamisetas.app.entity.OrderDetail;

@Component
public class OrderDetailMapper {
    private final ModelMapper mapper = new ModelMapper();

    public OrderDetailMapper() {
        mapper.typeMap(OrderDetail.class, OrderDetailDTO.class).addMappings(m -> {
            m.map(src -> src.getProduct().getId(), OrderDetailDTO::setProductId);
            m.map(src -> src.getOrder().getId(), OrderDetailDTO::setOrderId);
        });

    }

    public OrderDetailDTO toDTO(OrderDetail orderDetail) {
        return mapper.map(orderDetail, OrderDetailDTO.class);
    }

    public OrderDetail toEntity(OrderDetailDTO orderDetailDTO) {
        return mapper.map(orderDetailDTO, OrderDetail.class);
    }
}
