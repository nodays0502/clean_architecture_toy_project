package com.study.cleanArch.order.adapter.in.web;

import static org.springframework.http.HttpStatus.OK;

import com.study.cleanArch.common.auth.PrincipalDetails;
import com.study.cleanArch.member.domain.MemberNo;
import com.study.cleanArch.order.adapter.in.web.dto.GetOrderResponseDto;
import com.study.cleanArch.order.adapter.in.web.dto.OrderLineDto;
import com.study.cleanArch.order.adapter.in.web.dto.RegisterOrderRequestDto;
import com.study.cleanArch.order.application.port.in.CancelOrderUseCase;
import com.study.cleanArch.order.application.port.in.GetOrderQueryUseCase;
import com.study.cleanArch.order.application.port.in.RegisterOrderUseCase;
import com.study.cleanArch.order.domain.Order;
import com.study.cleanArch.order.domain.OrderLine;
import com.study.cleanArch.order.domain.OrderNo;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final GetOrderQueryUseCase getOrderQueryUseCase;
    private final RegisterOrderUseCase registerOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;

    @GetMapping("/order/{no}")
    public ResponseEntity getOrderByNo(@PathVariable long no) {
        Order order = getOrderQueryUseCase.findOrderByNo(new OrderNo(no));
        GetOrderResponseDto getOrderResponseDto = makeOrderToOrderResponseDto(order);
        return new ResponseEntity<GetOrderResponseDto>(getOrderResponseDto, OK);
    }

    @GetMapping("/order/orders")
    public ResponseEntity getOrderList(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<Order> orderList = getOrderQueryUseCase.findOrderList(
            principalDetails.getMember().getMemberNo());
        List<GetOrderResponseDto> orderResponseDtos = orderList.stream()
            .map(this::makeOrderToOrderResponseDto).collect(Collectors.toList());
        return new ResponseEntity<List<GetOrderResponseDto>>(orderResponseDtos, OK);
    }

    @PostMapping("/order")
    public ResponseEntity registerOrder(
        @AuthenticationPrincipal PrincipalDetails principalDetails,
        @Valid @RequestBody RegisterOrderRequestDto registerOrderRequestDto) {
        List<OrderLine> orderLines = registerOrderRequestDto.getOrderLineDtoLists().stream()
            .map(OrderLineDto::of).collect(Collectors.toList());

        Order order = Order.withOutNo(
            new MemberNo(principalDetails.getMember().getMemberNo().getValue()), orderLines,
            registerOrderRequestDto.getOrderState(), registerOrderRequestDto.getShippingInfo());
        registerOrderUseCase.registerOrder(order);

        return new ResponseEntity<GetOrderResponseDto>(OK);
    }

    @PostMapping("/order/{no}/cancel")
    public ResponseEntity cancelOrder(@PathVariable long no ) {
        cancelOrderUseCase.cancelOrder(new OrderNo(no));
        return new ResponseEntity<GetOrderResponseDto>(OK);
    }

    private GetOrderResponseDto makeOrderToOrderResponseDto(Order order) {
        List<OrderLineDto> orderLineDtoList = order.getOrderLines().stream()
            .map(o -> new OrderLineDto(o.getNo(), o.getPrice().getValue(), o.getQuantity()))
            .collect(
                Collectors.toList());
        GetOrderResponseDto getOrderResponseDto = new GetOrderResponseDto(order.getNo().getValue(),
            orderLineDtoList, order.getOrderState(),
            order.getTotalPrice(), order.getShippingInfo());
        return getOrderResponseDto;
    }
}
