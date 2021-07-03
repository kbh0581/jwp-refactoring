package kitchenpos.application;

import static kitchenpos.util.TestDataSet.원플원_후라이드;
import static kitchenpos.util.TestDataSet.추천_메뉴_그륩;
import static kitchenpos.util.TestDataSet.후라이드;
import static kitchenpos.util.TestDataSet.후라이드_2개;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import kitchenpos.dao.MenuDao;
import kitchenpos.dao.MenuGroupDao;
import kitchenpos.dao.MenuProductDao;
import kitchenpos.dao.ProductDao;
import kitchenpos.domain.Menu;

@SpringBootTest
public class MenuServiceTest {

    @Mock
    private MenuDao menuDao;

    @Mock
    private MenuGroupDao menuGroupDao;

    @Mock
    private MenuProductDao menuProductDao;

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private MenuService menuService;

    @BeforeEach
    void setUp() {
        //given
        given(menuGroupDao.existsById(any())).willReturn(true);
        given(productDao.findById(후라이드.getId())).willReturn(Optional.of(후라이드));
        given(menuDao.save(any())).willReturn(원플원_후라이드);
        given(productDao.save(any())).willReturn(후라이드);
        given(menuProductDao.save(후라이드_2개)).willReturn(후라이드_2개);
    }

    @Test
    @DisplayName("새로운 메뉴 정상 성공 케이스")
    void create() {

        //when
        Menu result = menuService.create(원플원_후라이드);

        // then
        assertThat(result.getName()).isEqualTo(원플원_후라이드.getName());
        assertThat(result.getPrice()).isEqualTo(원플원_후라이드.getPrice());
        assertThat(result.getMenuGroupId()).isEqualTo(원플원_후라이드.getMenuGroupId());
        assertThat(result.getMenuProducts()).containsExactly(후라이드_2개);

        verify(productDao, times(1)).findById(any());
        verify(menuDao, times(1)).save(result);

    }

    @Test
    @DisplayName("메뉴의 가격이 없거나 0보다 작을 경우 실패한다.")
    void noMony() {
        //when
        Menu 음수 = new Menu(1L, "후라이드+후라이드", BigDecimal.valueOf(-1), 추천_메뉴_그륩.getId(),
            Arrays.asList(후라이드_2개));

        Menu 제로 = new Menu(1L, "후라이드+후라이드", BigDecimal.valueOf(-1), 추천_메뉴_그륩.getId(),
            Arrays.asList(후라이드_2개));

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            menuService.create(음수);
            menuService.create(제로);
        });
    }

    @Test
    @DisplayName("존재하지 않는 메뉴 그룹에 등록할 경우 실패한다.")
    void already() {
        //given
        given(menuGroupDao.existsById(추천_메뉴_그륩.getId())).willReturn(false);

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            menuService.create(원플원_후라이드);
        });

        verify(menuGroupDao, times(1)).existsById(추천_메뉴_그륩.getId());
    }

    @Test
    @DisplayName("메뉴 가격이 상품 가격들의 합보다 클경우 실패한다.")
    void sumOver() {

        //given
        Menu 가격이_큰경우 = new Menu(1L, "후라이드+후라이드", BigDecimal.valueOf(100000), 추천_메뉴_그륩.getId(),
            Arrays.asList(후라이드_2개));
        // then
        assertThrows(IllegalArgumentException.class, () -> {
            menuService.create(가격이_큰경우);
        });
    }

    @Test
    @DisplayName("존재하지 않는 상품이 포함될 경우 실패한다.")
    void noProduct() {

        //given
        given(productDao.findById(any())).willReturn(Optional.empty());

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            menuService.create(원플원_후라이드);
        });

        verify(productDao, times(1)).findById(any());
    }

}
