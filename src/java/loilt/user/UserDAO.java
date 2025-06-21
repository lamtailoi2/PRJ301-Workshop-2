package loilt.user;

import java.io.Serializable;
import java.sql.Connection;
import loilt.util.DBHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Serializable {

    public boolean checkLogin(String userId, String password) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT password FROM tbl_User WHERE userId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    return storedPassword.equals(password);
                }
            }
            return false;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public UserDTO getUserById(String userId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        UserDTO user = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT userId, password, lastName, firstName, role FROM tbl_User WHERE userId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("userId");
                    String password = rs.getString("password");
                    String lastName = rs.getString("lastName");
                    String firstName = rs.getString("firstName");
                    int role = rs.getInt("role");
                    user = new UserDTO(id, password, lastName, firstName, role);
                }
            }
            return user;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public List<UserDTO> getUsersByLastName(String lastName) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        UserDTO user = null;
        List<UserDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT userId, password, lastName, firstName, role FROM tbl_User WHERE lastName LIKE ?";
                stm = con.prepareStatement(sql);
                String searchValue = "%" + lastName + "%";
                stm.setString(1, searchValue);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("userId");
                    String password = rs.getString("password");
                    String _lastName = rs.getString("lastName");
                    String firstName = rs.getString("firstName");
                    int role = rs.getInt("role");
                    user = new UserDTO(id, password, _lastName, firstName, role);
                    list.add(user);
                }
            }
            return list;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteByUserId(String userId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM tbl_User WHERE userId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                int row = stm.executeUpdate();
                return row > 0;
            }
            return false;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean updateUser(UserDTO user) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tbl_User "
                        + "SET password = ?, firstName = ?, lastName = ?, role = ? "
                        + "WHERE userId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, user.getPassword());
                stm.setString(2, user.getFirstName());
                stm.setString(3, user.getLastName());
                stm.setInt(4, user.getRole());
                stm.setString(5, user.getUserId());

                int row = stm.executeUpdate();
                return row > 0;
            }
            return false;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean createUser(UserDTO user) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tbl_User(userId, password, firstname, lastname, role) "
                        + "VALUES (?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, user.getUserId());
                stm.setString(2, user.getPassword());
                stm.setString(3, user.getFirstName());
                stm.setString(4, user.getLastName());
                stm.setInt(5, user.getRole());

                int row = stm.executeUpdate();
                return row > 0;
            }
            return false;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

}
