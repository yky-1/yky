package tool;

public class NewsComStat {

	private String month;
	private int news;
	private int comment;
	public String getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		if(month==1)this.month="一月";
		else if(month==2) this.month="二月";
		else if(month==3) this.month="三月";
		else if(month==4) this.month="四月";
		else if(month==5) this.month="五月";
		else if(month==6) this.month="六月";
		else if(month==7) this.month="七月";
		else if(month==8) this.month="八月";
		else if(month==9) this.month="九月";
		else if(month==10) this.month="十月";
		else if(month==11) this.month="十一月";
		else if(month==12) this.month="十二月";
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
