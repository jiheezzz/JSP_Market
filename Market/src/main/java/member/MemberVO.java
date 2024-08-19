package member;

import lombok.Data;

@Data
public class MemberVO {
	private String mid;
	private String mpassword1;
	private String mpassword2;
	private String mphone;
	private String maddr1;
	private String maddr2;
	private String maddr3;
	private String mname;
	private String mage;
	private String mgender;
	private String mgrade;
	private String metc;
	
	private String str1;
	private String str2;
	private int xidx;
	private int pageSize=10;
	private int pageListSize=10;
}
