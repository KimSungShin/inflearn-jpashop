package jpabook.jpashop.repository.order.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryDto() {
        List<OrderQueryDto> orders = findOrders();
        orders.forEach(o -> {
            List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
            o.setOrderItems(orderItems);
        });
        return orders;
    }

    public List<OrderQueryDto> findAllByDto_optimization() {
        List<OrderQueryDto> orders = findOrders();
        List<Long> ids = orders.stream().map(o -> o.getOrderId()).collect(Collectors.toList());
        List<OrderItemQueryDto> orderItems = findOrderItemMap(ids);

        Map<Long, List<OrderItemQueryDto>> map =  orderItems.stream().collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));

        orders.forEach(o -> {
            o.setOrderItems(map.get(o.getOrderId()));
        });

        return orders;
    }

    public List<OrderFlatDto> findAllByDto_flat() {
        return em.createQuery(
                "select new jpabook.jpashop.repository.order.query.OrderFlatDto(o.id, m.name, o.orderDate, o.status, d.address, oi.item.name, oi.orderPrice, oi.count)" +
                        "from Order o" +
                        " join o.member m " +
                        " join o.delivery d" +
                        " join o.orderItems oi" +
                        " join oi.item i ", OrderFlatDto.class)
                .getResultList();

    }

    private List<OrderItemQueryDto> findOrderItemMap(List<Long> ids) {
        return em.createQuery("" +
                "select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.item.name, oi.order.id, oi.orderPrice, oi.count) " +
                "from OrderItem  oi" +
                " join oi.item i" +
                " where oi.order.id in :orderIds", OrderItemQueryDto.class)
                .setParameter("orderIds", ids)
                .getResultList();
    }

    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return em.createQuery("" +
                "select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.item.name, oi.order.id, oi.orderPrice, oi.count) " +
                "from OrderItem  oi" +
                " join oi.item i" +
                " where oi.order.id = :orderId", OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    private List<OrderQueryDto> findOrders() {
        return em.createQuery("select new jpabook.jpashop.repository.order.query.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) from Order o" +
                " join o.member m" +
                " join o.delivery d", OrderQueryDto.class)
                .getResultList();
    }
}
