package member;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Servlet implementation class MemberController
 */
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
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
		RequestDispatcher dispatcher = null;
		MemberDao service = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		HttpSession session=request.getSession();
		
		String mid = request.getParameter("mid");
		String mpassword1 = request.getParameter("mpassword1");
		String mpassword2 = request.getParameter("mpassword2");
		String mphone = request.getParameter("mphone");
		String maddr1 = request.getParameter("maddr1");
		String maddr2 = request.getParameter("maddr2");
		String maddr3 = request.getParameter("maddr3");
		String mname = request.getParameter("mname");
		String mage = request.getParameter("mage");
		String mgender = request.getParameter("mgender");
		String mgrade = request.getParameter("mgrade");
		String metc = request.getParameter("metc");
		
		String BCpw  = BCrypt.hashpw(mpassword2, BCrypt.gensalt());
		
		if(sw.equals("I")) {
			System.out.println("===> I");
			vo.setMid(mid);
			vo.setMpassword1(mpassword1);
			vo.setMpassword2(BCpw);
			vo.setMphone(mphone);
			vo.setMaddr1(maddr1);
			vo.setMaddr2(maddr2);
			vo.setMaddr3(maddr3);
			vo.setMname(mname);
			vo.setMage(mage);
			vo.setMgender(mgender);
			service.insert(vo);
			response.sendRedirect(path+"/index.jsp");
			
		}else if(sw.equals("U")) {
			System.out.println("===> U");
			vo.setMphone(mphone);
			vo.setMaddr1(maddr1);
			vo.setMaddr2(maddr2);
			vo.setMaddr3(maddr3);
			vo.setMgrade(mgrade);
			vo.setMetc(metc);
			vo.setMid(mid);
			vo.setMpassword1(mpassword1);
			vo.setMpassword2(BCpw);

			service.update(vo);
			response.sendRedirect(path+"/MemberController?sw=S");
			
		}else if(sw.equals("F")) {
			System.out.println("===> F");
			response.sendRedirect(path+"/member/form.jsp");
			
		}else if(sw.equals("S")) {
			System.out.println("===> "+sw);
			String str1 = request.getParameter("str1");	
			String str2 = request.getParameter("str2");
			vo.setStr1(str1);
			vo.setStr2(str2);
			request.setAttribute("str1", str1);
			request.setAttribute("str2", str2);
			
			
			String xidxStr = request.getParameter("xidx");
			int  xidx = 0 ;
			if (xidxStr == null) {
				xidx = 0;
			}else{
				xidx = Integer.parseInt(xidxStr);
			}	
			int pageListSize=vo.getPageListSize();
			vo.setPageListSize(pageListSize);
			vo.setXidx(xidx);
			request.setAttribute("li", service.m_select(vo));
			
			int tc=service.tc(vo);
			request.setAttribute("tc", tc);
			
			int pageSize=vo.getPageSize();
			vo.setPageSize(pageSize);
			
			request.setAttribute("xidx", xidx);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageListSize", pageListSize);

			dispatcher=request.getRequestDispatcher("/member/list.jsp");
			dispatcher.forward(request, response);
			
		}else if(sw.equals("E")) {
			System.out.println("===> E");
			request.setAttribute("m", service.edit(mid));
			dispatcher = request.getRequestDispatcher("/member/edit.jsp");
			dispatcher.forward(request, response);
			
		}else if(sw.equals("D")) {
			System.out.println("===> D");
			service.delete(mid);
			response.sendRedirect(path+"/MemberController?sw=S");
			
		}else if(sw.equals("loginF")) {
			response.sendRedirect(path+"/member/login_form.jsp");
			
		}else if(sw.equals("IN")) {
			
			vo.setMid(mid);
			vo.setMpassword1(mpassword1);
			
			String m= service.login(vo);
			if(!m.equals("")) {
				System.out.println("로그인 성공!! :"+m);
				session.setAttribute("mid", m);
				response.sendRedirect(path+"/index.jsp");
			}else {
				System.out.println("로그인 실패!!");
				response.sendRedirect(path+"/member/login_form.jsp");
				
			}
			PrintWriter out=response.getWriter();
			out.print(m);
			
		}else if(sw.equals("OUT")) {
			session.invalidate();
			response.sendRedirect(path+"/index.jsp");
		}else if(sw.equals("ck")) {
			MemberVO m =service.edit(mid);
			int str ; 
			if (m.getMid()==null) {
				str=0;  // 사용가능 id
			}else {
				str=1;  // 중복 id
			}		

			PrintWriter out = response.getWriter();
			out.println(str);		
		
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
