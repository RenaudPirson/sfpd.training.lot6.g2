package be.sfpd.rest.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Article {

    private Long id;

    private LocalDate createdDate;

    private String body;

    private List<Comment> comments = new ArrayList<>();

    public Article() {
    }

    public Article(Long id, LocalDate createdDate, String body) {
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
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
