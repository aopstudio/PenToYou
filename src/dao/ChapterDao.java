package dao;

import entity.Article;
import entity.Chapter;
import entity.User;

import java.sql.*;
import java.util.ArrayList;

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
    
    public ArrayList<Chapter> getChaptersByPid(String pid){
    	String state=pid+"%";
        Object[] objects = new Object[]{state};
        ResultSet rs = super.executeSelect("select * from chapter where id like ?", objects);
        return this.excuteRSS(rs);
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
    private ArrayList<Chapter> excuteRSS(ResultSet rs){
    	ArrayList<Chapter> chapters=new ArrayList<Chapter>();
        try {
            while(rs.next()) {
            	Chapter targetChapter = new Chapter();
                targetChapter.setId(rs.getString(1));
                targetChapter.setTitle(rs.getString(2));
                targetChapter.setContent(rs.getString(3));
                chapters.add(targetChapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll(super.con, super.pst, rs);
        }
        return chapters;
    }

    private void addChapter(Chapter chapter) {
        Object[] objects = new Object[]{chapter.getId(), chapter.getTitle(), chapter.getContent()};
        super.executeIUD("insert into chapter values(?, ?, ?)", objects);
    }

    public boolean addChapter(String userId, String pid, Chapter subChapter){
        Object[] objects = new Object[]{userId, subChapter.getId()};
        int count = super.executeIUD("insert into user_chapters values(?,?)",objects);
        this.addChapter(subChapter);
        this.addSubChapter(pid, subChapter);
        return count > 0 ? true : false ;
    }
    
    public boolean addSubChapter(String pid, Chapter sub){
        Object[] objects = new Object[]{pid, sub.getId()};
        int count = super.executeIUD("insert into subchapter values(?, ?)", objects);
        return count > 0 ? true : false ;
    }
    
    public int chapterAmount(){
        ResultSet rs = super.executeSelect("select count(*) from chapter", null);
        int amount = 0;
        try{
            if(rs.next()){
                amount = rs.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return amount;
    }
    public int subChapterAmount(String pid){
    	Object[] objects = new Object[]{pid};
        ResultSet rs = super.executeSelect("select count(*) from subchapter where pid = ?", objects);
        int amount = 0;
        try{
            if(rs.next()){
                amount = rs.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return amount;
    }
}
