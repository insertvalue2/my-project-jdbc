package ch02;

import java.math.BigDecimal;
import java.sql.*;

public class Step1Statement {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/shop?serverTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER"); // 환경 변수에 저장 되어 있음
        String password = System.getenv("DB_PASSWORD"); // 환경 변수에 저장 되어 있음

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // 1. Connection 객체 필요 - 생성 - 세션 생성(논리적으로 연결된 상태를 의미함 )
            // 2. Statement 객체 필요 ( 문자열을 쿼리 객체로 변경 해 줌)
            // 3. ResultSet 객체 필요 (쿼리가 실행 되면 결과집합을 담고 있는 녀석)
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM product");
            System.out.println("==== 상품 목록 출력 ====");
            // rs.next() --> 다음 행이 존재 하는가? --> true , false
            while (rs.next()) {
                int id = rs.getInt("id");
                int categoryId = rs.getInt("category_id");
                String name = rs.getString("name");

                // 2. 도전 문제 - 추가 컬럼들 가져오기
                // decimal(10,2)는 오차 없는 계산을 위해 BigDecimal을 권장합니다.
                BigDecimal price = rs.getBigDecimal("price");
                int stock = rs.getInt("stock");
                String description = rs.getString("description");
                // datetime은 Timestamp 객체로 가져오면 날짜와 시간을 모두 다루기 편합니다.
                Timestamp createdAt = rs.getTimestamp("created_at");

                // 3. 전체 출력
                System.out.println("--------------------------------------------------");
                System.out.println("📦 상품 ID : " + id);
                System.out.println("📂 카테고리 : " + categoryId);
                System.out.println("🏷️ 상품명   : " + name);
                System.out.println("💰 가격     : " + price + "원");
                System.out.println("📊 재고     : " + stock + "개");
                System.out.println("📝 상세설명 : " + (description != null ? description : "없음"));
                System.out.println("⏰ 등록일   : " + createdAt);
            }
        } catch (SQLException e) {
            System.out.println("오류 발생 : " + e.getMessage());
            System.out.println("SQLState : " + e.getSQLState());
        }

    } // end of main

} // end of class
