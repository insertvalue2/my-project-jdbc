package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcHello2 {

    public static void main(String[] args) {
        // 연결 정보
        String url = "jdbc:mysql://localhost:3306/shop?serverTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        System.out.println("로컬 컴퓨터에 저장한 환경 변수 이름으로 값을 확인해 보자");
        System.out.println("user : " + user);
        System.out.println("password : " + password);

        try (Connection connection = DriverManager
                .getConnection(url, user, password)) {
            System.out.println("연결 성공 됨.");
            System.out.println("DB 제품명 : " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB 버전 : " + connection.getMetaData().getDatabaseProductVersion());

        } catch (SQLException e) {
            System.out.println("DB랑 연결 오류 발생 함");
            e.printStackTrace();
        }
    }

}
