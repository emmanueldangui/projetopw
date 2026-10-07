package br.edu.utfpr.pb.pw44s.server.mapper;

import br.edu.utfpr.pb.pw44s.server.dto.OrderDTO;
import br.edu.utfpr.pb.pw44s.server.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {OrderItemsMapper.class, AddressMapper.class})
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateTime", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "address.id", source = "addressId")
    Order toEntity(OrderDTO dto);

    @Mapping(target = "addressId", source = "address.id")
    @Mapping(target = "deliveryAddress", source = "address")
    OrderDTO toDTO(Order order);
}