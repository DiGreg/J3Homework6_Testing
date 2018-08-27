import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestTasks1and2 {
    private WorkWithArray a;

    //действие будет выполняться перед каждым тестом
    @Before
    public void startTest(){
        a = new WorkWithArray();//создание экземпляра класса с тестируемым методом
    }

    //Сами тесты:
    //Тесты для первой задачи:
    @Test
    @Ignore(value = "Уже проверял") //Игнорирование теста, который уже выполнялся
    //первый вариант входных данных
    public void test1(){
        int[] arrayTest = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        //использую метод, проверяющий массивы:
        Assert.assertArrayEquals(new int[]{1, 7}, a.arrayAfter4(arrayTest));
    }
    @Test
    @Ignore(value = "Уже проверял")
    //второй вариант входных данных
    public void test2(){
        int[] arrayTest = {5, 2, 3, 4, 2, 3, 5, 1, 7};
        Assert.assertArrayEquals(new int[]{2, 3, 5, 1, 7}, a.arrayAfter4(arrayTest));
    }
    //проверки на выброс ошибки
    @Test(expected = RuntimeException.class)
    @Ignore(value = "Уже проверял")
    public void test3(){
        int[] arrayTest = {5, 2, 3, 2, 3, 5, 1, 7};
        a.arrayAfter4(arrayTest);
    }
    @Test(expected = RuntimeException.class)
    @Ignore(value = "Уже проверял")
    public void test4(){
        int[] arrayTest = {5, 2, 3, 2, 0, 3, 5, 4};
        a.arrayAfter4(arrayTest);
    }

    //тесты для второй задачи
    @Test
    //проверка на true
    public void testTask2_1(){
        int[] arrayTest = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        Assert.assertEquals(true, a.isThereOneAndFour(arrayTest));
    }

    @Test
    //проверка на false №1
    public void testTask2_2(){
        int[] arrayTest = {3, 2, 3, 5, 2, 3};
        Assert.assertEquals(false, a.isThereOneAndFour(arrayTest));
    }

    @Test
    //проверка на false №2
    public void testTask2_3(){
        int[] arrayTest = {4, 2, 3, 5, 2, 3};
        Assert.assertEquals(false, a.isThereOneAndFour(arrayTest));
    }

    @Test
    //проверка на false №3
    public void testTask2_4(){
        int[] arrayTest = {1, 2, 3, 5, 9, 3};
        Assert.assertEquals(false, a.isThereOneAndFour(arrayTest));
    }

}

