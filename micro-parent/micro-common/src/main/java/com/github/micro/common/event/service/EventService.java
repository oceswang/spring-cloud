package com.github.micro.common.event.service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.micro.api.event.constants.EventType;
import com.github.micro.api.event.entity.BaseEvent;
import com.github.micro.common.event.config.EventTypeRegistry;
import com.github.micro.common.event.entity.EventProcess;
import com.github.micro.common.event.entity.EventProcessStatus;
import com.github.micro.common.event.entity.EventPublish;
import com.github.micro.common.event.entity.EventPublishStatus;
import com.github.micro.common.event.handler.EventHandler;
import com.github.micro.common.utils.JsonUtils;

@Service
public class EventService {
	private static final Logger logger = LoggerFactory.getLogger(EventService.class);
	@Autowired
	BinderAwareChannelResolver binderAwareChannelResolver;
	@Autowired
	EventPublishService eventPublishService;
	@Autowired
	EventProcessService eventProcessServcie;
	@Autowired
	EventTypeRegistry eventTypeRegistry;
	@Autowired
	TaskExecutor taskExecutor;
	
	@StreamListener(Processor.INPUT)
	public void receive(Message<byte[]> msg)
	{
		byte[] bytes = msg.getPayload();
		String message = new String(bytes);
		logger.debug(new String(bytes));
		try
		{
			receive(message);
		} catch (DataIntegrityViolationException e)
		{
			logger.error(String.format("保存EventProcess失败,Message=[%s]",message),e);
		}
		catch (Exception e)
		{
			logger.error(String.format("保存EventProcess失败,Message=[%s]",message),e);
			throw e;
		}
		
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
		eventPublishService.save(eventPublish);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public EventProcess receive(String message)
	{
		Map<String,Object> map = JsonUtils.json2Object(message, Map.class);
		if(map != null && map.containsKey("eventType"))
		{
			EventType eventType = EventType.valueOf((String) map.get("eventType"));
			EventProcess process = new EventProcess();
			process.setEventId((String)map.get("eventId"));
			process.setType(eventType);
			process.setStatus(EventProcessStatus.NEW);
			process.setPayload(message);
			process.setVersion(1);
			return eventProcessServcie.save(process);
			
		}
		return null;
	}
	
	@Transactional
	public void sendUnpublishEvent()
	{
		List<EventPublish> list = eventPublishService.findUnpublishedEvent();
		list.stream().forEach( item -> {
			send(item.getPayload(),item.getType().name());
			item.setStatus(EventPublishStatus.PUBLISHED);
			eventPublishService.save(item);
		});
	}
	@Transactional
	public void handleUnprocedssEvent()
	{
		List<EventProcess> list = eventProcessServcie.findByStatus(EventProcessStatus.NEW);
		CountDownLatch latch = new CountDownLatch(list.size());
		for(EventProcess item : list)
		{
			taskExecutor.execute(() -> {
				try
				{
					int update = eventProcessServcie.updateWithLock(item.getVersion(), item.getId());
					if(update>0)
					{
						item.setVersion(item.getVersion()+1);
						BaseEvent event = eventTypeRegistry.getEvent(item.getType(), item.getPayload());
						EventHandler<BaseEvent> handler = eventTypeRegistry.getHandler(item.getType());
						//不存在handler，状态-ignore
						if(handler == null)
						{
							logger.debug("Ignore event - "+item.getId());
							item.setStatus(EventProcessStatus.IGNORED);
							eventProcessServcie.save(item);
							return;
						}
						executeEventHandler(() -> {
							handler.handle(event);
							return null;
						} );
						//处理完成，状态-Processed
						item.setStatus(EventProcessStatus.PROCESSED);
						eventProcessServcie.save(item);
					}
				} catch (Exception e)
				{
					logger.error("处理事件异常, ID="+item.getId(),e);
				}
				finally
				{
					latch.countDown();
				}
			});
		}
		try
		{
			latch.await();
		} catch (InterruptedException e)
		{
			logger.error("Thread Error",e);
		}
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
    public <T> T executeEventHandler(Supplier<T> supplier){

        return supplier.get();

    }

}
