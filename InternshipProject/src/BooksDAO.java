import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksDAO {

    public List<Books> findAll() {
        List<Books> list = new ArrayList<>();
        final String sql = "SELECT id, name, author, page, stock FROM books ORDER BY id";
        try (Connection cx = DBUtil.getConnection();
             PreparedStatement ps = cx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Books(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getInt("page"),
                        rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        }
        return list;
    }


    public boolean insertbooks(String name, String author, int page, int stock) {
        final String sql = "INSERT INTO books(`name`,`author`,`page`,`stock`) VALUES (?,?,?,?)";
        try (Connection cx = DBUtil.getConnection();
             PreparedStatement ps = cx.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, author);
            ps.setInt(3, page);
            ps.setInt(4, stock);

            int n = ps.executeUpdate();                     // <-- KRİTİK
            System.out.println("INSERT rows=" + n + ", DB=" + cx.getCatalog());
            return n == 1;
        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
            return false;
        }
    }

    public void updatestock(int bookId, int diff) {
        final String sql = "UPDATE books SET stock = stock + ? WHERE id = ?";
        try (Connection cx = DBUtil.getConnection();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, diff);
            ps.setInt(2, bookId);
            ps.executeUpdate();
        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        }

    }
    public void deletebooks(int bookId) throws SQLException {
        final String sql="DELETE from books where id= ?";
        try {
            Connection cx = DBUtil.getConnection();
            PreparedStatement ps = cx.prepareStatement(sql);
            ps.setInt(1, bookId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Books> SearchProduct(String q) throws SQLException {

        List<Books> list = new ArrayList<>();
        String sql="SELECT id,name, author,page,stock FROM books\n" +
                "WHERE name LIKE ? or author LIKE ?;";
        JTextField textField = null;
        String name1="%"+q+"%";
        try(Connection cx=DBUtil.getConnection();
        PreparedStatement ps=cx.prepareStatement(sql)) {
            ps.setString(1, name1);
            ps.setString(2, name1);
            ResultSet rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    list.add(new Books(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("author"),
                            rs.getInt("page"),
                            rs.getInt("stock")));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return  list;
        }
    };
    }


