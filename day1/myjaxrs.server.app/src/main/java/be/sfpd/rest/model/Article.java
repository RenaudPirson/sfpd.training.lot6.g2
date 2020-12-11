package be.sfpd.rest.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbProperty;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Article {

    private Long id;

    private LocalDateTime createdDate;

    private String body;

    @JsonbProperty("comments")
    private List<Comment> comments = new ArrayList<>();

    public Article() {
    }

    public Article(Long id, LocalDateTime createdDate, String body) {
        this.id = id;
        this.createdDate = createdDate;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

	public List<Comment> getComments() {
		return comments;
	}

}
