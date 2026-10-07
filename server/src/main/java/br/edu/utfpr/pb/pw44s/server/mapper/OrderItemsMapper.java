package br.edu.utfpr.pb.pw44s.server.mapper;

import br.edu.utfpr.pb.pw44s.server.dto.OrderItensDTO;
import br.edu.utfpr.pb.pw44s.server.model.OrderItems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product.id", source = "productId")
    OrderItems toEntity(OrderItensDTO dto);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    OrderItensDTO toDTO(OrderItems entity);
}