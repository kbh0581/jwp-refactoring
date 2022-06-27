package kitchenpos.helper;

import java.lang.reflect.Field;
import kitchenpos.domain.Menu;
import kitchenpos.domain.Product;

public class ReflectionHelper {
    public static void SetProductId(Long id, Product product){
        Field field = null;
        try {
            field = Product.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(product, id);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setMenuId(Long id, Menu menu){
        Field field = null;
        try {
            field = Menu.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(menu, id);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
