package kitchenpos.menu.domain;

import java.util.List;
import java.util.stream.Collectors;
import kitchenpos.menu.dto.MenuProductRequest;
import kitchenpos.menu.dto.MenuRequest;
import kitchenpos.product.repository.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class MenuValidator {

    private final ProductRepository productRepository;

    public MenuValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public void validateMenuProduct(MenuRequest menuRequest) {
        List<Long> productIds = menuRequest.getMenuProducts()
                .stream()
                .map(MenuProductRequest::getProductId).collect(Collectors.toList());

//        productIds.stream()
//
//        Amount.
//
//        menuRequest.getPrice();
//
//        menuRequest.getMenuProducts().stream().map((productRequest) -> productRequest.)
//        productRepository.findAllById(menuRequest.getMenuProducts().stream().map())
//        menuRequest.getMenuProducts()

    }


    private void validateProduct(MenuProduct menuProduct) {
        if (ObjectUtils.isEmpty(menuProduct)) {
            throw new IllegalArgumentException("메뉴상품이 비어있습니다");
        }
    }


}
