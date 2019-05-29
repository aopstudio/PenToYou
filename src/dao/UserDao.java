package dao;

import entity.User;

import java.sql.*;

public class UserDao extends BaseDao {

    public User getUserById(String id){
        Object[] objects = new Object[]{id};
        ResultSet rs = super.executeSelect("select * from user where id=?", objects);
        return this.executeRS(rs);
    }

    public User getUserByName(String name){
        Object[] objects = new Object[]{name};
        ResultSet rs = super.executeSelect("select * from user where name=?", objects);
        return this.executeRS(rs);

    }

    private User executeRS(ResultSet rs){
        User targetUser = new User(null, null, null);
        try{
            if(rs.next()){
                targetUser.setId(rs.getString(1));
                targetUser.setName(rs.getString(2));
                targetUser.setInfo(rs.getString(3));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return targetUser;
    }

    public boolean addUser(User user){
        Object[] objects = new Object[]{user.getId(), user.getName(), user.getInfo()};
        int count = super.executeIUD("insert into user values(?,?,?)", objects);
        return count > 0 ? true : false;
    }

    public boolean deleteUser(User user){
        Object[] objects = new Object[]{user};
        int count = super.executeIUD("delete from user where id =?", objects);
        return count > 0 ? true : false;
    }

    public boolean deleteUserById(String id){
        Object[] objects = new Object[]{id};
        int count = super.executeIUD("delete from user where id =?", objects);
        return count > 0 ? true : false;
    }

    public boolean deleteUserByName(String name){
        Object[] objects = new Object[]{name};
        int count = super.executeIUD("delete from user where name =?", objects);
        return count > 0 ? true : false;
    }

    public int userChapterAmount(String id){
        Object[] objects = new Object[]{id};
        ResultSet rs = super.executeSelect("select sum(*) from user_chapters where userId=?", objects);

        return this.amount(rs);
    }

    public int userArticleAmount(String id){
        Object[] objects = new Object[]{id};
        ResultSet rs = super.executeSelect("select sum(*) from user_articles where userId=?", objects);

        return this.amount(rs);
    }

    private int amount(ResultSet rs){
        int amount = 0;
        try{
            if(rs.next()){
                amount = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return amount;
    }

}
