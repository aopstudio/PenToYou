package dao;

import entity.Article;
import entity.Chapter;
import entity.User;

import java.sql.*;

public class ChapterDao extends BaseDao {

    public Chapter getChapter(Chapter chapter) {
        Object[] objects = new Object[]{chapter.getId()};
        ResultSet rs = super.executeSelect("select * from chapter where id = ?", objects);
        return this.excuteRS(rs);
    }

    public Chapter getChapterById(String id){
        Object[] objects = new Object[]{id};
        ResultSet rs = super.executeSelect("select * from chapter where id = ?", objects);
        return this.excuteRS(rs);
    }

    private Chapter excuteRS(ResultSet rs){
        Chapter targetChapter = new Chapter();
        try {
            if(rs.next()) {
                targetChapter.setId(rs.getString(1));
                targetChapter.setTitle(rs.getString(2));
                targetChapter.setContent(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll(super.con, super.pst, rs);
        }
        return targetChapter;
    }

    public void addChapter(Chapter chapter) {
        Object[] objects = new Object[]{chapter.getId(), chapter.getTitle(), chapter.getContent()};
        super.executeIUD("insert into chapter values(?, ?, ?)", objects);
    }

    public void addSubChapter(Chapter parent, Chapter sub){
        Object[] objects = new Object[]{parent.getId(), sub.getId()};
        super.executeIUD("insert into chapter_sub values(?, ?)", objects);
    }

    public void addSubChapterToArticle(String articleId, Chapter sub){
        Object[] objects = new Object[]{articleId, sub.getId()};
        super.executeIUD("insert into chapter_sub values(?, ?)", objects);
    }

    public void addSubChapter(Article article, Chapter chapter){
        Object[] objects = new Object[]{article.getId(), chapter.getId()};
        super.executeIUD("insert into article_chapter values(?, ?)", objects);
    }

    public void addSubChapter(String articleId, Chapter chapter){
        Object[] objects = new Object[]{articleId, chapter.getId()};
        super.executeIUD("insert into article_chapter values(?, ?)", objects);
    }

    public boolean addChapter(User user, Article article, Chapter chapter){
        Object[] objects = new Object[]{user.getId(), chapter.getId()};
        int count = super.executeIUD("insert into user_chapters values(?,?)",objects);
        this.addChapter(chapter);
        this.addSubChapter(article, chapter);
        return count > 0 ? true : false ;
    }

    public boolean addChapterToArticle(String userId, String articleId, Chapter chapter){
        Object[] objects = new Object[]{userId, chapter.getId()};
        int count = super.executeIUD("insert into user_chapters values(?,?)",objects);
        this.addChapter(chapter);
        this.addSubChapter(articleId,chapter);
        return count > 0 ? true : false ;
    }

    public boolean addChapter(User user, Chapter parentChapter, Chapter subChapter){
        Object[] objects = new Object[]{user.getId(), subChapter.getId()};
        int count = super.executeIUD("insert into user_chapters values(?,?)",objects);
        this.addChapter(subChapter);
        this.addSubChapter(parentChapter, subChapter);
        return count > 0 ? true : false ;
    }

    public boolean addChapterToChapter(String userId, String chapterId, Chapter chapter){
        Object[] objects = new Object[]{userId, chapter.getId()};
        int count = super.executeIUD("insert into user_chapters values(?,?)",objects);
        this.addSubChapterToArticle(chapterId, chapter);
        this.addChapter(chapter);
        return count > 0 ? true : false ;
    }
}
