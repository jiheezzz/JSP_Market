package Test;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Servlet implementation class TestController
 */
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String sw = request.getParameter("sw");
		String path = request.getContextPath();
		
		if(sw.equals("F")) {
			response.sendRedirect(path+"/test/form.jsp");
			
		}else if(sw.equals("I")) {
			System.out.println("===>"+sw);
			String[] name = request.getParameterValues("name");
			String[] amount = request.getParameterValues("amount");
			TestVO vo = null;
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			HashMap<String,String> hash = null;
			for(int i=0;i<name.length;i++) {
					//System.out.println(name[i]+":"+amount[i]+"개");
				 vo = new TestVO();
				vo.setName(name[i]);
				vo.setAmount(amount[i]);
				TestDao service = new TestServiceImpl();
				service.insert(vo);
				hash = new HashMap<String, String>();
				hash.put("name", name[i]);
				hash.put("amount", amount[i]);
				list.add(hash);
			
			}
			request.setAttribute("li",list);
			RequestDispatcher dispatcher=request.getRequestDispatcher("/test/list.jsp");
			dispatcher.forward(request, response);
			
		}else if(sw.equals("kakao")){
			response.sendRedirect(path+"/test/pay.jsp");
		}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
