package ch02;

import java.sql.*;

public class Step2PreparedStatement {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/shop?serverTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER");
        String pwd = System.getenv("DB_PASSWORD");

        int maxPrice = 50000; // 5만원 이하 상품 검색 예정
        try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM product where price <=  ? ");
            pstmt.setInt(1, maxPrice);
            // 쿼리 실행
            // SELECT - executeQuery()   - ResetSet
            // INSERT, UPDATE, DELETE - executeUpdate()  - int
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("%-20s %,d원 (재고: %d개)%n",
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getInt("stock"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } // end of main
} // end of class
