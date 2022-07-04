package kitchenpos.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import kitchenpos.dto.OrderLineItemRequest;
import org.springframework.util.ObjectUtils;

@Embeddable
public class OrderLineItems {

    private static final int MIN_ORDER_LINE_NUMBER = 1;
    private static final int SEQ_START_INDEX = 1;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "order")
    private List<OrderLineItem> orderLineItemElements = new ArrayList<>();

    protected OrderLineItems() {

    }

    private OrderLineItems(List<OrderLineItem> orderLineItemElements) {
        valid(orderLineItemElements);
        this.orderLineItemElements = orderLineItemElements;
        generateSeq();
    }


    private void valid(List<OrderLineItem> orderLineItemElements) {
        if (ObjectUtils.isEmpty(orderLineItemElements)) {
            throw new IllegalArgumentException("주문목록은 존재 해야합니다.");
        }

        if (orderLineItemElements.size() < MIN_ORDER_LINE_NUMBER) {
            throw new IllegalArgumentException("주문목록은 하나 이상 있어야합니다.");
        }
    }

    public static OrderLineItems of(List<OrderLineItemRequest> requestList) {
        return new OrderLineItems(requestList
                .stream()
                .map((it) -> OrderLineItem.of(it.getMenuId(), it.getQuantity()))
                .collect(Collectors.toList()));
    }

    public static OrderLineItems from(List<OrderLineItem> orderLineItems) {
        return new OrderLineItems(orderLineItems);
    }

    public List<OrderLineItem> value() {
        return Collections.unmodifiableList(orderLineItemElements);
    }

    public void changeOrder(Order order) {
        this.orderLineItemElements.forEach((it) -> it.changeOrder(order));
    }


    private void generateSeq() {
        for (int i = SEQ_START_INDEX; i <= orderLineItemElements.size(); i++) {
            orderLineItemElements.get(i - SEQ_START_INDEX).changeSeq(i);
        }
    }
}
