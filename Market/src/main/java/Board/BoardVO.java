package Board;

import lombok.Data;

@Data
public class BoardVO {
private String idx;
private String name;
private String title;
private String img;
private String etc;
private int cnt;
private String day;

private int xidx;
private int pageSize=10;
private int pageListSize=10;
private String str1;
private String str2;
}
