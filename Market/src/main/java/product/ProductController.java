package product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
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
 * Servlet implementation class ProductController
 */
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
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
	    ProductVO vo = new ProductVO();
	    ProductDao service = new ProductServiceImpl();
	    String product_id = request.getParameter("product_id");
	    String product_name = request.getParameter("product_name");
	    String product_etc = request.getParameter("product_etc");
	    String product_price = request.getParameter("product_price");
	    String product_img = request.getParameter("product_img");

	    String realFolder = getServletContext().getRealPath("/product/img/"); 
	    System.out.println("realFolder: " + realFolder);
	    String contentType = request.getContentType();
	    if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
	        Part part = request.getPart("product_img");
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
	                product_img = k + ran + extension;
	                part.write(realFolder + product_img);
	                vo.setProduct_img(product_img);
	            } else {
	                vo.setProduct_img("space.PNG");
	            }} 
	        vo.setProduct_name(product_name);
	        vo.setProduct_price(Integer.parseInt(product_price));
	        vo.setProduct_etc(product_etc);
	        if (sw.equals("I")) {
	            System.out.println("===> I");
	            vo.setProduct_id(product_id);
	            service.insert(vo);
	            response.sendRedirect(path + "/ProductController?sw=S");
	        } else if (sw.equals("U")) {
	            System.out.println("===> U");
	            ProductVO m = service.edit(product_id);
	            vo.setProduct_id(product_id);
	            if (!m.getProduct_img().equals("space.PNG") && part != null) {
	                String delFile = realFolder + m.getProduct_img();
	                File f = new File(delFile);
	                f.delete();
	            }
	            if (part == null || part.getSubmittedFileName().isEmpty()) {
	                service.update_edit(vo);
	            } else {
	                service.update(vo);
	            }
	            response.sendRedirect(path + "/ProductController?sw=S");
	        }
	    } else {
	        vo.setProduct_img(product_img);

	        if (sw.equals("F")) {
	            System.out.println("===> F");
	            response.sendRedirect(path + "/product/form.jsp");
	        } else if (sw.equals("D")) {
	            System.out.println("===> D");
	            ProductVO m = service.edit(product_id);
	            String file = m.getProduct_img();
	            String delFile = realFolder + file;
	            File f = new File(delFile);
	            if (f.exists() && !m.getProduct_img().equals("space.PNG")) {
	                f.delete();
	            }
	            vo.setProduct_id(product_id);
	            service.delete(product_id);
	            response.sendRedirect(path + "/ProductController?sw=S");
	        } else if (sw.equals("S")) {
	            System.out.println("===> S");
	            List<ProductVO> li = service.select(vo);
	            request.setAttribute("li", li);
	            dispatcher = request.getRequestDispatcher("/product/list.jsp");
	            dispatcher.forward(request, response);
	        } else if (sw.equals("E")) {
	            System.out.println("===> E");
	            vo.setProduct_id(product_id);
	            ProductVO m = service.edit(product_id);
	            request.setAttribute("m", m);
	            dispatcher = request.getRequestDispatcher("/product/edit.jsp");
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
