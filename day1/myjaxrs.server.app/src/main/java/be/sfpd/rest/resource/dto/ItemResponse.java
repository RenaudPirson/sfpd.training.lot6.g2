package be.sfpd.rest.resource.dto;

import java.time.LocalDateTime;

public class ItemResponse<T> {
	private T item;
	private LocalDateTime timestamp = LocalDateTime.now();

	public ItemResponse() {
	}

	public ItemResponse(T item) {
		this.item = item;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
