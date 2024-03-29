package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Chapter;
import service.ChapterService;
import tool.Key;

/**
 * Servlet implementation class NewContinue
 */
@WebServlet("/NewChapter")
public class NewChapter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewChapter() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String userid=Key.md5s(request.getParameter("userid"));
		String title=request.getParameter("title");
		String pid=request.getParameter("pid");
		String content=request.getParameter("content");
		int subChapterAmount=ChapterService.subChapterAmount(pid);
		int subid=subChapterAmount+1;
		String id=pid+"-"+subid;
		Chapter chapter=new Chapter(id, content, title);
		ChapterService.addChapter(userid, pid, chapter);
		response.getWriter().print(request.getParameter("title"));
		
	}

}
