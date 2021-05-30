package jpabook.jpashop.repository.order.query;

import lombok.Data;

@Data
public class OrderItemQueryDto {
    private String itemName;
    private Long orderId;
    private int orderPrice;
    private int count;


    public OrderItemQueryDto(String itemName, Long orderId, int orderPrice, int count) {
        this.itemName = itemName;
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.count = count;
    }


}
