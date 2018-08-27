/*
Java 3. Обзор средств разработки
ДЗ №6: Тестирование методов

Студент: Гришин Дмитрий
 */

import java.util.Arrays;

// Задание 1. Возврат массива после четвёрки
// Задание 2. Проверка присутствия в массиве и 1 и 4.
public class WorkWithArray {

    public static void main(String[] args) {
        //для себя создал main()
        int[] arrayTest = {1, 2, 4, 4, 2, 3, 4, 1, 4};
        int[] arrayFrom;
        WorkWithArray a = new WorkWithArray();

        //для 1-го метода
        try{
            arrayFrom = a.arrayAfter4(arrayTest);
            System.out.println(Arrays.toString(arrayFrom));
        } catch (RuntimeException e){
            System.out.println(e);
        }
        //для второго метода
        System.out.println(a.isThereOneAndFour(new int[]{2, 5, 8, 7, 4}));
        System.out.println(a.isThereOneAndFour(new int[]{1, 2, 3, 5, 9, 3}));
        System.out.println(a.isThereOneAndFour(arrayTest));
    }

    //метод возвращающий массив из элементов, следующих после последней четвёрки:
    public int[] arrayAfter4(int... intArray){
        int indexOf4 = intArray.length;
        int[] arrayAfter4;
        for (int i = 0; i < intArray.length; i++){
            if(intArray[i] == 4) indexOf4 = i;
        }
        //выброс исключений на случаи отсутвия четвёрок, либо идёт последней в массиве:
        if (indexOf4 == (intArray.length - 1)) {
            throw new RuntimeException("Четвёрка шла последней, после неё элементов нет.");
        }
        if (indexOf4 == intArray.length) {
            throw new RuntimeException("Четвёрок во входном массиве нет.");
        }
        //задаю ёмкость выходного массива:
        arrayAfter4 = new int[intArray.length - 1 - indexOf4];
        //заполняю выходной массив:
        for(int i = 0; i < intArray.length - 1 - indexOf4; i++){
            arrayAfter4[i] = intArray[indexOf4 + 1 + i];
        }
        return arrayAfter4;
    }

    //метод возвращает false, если во входном массиве нет хоть одной четвёрки или единицы.
    public boolean isThereOneAndFour(int[] intArray){
        int countOnes = 0, countFours = 0; //счётчики единиц и четвёрок
        //перебираю массив:
        for(int element: intArray){
            switch (element){
                case 1:
                    countOnes++;
                    break;
                case 4:
                    countFours++;
                    break;
            }
        }
        return (countOnes > 0 && countFours > 0); //если хотя бы один из счётчиков = 0 вернётся false
    }
}
