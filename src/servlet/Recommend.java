package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import entity.Article;
import entity.RecommendInfo;
import service.ArticleService;

/**
 * Servlet implementation class Recommend
 */
@WebServlet("/Recommend")
public class Recommend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recommend() {
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
		String code=request.getParameter("code");
		if(code.equals("100")) {	//请求码定为100，相当于密钥，防止有人恶意查询
			int amount=ArticleService.articleAmount();
			if(amount<4) {
				response.getWriter().print("文章数量太少，无法开启推荐");
			}
			else {
				Random rand = new Random(); 
				int num1=rand.nextInt(amount)+1;
				int num2=rand.nextInt(amount)+1;
				while(num2==num1) {
					num2=rand.nextInt(amount)+1;
				}
				int num3=rand.nextInt(amount)+1;
				while(num3==num2||num3==num1) {
					num3=rand.nextInt(amount)+1;
				}
				int num4=rand.nextInt(amount)+1;
				while(num4==num3||num4==num2||num4==num1) {
					num4=rand.nextInt(amount)+1;
				}
				Article article1=ArticleService.getArticleById(String.valueOf(num1));
				Article article2=ArticleService.getArticleById(String.valueOf(num2));
				Article article3=ArticleService.getArticleById(String.valueOf(num3));
				Article article4=ArticleService.getArticleById(String.valueOf(num4));
				
				ArrayList<Article> lst = new ArrayList<Article>();
				lst.add(article1);
				lst.add(article2);
				lst.add(article3);
				lst.add(article4);
				String json=JSON.toJSONString(lst);
				response.getWriter().print(json);
			}
		}
	}

}
