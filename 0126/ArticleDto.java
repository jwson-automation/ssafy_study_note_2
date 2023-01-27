
import java.sql.Date;

public class ArticleDto {
	int idx;
	private String username;
    private String subject;
    private String content;
    Date regtime ;
    
	public ArticleDto(String username, String subject, String content) {
		super();
		idx = 1 ; 
		this.username = username;
		this.subject = subject;
		this.content = content;
		regtime = null;
	}
	
	public ArticleDto(int idx,String username, String subject, String content, Date regtime) {
		super();
		this.idx = idx; 
		this.username = username;
		this.subject = subject;
		this.content = content;
		this.regtime = regtime;
	}

	@Override
	public String toString() {
		return "ArticleDto [idx=" + idx + ", username=" + username + ", subject=" + subject + ", content=" + content
				+ ", regtime=" + regtime + "]";
	}

	public String getUsername() {
		
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
