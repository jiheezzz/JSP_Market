package kakao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Cart.CartServiceImpl;
import Cart.CartVO;
import Cart.OrderVO;

/**
 * Servlet implementation class ApproveServlet
 */
public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_KEY = "708357e20ee115b73116b2113d72618f"; // Admin 키
	private static final String CID = "TC0ONETIME"; // 테스트 CID
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String path = request.getContextPath();
		System.out.println("===> ApproveServlet 넘어옴 !!" );
		
        String pgToken = request.getParameter("pg_token");
        
        String tid = (String) request.getSession().getAttribute("tid");
        String xid = (String) request.getSession().getAttribute("partner_order_id");
        String mid = (String) request.getSession().getAttribute("partner_user_id");
        
        System.out.println("===> ApproveServlet pgToken 확인" +  pgToken);
        System.out.println("===> ApproveServlet tid 확인:" + tid );
        
        
        String apiUrl = "https://kapi.kakao.com/v1/payment/approve";
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "KakaoAK " + ADMIN_KEY);
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        conn.setDoOutput(true);

        System.out.println("===>ApproveServlet conn 확인: " + conn );
        
        String params = "cid=" + CID +
                        "&tid=" + tid +
                        "&partner_order_id=" + xid +
                        "&partner_user_id=" + mid +
                        "&pg_token=" + pgToken;

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = params.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = conn.getResponseCode();
        Scanner sc;
        if (code == 200) {
            sc = new Scanner(conn.getInputStream());
            System.out.println("===>ApproveServlet sc 성공 확인: " + sc );
        } else {
            sc = new Scanner(conn.getErrorStream());
            System.out.println("===>ApproveServlet sc 실패 확인: " + sc );
        }
        
        StringBuilder result = new StringBuilder();
        while (sc.hasNext()) {
            result.append(sc.nextLine());
        }
        

        System.out.println("===>ApproveServlet result 확인: " + result );
        
       
        
        System.out.println("===>partner_order_id 확인: " + request.getSession().getAttribute("partner_order_id")  );
        System.out.println("===>partner_user_id 확인: " + request.getSession().getAttribute("partner_user_id") );
        System.out.println("===>total_amount 확인: " + request.getSession().getAttribute("total_amount") );

        sc.close(); 

        
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(result.toString());
        response.sendRedirect(path+"/CartController?sw=L");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
