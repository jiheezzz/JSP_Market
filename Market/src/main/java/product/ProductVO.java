package product;

import lombok.Data;

@Data
public class ProductVO {
	private String product_name;
	private String product_id;
	private int product_price;
	private String product_img;
	private String product_etc;
}
