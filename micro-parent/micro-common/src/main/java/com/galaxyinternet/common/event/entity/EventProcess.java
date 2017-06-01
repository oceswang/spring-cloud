package com.galaxyinternet.common.event.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.galaxyinternet.common.entity.LockEntity;

@Entity
@Table(name="t_event_process")
public class EventProcess extends LockEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="event_status")
	@Enumerated(EnumType.STRING)
	private EventPublishStatus status;
	
	@Column
	private String payload;
	
	@Column(name="created_time")
	private LocalDateTime createdTime;
	
	@Column(name="event_type")
	@Enumerated(EnumType.STRING)
	private EventType type;
	
	@Column(name="event_id")
	private String eventId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EventPublishStatus getStatus() {
		return status;
	}

	public void setStatus(EventPublishStatus status) {
		this.status = status;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	
}
