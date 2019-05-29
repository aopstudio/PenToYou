package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Article;
import service.ArticleService;;

import entity.Article;
import service.ArticleService;
import service.UserService;
import tool.Key;

/**
 * Servlet implementation class NewOpening
 */
@WebServlet("/NewArticle")
public class NewArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String userid=Key.md5s(request.getParameter("userid"));
		String title=request.getParameter("title");
		String description=request.getParameter("description");
		String opening=request.getParameter("opening");
		String id=String.valueOf(ArticleService.articleAmount()+1);
		String author=UserService.getUserById(userid).getName();
		Article article=new Article(author,id,title,description,null,opening,null);
		ArticleService.addArticle(userid,article);
		response.getWriter().print("新建成功");
	}

}
