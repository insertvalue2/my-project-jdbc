package ch03;

import java.sql.*;

public class ProductSelectByPrice {

    public static void main(String[] args) {

        // 상품이 1만원 ~ 10만원 사이의 상품을 출력 하시오
        // shop DB 사용 product 테이블 사용
        // 가격 오름차순 정렬

        String url = "jdbc:mysql://localhost:3306/shop?serverTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER");
        String pwd = System.getenv("DB_PASSWORD");
        // insert 쿼리 구문을 미리 Strin으로 만들어 두자
        // 텍스트블록 문법 JDK 13이후 사용 가능

        int minPrice = 10000;
        int maxPrice = 100000;

        String sql = """ 
                   SELECT * FROM product WHERE price BETWEEN ? AND ? ORDER BY price ASC
                """;
        try (Connection conn = DriverManager.getConnection(url, user, pwd);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, minPrice);
            pstmt.setInt(2, maxPrice);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("== 전체 상품 목록 조회");
            while (rs.next()) {
                String output = """
                        ID: %3d | %-20s | %,7d원 | 재고 : %3d개
                        """.formatted(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("stock")
                );
                // 참고로 텍스트 블록 문법은 자동으로 \n이 들어가 있다.
                System.out.print(output);
            }
        } catch (SQLException e) {
            System.out.println("오류 : " + e.getMessage());
        }
    }
}
