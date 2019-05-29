package service;

import dao.ArticleDao;
import entity.Article;

public class ArticleService {
    private static ArticleDao dao = new ArticleDao();

    public static boolean addArticle(Article article){
        return dao.addArticle(article);
    }
    public static boolean addArticle(String userId, Article article){
    	return dao.addArticle(userId, article);
    }
    public static Article getArticleById(String id){
        return dao.getArticleById(id);
    }

    public static Article getArticleByTitle(String title){
        return dao.getArticleByTitle(title);
    }

    public static boolean deleteArticel(Article article){
        return dao.deleteArticle(article);
    }

    public static boolean deleteArticleById(String id){
        return dao.deleteArticleById(id);
    }

    public static boolean deleteArticelByTitle(String title){
        return dao.deleteArticleByTitle(title);
    }

    public static boolean alterOpening(Article article, String opening){
        return dao.alterOpening(article, opening);
    }

    public static boolean alterOpeningById(String id, String opening){
        return dao.alterOpeningById(id, opening);
    }

    public static boolean updateArticle(Article article){
        return  dao.updateArticle(article);
    }

    public static int articleAmount(){
        return dao.articleAmount();
    }
}