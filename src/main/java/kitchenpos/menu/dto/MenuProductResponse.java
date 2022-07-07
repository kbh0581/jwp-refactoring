package kitchenpos.menu.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import kitchenpos.global.domain.Price;
import kitchenpos.global.domain.Quantity;
import kitchenpos.menu.domain.MenuProduct;

public class MenuProductResponse {
    private long seq;
    private String name;
    private BigDecimal price;

    private long quantity;

    public MenuProductResponse() {
    }

    public MenuProductResponse(long seq, String name, Price price, Quantity quantity) {
        this.seq = seq;
        this.name = name;
        this.price = price.value();
        this.quantity = quantity.value();
    }

    public static MenuProductResponse of(MenuProduct menuProduct) {
        return null;
//        return new MenuProductResponse(menuProduct.getSeq(), menuProduct.getProductName(), menuProduct.getPrice(),
//                menuProduct.getQuantity());
    }

    public static List<MenuProductResponse> of(List<MenuProduct> menuProducts) {
       return menuProducts.stream()
                .map(MenuProductResponse::of)
                .collect(Collectors.toList());
    }

    public long getSeq() {
        return seq;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getQuantity() {
        return quantity;
    }
}
