package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcHello {

    public static void main(String[] args) {
        // 연결 정보
        String url = "jdbc:mysql://localhost:3306/shop?serverTimezone=Asia/Seoul";
        String user = "xxxxxxxx";
        String password = "xxxxxxxxx";
        // 실무에서 비밀번를 코드를 직접 작성 쓰지 않습니다.
        // 환경변수나 설정파일 사용할 수 있음 (.env, application.properties, application.ymal)

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
