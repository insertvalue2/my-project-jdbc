package ch03;

import java.sql.*;

public class ProductUpdate {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/shop2?serverTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER");
        String pwd = System.getenv("DB_PASSWORD");

        // UPDATE - name, 가격을 수정하는 쿼리를 진행할 예정
        // 텍스트블록 문법 JDK 13이후 사용 가능
        String sql = """ 
                  update product2
                  set price = ?
                  where name = ?
                """;

        int newPrice = 500000;
        String targetName = "애플 에어팟 프로";

        try (Connection conn = DriverManager.getConnection(url, user, pwd);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newPrice);
            pstmt.setString(2, targetName);
            System.out.println("== 상품 가격 수정 : 타켓상품 : " + targetName);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println(rows + "개의 상품 가격이 수정되었습니다.");
            } else {
                System.out.println("수정할 상품을 찾지 못했습니다.");
            }
        } catch (SQLException e) {
            System.out.println("오류 : " + e.getMessage());
        }
    }
}
