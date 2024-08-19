package kakao;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Servlet implementation class PaymentServlet
 */
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 private static final String KAKAO_API_KEY = "708357e20ee115b73116b2113d72618f";  //  발급받은 Admin 키
	    private static final String CID = "TC0ONETIME"; // 테스트 CID
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String path = "http://localhost:8080/Market";   // 필히 문자열로 넘겨주어야 한다.  (중요);
		String  mid =  (String) request.getSession().getAttribute("mid");
		String sum2 = (String)request.getParameter("total_amount");

        String apiUrl = "https://kapi.kakao.com/v1/payment/ready";
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        String k = sdf.format(now);
        int ran = (int) (Math.random() * 1000) + 1;
        String xid = k + ran ;
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "KakaoAK " + KAKAO_API_KEY);
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        conn.setDoOutput(true);
        
        System.out.println("conn:" + conn);
        System.out.println("mid="+mid+",sum2="+sum2+",xid="+xid);
        String params = "cid=" + CID +
                        "&partner_order_id=" + xid+      //  order_id (상품 id)
                        "&partner_user_id=" + mid  +  //  user_id  (사용자 id)
                        "&item_name=짱구굿즈샵" +            //  item_name ( 상품명 ) 
                        "&quantity=1" +                  //  quantity ( 수량 )
                        "&total_amount=" + sum2+            //  total_amount ( 가격 )
                        "&vat_amount=1" +                //  vat_amount ( 부가가치세 : 세금 )
                        "&tax_free_amount=0" +           //  tax_free_amount ( 면세 )
                        "&approval_url=" + path + "/ApproveServlet" +
                        "&cancel_url=" + path + "/cancel" +
                        "&fail_url="+ path +"/fail";

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = params.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = conn.getResponseCode();
        Scanner sc;
        if (code == 200) {
            sc = new Scanner(conn.getInputStream());
            System.out.println("conn1:" + sc);
        } else {
            sc = new Scanner(conn.getErrorStream());
            System.out.println("conn2:" + sc);
        }
        
        StringBuilder result = new StringBuilder();
        while (sc.hasNext()) {
            result.append(sc.nextLine());
        }
        System.out.println("result:" + result);
        sc.close();
        
        // JSON 파싱 후, next_redirect_pc_url 값으로 리다이렉트
        
        // tid 는 결제 준비 단계에서 카카오페이로 부터 받은 거래 고유 번호 이다. 
        // 이 tid 는 결제 승인 단계에서 필수로 사용되며 세션 또는 다늘 방법으로 관리해야 한다. 
        // 준비 단계에서 받은 tid를 결제 승인 요청 시 함께 전송하여 결제를 완료 해 야 한다. 
        String tid = result.toString().split("\"tid\":\"")[1].split("\"")[0];
        
        // 방법1.
        request.getSession().setAttribute("tid", tid);
        
        
        String redirectUrl = result.toString().split("\"next_redirect_pc_url\":\"")[1].split("\"")[0];
        System.out.println("===>redirectUrl 확인:" + redirectUrl);
	        
        request.getSession().setAttribute("partner_user_id", mid);
        request.getSession().setAttribute("total_amount", sum2);
        request.getSession().setAttribute("partner_order_id", xid);
        
        response.sendRedirect(redirectUrl);
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
