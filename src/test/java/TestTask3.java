import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;//для работы c SQL

public class TestTask3 {
    private StudyDateBase stb;

    //действие будет выполняться перед каждым тестом
    @Before
    public void startTest(){
        stb = new StudyDateBase();//создание экземпляра класса с тестируемым методом
        //установление соединения с БД:
        try{
            stb.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //действие будет выполняться после каждого теста - закрытие соединения с БД
    @After
    public void stopTest(){
        try {
            stb.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //тест на добавление записи:
    @Test
    public void testAdd(){
        try {
            //проверка - если добавление выполнится, то вернётся 1
            Assert.assertEquals(1,stb.add("Zelentsov",65));

            //для удаления внесённых изменений (по условию задачи):
            stb.getStmt().execute("DELETE FROM Students WHERE score = 65");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //тест на изменение записи:
    @Test
    public void testUpdate(){
        try {
            int scoreBeforeChange = stb.readScore("Sotnikov");//запоминаю первоначальное значение баллов

            //проверка - если изменение внесётся, то вернётся 1
            Assert.assertEquals(1,stb.update("Sotnikov",78));

            //для исправления внесённых изменений (по условию задачи):
            stb.update("Sotnikov",scoreBeforeChange);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
