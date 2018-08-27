/*
Задание 3. Таблица БД Students
 */
import java.sql.*;//библиотека для работы с SQL

public class StudyDateBase {
    private static Connection connection; //объект, необходимый для подкл. к БД
    private Statement stmt; //объект для отправки SQL-запросов из приложения в БД
    private ResultSet rs;//объект, который возвращает Statement после отправки запроса
    private static PreparedStatement pstmt; //объект для подготовленных SQL-запросов

    public static void main(String[] args) {
        //для себя создал main()
        StudyDateBase studyDB = new StudyDateBase();//инициализирую объект БД для работы с методами

        try{
            studyDB.connect();//устанавливаю соединение с БД

            //создаю таблицу students:
            studyDB.stmt.execute("CREATE TABLE IF NOT EXISTS Students (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "surname TEXT NOT NULL," +
                    "score INTEGER NOT NULL);");

            //добавляю записи:
            //int a = studyDB.add("Grishin", 80);
            //System.out.println(a);
            //studyDB.add("Sotnikov", 78);
            //studyDB.add("Kolodina", 89);

            //обновляю запись:
            //System.out.println(studyDB.update("Kolodina",55));

            //вывожу инфу о студентах:
            studyDB.printResult(studyDB.readBeetween(50, 79));
            System.out.println(studyDB.readScore("Kolodina"));


            //удаление строк:
            //studyDB.stmt.execute("DELETE FROM Students WHERE score = 65");



        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                disconnect();//закрываю соединение с БД
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    //для доступа к stmt из других классов
    public Statement getStmt() {
        return stmt;
    }

    //метод connect для установки соединения:
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");//указываем, какую будем использовать JDBC
        connection = DriverManager.getConnection("jdbc:sqlite:study.db");//указываем адрес к БД
        stmt = connection.createStatement();//инициализируем объект stmt в момент подключения к БД
    }

    //метод для закрытия соединения:
    public static void disconnect() throws SQLException {
        connection.close();
    }

    //метод добавления записи:
    public int add(String surname, int score) throws SQLException {
        //pstmt = StudyDateBase.connection.prepareStatement("INSERT INTO Students (surname, score) VALUES (?, ?)");
        int result = stmt.executeUpdate("INSERT INTO Students (surname, score) VALUES ('" + surname + "', " + score +")");
        return result;
    }
    //метод обновления строк:
    public int update(String surname, int scoreChange) throws SQLException {
        int result = stmt.executeUpdate("UPDATE Students SET score = " + scoreChange + " WHERE surname = '" + surname + "'");
        return result;
    }

    //метод чтения данных по величине баллов:
    public ResultSet readBeetween(int minScore, int maxScore) throws SQLException {
        return stmt.executeQuery("SELECT * FROM Students WHERE score BETWEEN " + minScore + " AND " + maxScore);
    }

    //метод вывода на консоль результата SQL-запроса на чтение данных
    public void printResult(ResultSet rs) throws SQLException {
        if (!rs.isBeforeFirst()) System.out.println("Нет результатов, удовлетворяющих условиям запроса");//проверяю, есть ли результат запроса без считывания
        else {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + ") Студент(ка): " + rs.getString(2) + ". Баллы: " + rs.getInt(3));//вывожу результат запроса
            }
        }
    }

    //метод чтения баллов по фамилии:
    public int readScore(String surname) throws SQLException {
        int readScore = 0;
        rs = stmt.executeQuery("SELECT score FROM Students WHERE surname = '" + surname + "'");
        if (rs.isBeforeFirst()) {
            while (rs.next()){
                readScore = rs.getInt(1);
            }
        } else System.out.println("Нет такой фамилии");
        return readScore;
    }
}
