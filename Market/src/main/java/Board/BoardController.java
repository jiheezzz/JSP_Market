package Board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import member.BCrypt;
import member.MemberDao;
import member.MemberServiceImpl;
import member.MemberVO;
import product.ProductVO;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@MultipartConfig(
		location="/", 
		fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5, 
        maxRequestSize=1024*1024*5*5
)
/**
 * Servlet implementation class BoardController
 */
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
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
		BoardDao service = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		
		String idx = request.getParameter("idx");;
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String img = request.getParameter("img");
		String etc = request.getParameter("etc");
		String cnt = request.getParameter("cnt");
		String day = request.getParameter("day");
		
		String realFolder = getServletContext().getRealPath("/board/img/"); 
	    System.out.println("realFolder: " + realFolder);

	    String contentType = request.getContentType();
	    if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
	        Part part = request.getPart("img");
	        if (part != null) {
	            String nameStr = part.getSubmittedFileName();
	            if (nameStr != null && !nameStr.isEmpty()) {
	                Date now = new Date();
	                SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
	                String k = sdf.format(now);
	                int ran = (int) (Math.random() * 1000) + 1;
	                int lastIndex = nameStr.lastIndexOf('.');
	                String extension = nameStr.substring(lastIndex);
	                System.out.println("Extension: " + extension);
	                img = k + ran + extension;
	                part.write(realFolder + img);
	                vo.setImg(img);
	            } else {
	                vo.setImg("space.PNG");
	            }
	            vo.setName(name);
	            vo.setTitle(title);
	            vo.setEtc(etc);
	        if(sw.equals("I")) {
				System.out.println("===> I");
				service.insert(vo);
				response.sendRedirect(path+"/BoardController?sw=S");
				
	        } else if (sw.equals("U")) {
	            System.out.println("===> U");
	            System.out.println("===>nameStr: "+nameStr);
	            BoardVO m = service.edit(idx);
	            vo.setIdx(idx);
	            vo.setDay(day);
	            if (!m.getImg().equals("space.PNG") && !nameStr.equals("")) {
	                String delFile = realFolder + m.getImg();
	                File f = new File(delFile);
	                f.delete();
	            }
	            if (nameStr.equals("") || nameStr.isEmpty()) {
	                service.update_edit(vo);
	            } else {
	                service.update(vo);
	            }
	            response.sendRedirect(path + "/BoardController?sw=S");
	        }
	        }
	    }
	    else {
	    	 vo.setImg(img);
	    if(sw.equals("F")) {
			System.out.println("===> F");
			response.sendRedirect(path+"/board/form.jsp");
			
		}else if (sw.equals("D")) {
            System.out.println("===> D");
            BoardVO m = service.edit(idx);
            String file = m.getImg();
            String delFile = realFolder + file;
            File f = new File(delFile);
            if (f.exists() && !m.getImg().equals("space.PNG")) {
                f.delete();
            }
            vo.setIdx(idx);
            service.delete(idx);
            response.sendRedirect(path + "/BoardController?sw=S");
            
        }else if(sw.equals("S")) {
			System.out.println("===> S");
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
			request.setAttribute("li", service.select(vo));
			
			int tc=service.tc(vo);
			request.setAttribute("tc", tc);
			int pageSize=vo.getPageSize();
			vo.setPageSize(pageSize);
			
			request.setAttribute("xidx", xidx);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageListSize", pageListSize);

			dispatcher=request.getRequestDispatcher("/board/list.jsp");
			dispatcher.forward(request, response);
		}else if (sw.equals("E")) {
            System.out.println("===> E");
            vo.setIdx(idx);
            BoardVO m = service.edit(idx);
            request.setAttribute("m", m);
            dispatcher = request.getRequestDispatcher("/board/edit.jsp");
            dispatcher.forward(request, response);
            service.cnt(idx);
        }else if(sw.equals("MS")) {
			System.out.println("===> S");
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
			request.setAttribute("li", service.select(vo));
			
			int tc=service.tc(vo);
			request.setAttribute("tc", tc);
			
			int pageSize=vo.getPageSize();
			vo.setPageSize(pageSize);
			
			request.setAttribute("xidx", xidx);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageListSize", pageListSize);

			dispatcher=request.getRequestDispatcher("/board/manager_list.jsp");
			dispatcher.forward(request, response);
		}
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
