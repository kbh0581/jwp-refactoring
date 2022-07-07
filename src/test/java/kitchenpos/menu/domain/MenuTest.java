package kitchenpos.menu.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import kitchenpos.global.domain.Price;
import kitchenpos.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("메뉴")
class MenuTest {

    MenuProducts 상품들;

    @BeforeEach
    void setUp() {
        Product product1 = new Product("상품1", Price.from(20));
        Product product2 = new Product("상품2", Price.from(30));
        MenuProduct menuProduct1 = new MenuProduct(1L, 1);
        MenuProduct menuProduct2 = new MenuProduct(2L, 2);

        상품들 = MenuProducts.from(Arrays.asList(menuProduct1, menuProduct2));
    }


    @DisplayName("메뉴의 가격은 구성하고 있는 메뉴 상품들의 가격(상품가격 * 수량)의 합계보다 작아야 한다.")
    @Test
    void noMenuPriceValid() {
        //when & then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Menu("이름", Price.from(1000), MenuGroup.from("메뉴그룹1"), 상품들));
    }

    @DisplayName("메뉴는 메뉴 그룹이 없으면 등록할 수 없다.")
    @Test
    void noMenuGroup() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Menu("이름", Price.from(10), null, 상품들));
    }

    @DisplayName("메뉴는 메뉴의 금액 없으면 등록할 수 없다.")
    @Test
    void noPrice() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Menu("이름", null, MenuGroup.from("메뉴그룹1"), 상품들));
    }

    @DisplayName("메뉴는 이름 없으면 등록할 수 없다.")
    @Test
    void noName() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Menu(null, Price.from(10), MenuGroup.from("메뉴그룹1"), 상품들));
    }

    @DisplayName("메뉴는 상품들이 없으면 등록할 수 없다.")
    @Test
    void noMenuProducts() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Menu("이름", Price.from(10), MenuGroup.from("메뉴그룹1"), null));
    }


}
