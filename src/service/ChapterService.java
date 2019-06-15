package service;

import java.util.ArrayList;

import dao.ChapterDao;
import entity.Article;
import entity.Chapter;
import entity.User;

public class ChapterService {
    private static ChapterDao dao = new ChapterDao();

    public static String getChapter(String id){
        return dao.getChapterById(id).getContent();
    }
    public static ArrayList<Chapter> getSubChapters(String pid){
        return dao.getChaptersByPid(pid);
    }
    public static Chapter getChapter(String id, String chapter){
        return dao.getChapterById(id);
    }

    public static boolean addChapter(String userId, String pid, Chapter chapter){
        return dao.addChapter(userId,pid, chapter);
    }

    public static int chapterAmount(){
        return dao.chapterAmount();
    }
    public static int subChapterAmount(String pid){
        return dao.subChapterAmount(pid);
    }
    public static ArrayList<Article> getChaptersArticlesByUserId(String userid) {
		return dao.getChaptersArticlesByUserId(userid);
	}
}
