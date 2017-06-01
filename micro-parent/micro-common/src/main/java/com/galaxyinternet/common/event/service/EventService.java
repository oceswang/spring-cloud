package com.galaxyinternet.common.event.service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxyinternet.common.event.dao.EventPublishDAO;
import com.galaxyinternet.common.event.entity.BaseEvent;
import com.galaxyinternet.common.event.entity.EventPublish;
import com.galaxyinternet.common.event.entity.EventPublishStatus;
import com.galaxyinternet.common.utils.JsonUtils;

@Service
public class EventService {
	
	@Autowired
	BinderAwareChannelResolver binderAwareChannelResolver;
	@Autowired
	EventPublishDAO eventPublishDAO;
	
	@StreamListener(Processor.INPUT)
	public void receive(Message<byte[]> msg)
	{
		byte[] bytes = msg.getPayload();
        System.err.println(new String(bytes));
	}
	
	public boolean send(String message, String destination)
	{
		MessageChannel messageChannel = binderAwareChannelResolver.resolveDestination(destination);
        byte[] payload = message.getBytes(Charset.forName("UTF-8"));
        return messageChannel.send(MessageBuilder.withPayload(payload).build(), 1000L);
	}
	@Transactional
	public void publish(BaseEvent event)
	{
		event.setEventId(UUID.randomUUID().toString());
		EventPublish eventPublish = new EventPublish();
		eventPublish.setCreatedTime(LocalDateTime.now());
		eventPublish.setStatus(EventPublishStatus.NEW);
		eventPublish.setPayload(JsonUtils.object2Json(event));
		eventPublish.setEventId(event.getEventId());
		eventPublish.setType(event.getEventType());
		eventPublishDAO.save(eventPublish);
	}
	@Transactional
	public void sendUnpublishEvent()
	{
		List<EventPublish> list = eventPublishDAO.findByStatus(EventPublishStatus.NEW);
		list.stream().forEach( item -> {
			send(item.getPayload(),item.getType().name());
			item.setStatus(EventPublishStatus.PUBLISHED);
			eventPublishDAO.save(item);
			eventPublishDAO.flush();
		});
	}

}
