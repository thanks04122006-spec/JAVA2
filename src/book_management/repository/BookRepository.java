package book_management.repository;

import book_management.domain.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookRepository {
    ArrayList<Book> bookList = new ArrayList<Book>();

    public ArrayList<Book> select(String searchWord, int selectedIndex){
        Connection con = JDBCConnector.getConnection();
        String[] columName = {"name", "publish_name", "author"};
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select isbn, name, author, price, inventory, publish_name, category_name from book b, category c, publish p " +
                "where b.category_id = c.category_id and b.publish_id = p.publish_id " +
                "and " + columName[selectedIndex] + " like ?";
        System.out.println(sql);
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + searchWord + "%");
            rs = pstmt.executeQuery();
            Book book = null;

            while (rs.next()){
                book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getInt("price"));
                book.setInventory(rs.getInt("inventory"));
                book.setPublish_name(rs.getString("publish_name"));
                book.setCategory_name(rs.getString("category_name"));
                bookList.add(book);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                pstmt.close();
                rs.close();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return bookList;
    }
}
