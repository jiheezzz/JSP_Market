package Cart;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.ProductVO;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Test.TestDao;
import Test.TestServiceImpl;
import Test.TestVO;

/**
 * Servlet implementation class CartController
 */
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
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
		String mid = (String)request.getSession().getAttribute("mid");
		String partner_user_id = (String)request.getSession().getAttribute("partner_user_id");
		String partner_order_id = (String)request.getSession().getAttribute("partner_order_id");
		String product_id = request.getParameter("product_id");
		String amount = request.getParameter("amount");
		String cart_id = request.getParameter("cart_id");
		String orderG = (String)request.getSession().getAttribute("partner_order_id");
		
		CartDao service = new CartServiceImpl();
		CartVO  cvo = new CartVO(); 
		OrderVO  ovo = new OrderVO();
		
		if(sw.equals("F")) {
			response.sendRedirect(path+"/cart/form.jsp");
			
		}else if(sw.equals("I")) {
			System.out.println("===>"+sw);
				cvo.setMid(mid);
				cvo.setProduct_id(product_id);
				cvo.setAmount(amount);
				service.insert(cvo);
				response.sendRedirect(path+"/ProductController?sw=S");
		}else if(sw.equals("S")) {
			System.out.println("===>"+sw);
			cvo.setMid(mid);
			request.setAttribute("li",service.cartSelect(cvo));
			RequestDispatcher dispatcher=request.getRequestDispatcher("/cart/list.jsp");
			dispatcher.forward(request, response);
		}else if(sw.equals("U")) {
			System.out.println("===>"+sw);
			String[] uamount = request.getParameterValues("amount");
			String[] ucart_id = request.getParameterValues("cart_id");
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			HashMap<String,String> hash = null;
			for(int i=0;i<ucart_id.length;i++) {
					//System.out.println(name[i]+":"+amount[i]+"개");
				cvo.setCart_id(ucart_id[i]);
				cvo.setAmount(uamount[i]);
				service.update(cvo);
				hash = new HashMap<String, String>();
				hash.put("cart_id", ucart_id[i]);
				hash.put("amount", uamount[i]);
				list.add(hash);
			
			}
			request.setAttribute("li",list);
			RequestDispatcher dispatcher= request.getRequestDispatcher("/CartController?sw=S");
			dispatcher.forward(request, response);
			
		}else if(sw.equals("D")) {
			System.out.println("===>"+sw);
			cvo.setMid(mid);
			service.delete(cvo);
			response.sendRedirect(path + "/CartController?sw=S");
		}else if(sw.equals("L")) {
		
			CartVO vo = new CartVO();
			System.out.println("vo:"+vo);
			vo.setMid(mid);
	        List<HashMap<String, Object>>  li =  service.cartSelect(vo);
	        System.out.println("===> (2)" + li.size());
	        for (HashMap<String, Object> member : li) {
				ovo = new OrderVO();
				ovo.setOrderG(partner_order_id);
				ovo.setCart_id(String.valueOf(member.get("cart_id")));
				ovo.setMid(partner_user_id);
				ovo.setProduct_id(String.valueOf(member.get("pid")));
				ovo.setAmount(String.valueOf(member.get("amount")));
				System.out.println("ovo: "+ovo);
				service.orderInsert(ovo);
	        }
	        
	        vo.setMid(mid);
	        service.delete(vo);
	        
	        ovo = new OrderVO();
	        ovo.setMid(mid);
	        request.setAttribute("li", service.orderList(ovo));	
	        
			RequestDispatcher	dispatcher
			    =request.getRequestDispatcher("/cart/order_list.jsp");
			dispatcher.forward(request, response);	
	        
		}else if(sw.equals("E")) {
			orderG = request.getParameter("orderG");
			ovo.setOrderG(orderG);
			request.setAttribute("li", service.editList(ovo));
			RequestDispatcher	dispatcher
			    =request.getRequestDispatcher("/cart/edit.jsp");
			dispatcher.forward(request, response);	
			
		}else if(sw.equals("morderL")) {
		ovo = new OrderVO();
        request.setAttribute("li", service.manager_orderList(ovo));	
        
		RequestDispatcher	dispatcher
		    =request.getRequestDispatcher("/cart/manager_order_list.jsp");
		dispatcher.forward(request, response);
		
		}else if(sw.equals("OD")) {
			String G = (String)request.getParameter("orderG");
			ovo.setOrderG(G);
			service.order_delete(ovo);
			response.sendRedirect(path + "/CartController?sw=morderL");
			
		}else if(sw.equals("orderL")) {
			ovo = new OrderVO();
			ovo.setMid(mid);
	        request.setAttribute("li", service.orderList(ovo));	
	        
			RequestDispatcher	dispatcher
			    =request.getRequestDispatcher("/cart/order_list.jsp");
			dispatcher.forward(request, response);
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
