package kadai.review05;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review05 {

    public static void main(String[] args) {
     // 3. データベース接続と結果取得のための変数宣言
        Connection con =null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 1. ドライバのクラスをJava上で読み込む
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");


        // 2. DBと接続する
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                "root", "2525sql");

        // 4. DBとやりとりする窓口（Statementオブジェクト）の作成
            String sql= "select * from person where id = ?";
            pstmt = con.prepareStatement(sql);

        // 5, 6. Select文の実行と結果を格納／代入
            System.out.println("検索するIDを数字で入力してください >");
            int num1 = keyInnum();

            pstmt.setInt(1,num1);
            rs = pstmt.executeQuery();

        // 7. 結果を表示する
            while(rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");

                System.out.println(name);
                System.out.println(age);
            }



        // 8. 接続を閉じる
        } catch (ClassNotFoundException e) {
            // TODO 自動生成された catch ブロック
            System.out.println("DB接続できませんでした");
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs != null) {
                try {
                    rs.close();
                }catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(pstmt != null) {
                try {
                    pstmt.close();
                }catch (SQLException e) {
                    e.printStackTrace();

                }
            }

        }
            if (con != null) {
                try {
                    con.close();
                }catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }

    private static String keyin() {
        String line = null;
        try {
            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            line = key.readLine();
        }catch(IOException e) {

        }
        return line;
    }
    private static int keyInnum() {
        // TODO 自動生成されたメソッド・スタブ
        int result =0;
        try {
        result = Integer.parseInt(keyin());
        }catch(NumberFormatException  e) {
            e.printStackTrace();
        }
        return result;
    }



}


