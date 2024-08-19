package Cart;

import lombok.Data;

@Data
public class CartVO {
	private String cart_id;
	private String mid;
	private String product_id;
	private String amount;
	private String shipping_fee="2500";
}
