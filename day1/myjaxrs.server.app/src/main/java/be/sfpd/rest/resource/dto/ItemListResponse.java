package be.sfpd.rest.resource.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ItemListResponse<T> {
	private  List<T> items;

	private  LocalDateTime timestamp = LocalDateTime.now();

	public ItemListResponse() {
	}

	public ItemListResponse(List<T> items) {
		this.items = items;
	}

	public List<T> getItems() {
		return items;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
}
