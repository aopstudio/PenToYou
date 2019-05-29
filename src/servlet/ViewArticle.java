package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import entity.Article;
import entity.Chapter;
import entity.ChapterInfo;
import service.ArticleService;
import service.ChapterService;
import tool.StringFunc;

/**
 * Servlet implementation class ViewArticle
 */
@WebServlet("/ViewArticle")
public class ViewArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewArticle() {
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
		String pid=request.getParameter("id");
		Article article=ArticleService.getArticleById(pid);
		ArrayList<Chapter> chapters=ChapterService.getSubChapters(pid);
		ChapterInfo root=new ChapterInfo(article.getId(),null,article.getOpening(),article.getTitle());
		Stack<ChapterInfo> stack=new Stack<ChapterInfo>();
		stack.push(root);
		int level=0;	//子章节的层级
		for(Chapter chapter:chapters) {
			String id=chapter.getId();
			String fid=id.substring(0,id.lastIndexOf("-")-1);
			ChapterInfo chapterInfo=new ChapterInfo(chapter.getId(),fid,chapter.getContent(),chapter.getTitle());
			int levelNum=StringFunc.count(id, "-");
			if(levelNum>level) {
				level=levelNum;
				if(stack.peek().getChildren()==null) {
					stack.peek().haveChildren();
				}
				stack.peek().getChildren().add(chapterInfo);
				stack.push(chapterInfo);
			}
			else if(levelNum==level) {
				stack.pop();
				if(stack.peek().getChildren()==null) {
					stack.peek().haveChildren();
				}
				stack.peek().getChildren().add(chapterInfo);
				stack.push(chapterInfo);
			}
			else {
				while(level!=levelNum) {
					ChapterInfo fChapterInfo=stack.pop();
					level=StringFunc.count(fChapterInfo.getId(), "-");
				}
				if(stack.peek().getChildren()==null) {
					stack.peek().haveChildren();
				}
				stack.peek().getChildren().add(chapterInfo);
				stack.push(chapterInfo);
			}
		}
		ArrayList<ChapterInfo> rootList=new ArrayList<ChapterInfo>();
		rootList.add(root);
		String jsonString=JSON.toJSONString(rootList);
		response.getWriter().print(jsonString);
	}

}
