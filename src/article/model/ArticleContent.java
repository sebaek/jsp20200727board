package article.model;

public class ArticleContent {
	private Integer number;
	private String content;
	
	public ArticleContent(Integer number, String content) {
		super();
		this.number = number;
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	public Integer getNumber() {
		return number;
	}
	
}
