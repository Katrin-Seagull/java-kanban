package test;

import model.Epic;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EpicTest {

    @Test
    public void testEpicEqualityById() {
        // Создаем два эпика с одинаковым ID, но разными названиями и описаниями
        Epic epic1 = new Epic("Название эпика 1", "Описание эпика 1");
        Epic epic2 = new Epic("Другое название эпика", "Другое описание эпика");

        epic1.setId(1);
        epic2.setId(1);
        // Проверяем, что эпики равны по ID
        assertEquals(epic1, epic2);
    }

}