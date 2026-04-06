package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDelete {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/shop2?serverTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER");
        String pwd = System.getenv("DB_PASSWORD");

        // UPDATE - name, 가격을 수정하는 쿼리를 진행할 예정
        // 텍스트블록 문법 JDK 13이후 사용 가능
        String sql = """ 
                    delete from product2 where id = ?
                """;

        int targetId = 2;

        try (Connection conn = DriverManager.getConnection(url, user, pwd);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, targetId);

            System.out.println("== 상품 가격 수정 : 타켓상품ID : " + targetId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println(rows + "개의 상품이 삭제 되었습니다.");
            } else {
                System.out.println("삭제할 상품을 찾지 못했습니다.");
            }
        } catch (SQLException e) {
            System.out.println("오류 : " + e.getMessage());
        }
    }
}
