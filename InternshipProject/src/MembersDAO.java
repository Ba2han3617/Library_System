import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MembersDAO {

    public static List<Members> findAll(){
        List<Members> list=new ArrayList<>();
        final String sql="SELECT id,name,surname,email,username,password from members ORDER BY id";
        try{
            Connection cx=DBUtil.getConnection();
            PreparedStatement ps=cx.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                list.add(new Members(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("papssword")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public boolean addUser(String name,String surname,String email,String username,String password) {
        final String sql = "INSERT INTO members (name, surname, email, username, `password`)\n" +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection cx = DBUtil.getConnection();
             PreparedStatement ps = cx.prepareStatement(sql)) {

            ps.setString(1,name);
            ps.setString(2, surname);
            ps.setString(3, email);
            ps.setString(4, username);
            ps.setString(5, password);

            int n = ps.executeUpdate();
            System.out.println("INSERT rows=" + n + ", DB=" + cx.getCatalog());
            return n == 1;
        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
            return false;
        }
    }
    public boolean SıgnIn(String username, String password) {
        final String sql =
                "SELECT EXISTS(SELECT 1 FROM members WHERE username = ? AND password = ?) AS ok";

        try (Connection cx = DBUtil.getConnection();
             PreparedStatement ps = cx.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getBoolean("ok");
                }
                return false;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("signIn query failed", e);
        }
    }

}
