package service;

import dao.ChapterDao;
import entity.Article;
import entity.Chapter;
import entity.User;

public class ChapterService {
    private static ChapterDao dao = new ChapterDao();

    public static void addChapter(Article article, Chapter chapter){
        dao.addSubChapter(article, chapter);
    }

    public static void addChapter(Chapter chapter){
        dao.addChapter(chapter);
    }

    public static  void addChapter(Chapter parent, Chapter sub){
        dao.addSubChapter(parent, sub);
    }

    public static String getChapter(String id){
        return dao.getChapterById(id).getContent();
    }

    public static Chapter getChapter(String id, String chapter){
        return dao.getChapterById(id);
    }

    public static boolean addChapter(User user, Article article, Chapter chapter){
        return dao.addChapter(user, article, chapter);
    }

    public static boolean addChapter(User user, Chapter parentChapter, Chapter subChapter){
        return dao.addChapter(user, parentChapter, subChapter);
    }

    public static boolean addChapterToArticle(String userId, String articleId, Chapter chapter){
        return dao.addChapterToArticle(userId, articleId, chapter);
    }

    public static boolean addChapterToChapter(String userId, String chapterId, Chapter chapter){
        return dao.addChapterToChapter(userId, chapterId, chapter);
    }

}
