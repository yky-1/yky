package tool;

public class NewsComStat {

	private String month;
	private int news;
	private int comment;
	public String getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		if(month==1)this.month="һ��";
		else if(month==2) this.month="����";
		else if(month==3) this.month="����";
		else if(month==4) this.month="����";
		else if(month==5) this.month="����";
		else if(month==6) this.month="����";
		else if(month==7) this.month="����";
		else if(month==8) this.month="����";
		else if(month==9) this.month="����";
		else if(month==10) this.month="ʮ��";
		else if(month==11) this.month="ʮһ��";
		else if(month==12) this.month="ʮ����";
		else this.month=null;
	}
	public int getNews() {
		return news;
	}
	public void setNews(int news) {
		this.news = news;
	}
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}
}
