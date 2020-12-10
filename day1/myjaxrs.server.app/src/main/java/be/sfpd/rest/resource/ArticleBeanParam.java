package be.sfpd.rest.resource;

import javax.ws.rs.QueryParam;

public class ArticleBeanParam {
	@QueryParam("offset")
	private Integer offset;
	@QueryParam("limit")
	private Integer limit;
	@QueryParam("year")
	private Integer year;

	public Integer getOffset() {
		return offset != null ? offset : 0;
	}

	public Integer getLimit() {
		return limit;
	}

	public Integer getYear() {
		return year;
	}
}
