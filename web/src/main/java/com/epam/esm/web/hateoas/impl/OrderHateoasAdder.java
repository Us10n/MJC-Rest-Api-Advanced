package com.epam.esm.web.hateoas.impl;

import com.epam.esm.service.dto.Order;
import com.epam.esm.service.dto.OrderDetailDto;
import com.epam.esm.web.controller.OrderController;
import com.epam.esm.web.hateoas.HateoasAdder;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderHateoasAdder implements HateoasAdder<OrderDetailDto> {

    private static final Class<OrderController> ORDER_CONTROLLER = OrderController.class;

    @Override
    public void addLinksToEntity(OrderDetailDto entity) {
        entity.add(linkTo(methodOn(ORDER_CONTROLLER).readOrderById(entity.getOrderId())).withSelfRel());
        entity.add(linkTo(methodOn(ORDER_CONTROLLER).readOrdersByUserId(entity.getUserId(),1,10)).withRel("userOrders"));
        Order order=new Order(entity.getUserId(),entity.getGiftCertificateId());
        entity.add(linkTo(methodOn(ORDER_CONTROLLER).createOrder(order)).withRel("create"));
    }

    @Override
    public void addLinksToCollection(PagedModel<OrderDetailDto> model) {
        PagedModel.PageMetadata metadata = model.getMetadata();
        int page = (int) metadata.getNumber();
        int limit = (int) metadata.getSize();
        int totalPages = (int) metadata.getTotalPages();

        model.add(linkTo(methodOn(ORDER_CONTROLLER).readAllOrders(page, limit)).withSelfRel());
        if (page < totalPages) {
            model.add(linkTo(methodOn(ORDER_CONTROLLER).readAllOrders(page + 1, limit)).withRel("next"));
        }
        if (page > 1) {
            model.add(linkTo(methodOn(ORDER_CONTROLLER).readAllOrders(page - 1, limit)).withRel("prev"));
        }
        model.getContent().forEach(this::addLinksToEntity);
    }
}
