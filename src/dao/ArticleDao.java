package dao;

import entity.Article;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleDao extends BaseDao {
    public boolean addArticle(Article article){
        Object[] objects = new Object[]{article.getAuthor(),article.getTitle(), article.getId(),article.getDescription(), article.getPicurl(),article.getOpening(), article.getCategory()};
        int count = super.executeIUD("insert into article values(?,?,?,?,?,?,?)",objects);
        return count > 0 ? true : false;
    }

    public Article getArticleById(String id){
        Object[] objects = new Object[]{id};
        ResultSet rs = super.executeSelect("select * from article where id=?", objects);
        return this.executeRS(rs);
    }

    public Article getArticleByTitle(String title){
        Object[] objects = new Object[]{title};
        ResultSet rs = super.executeSelect("select * from article where title=?", objects);
        return this.executeRS(rs);
    }

    private Article executeRS(ResultSet rs){
        Article targetArticle = new Article();
        try{
            if(rs.next()){
            	targetArticle.setAuthor(rs.getString(1));
                targetArticle.setTitle(rs.getString(2));
                targetArticle.setId(rs.getString(3));
                targetArticle.setDescription(rs.getString(4));
                targetArticle.setPicurl(rs.getString(5));
                targetArticle.setOpening(rs.getString(6));
                targetArticle.setCategory(rs.getString(7));
            }
        } catch (SQLException e){
            e.printStackTrace();;
        }
        return targetArticle;
    }

    public boolean deleteArticle(Article article){
        Object[] objects = new Object[]{article.getId()};
        int count = super.executeIUD("delete from article where id=?", objects);
        return count > 0 ? true : false;
    }

    public boolean deleteArticleById(String id){
        Object[] objects = new Object[]{id};
        int count = super.executeIUD("delete from article where id=?", objects);
        return count > 0 ? true : false;
    }

    public boolean deleteArticleByTitle(String title){
        Object[] objects = new Object[]{title};
        int count = super.executeIUD("delete from article where title=?", objects);
        return count > 0 ? true : false;
    }

    public boolean alterOpeningById(String id, String opening){
        Object[] objects = new Object[]{opening, id};
        int count = super.executeIUD("update article set opening=? where id=?", objects);
        return count > 0 ? true : false;
    }

    public boolean alterOpening(Article article, String opening){
        Object[] objects = new Object[]{opening, article.getId()};
        int count = super.executeIUD("update article set opening=? where id=?", objects);
        return count > 0 ? true : false;
    }

    public boolean updateArticle(Article article){
        Object[] objects = new Object[]{article.getAuthor(),article.getTitle(), article.getId(), article.getDescription(), article.getPicurl(), article.getOpening(), article.getCategory(), article.getId()};
        int count = super.executeIUD("update article set author=?,title=?,id=?,description=?,picurl=?,opening=?,category=? where id=?", objects);
        return count > 0 ? true : false;
    }

    public boolean addArticle(User user, Article article){
        Object[] objects = new Object[]{user.getId(), article.getId()};
        int count = super.executeIUD("insert into user_articles values(?,?)",objects);
        this.addArticle(article);
        return count > 0 ? true : false ;
    }

    public boolean addArticle(String userId, Article article){
        Object[] objects = new Object[]{userId, article.getId()};
        int count = super.executeIUD("insert into user_articles values(?,?)",objects);
        this.addArticle(article);
        return count > 0 ? true : false ;
    }

    public int articleAmount(){
        ResultSet rs = super.executeSelect("select count(*) from article", null);
        int amount = 0;
        try{
            if(rs.next()){
                amount = rs.getInt(1);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return amount;
    }
}
