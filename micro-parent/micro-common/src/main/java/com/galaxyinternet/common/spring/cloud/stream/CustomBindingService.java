package com.galaxyinternet.common.spring.cloud.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.cloud.stream.binder.BinderFactory;
import org.springframework.cloud.stream.binder.Binding;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ExtendedConsumerProperties;
import org.springframework.cloud.stream.binder.ExtendedProducerProperties;
import org.springframework.cloud.stream.binder.ExtendedPropertiesBinder;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.cloud.stream.binding.BindingService;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.beanvalidation.CustomValidatorBean;

import com.galaxyinternet.common.event.config.EventTypeRegistry;
import com.galaxyinternet.common.event.entity.EventType;

public class CustomBindingService extends BindingService
{

	private final CustomValidatorBean validator;

	private final Log log = LogFactory.getLog(BindingService.class);

	private BinderFactory binderFactory;

	private final BindingServiceProperties bindingServiceProperties;

	private final Map<String, Binding<?>> producerBindings = new HashMap<>();

	private final Map<String, List<Binding<?>>> consumerBindings = new HashMap<>();

	private final EventTypeRegistry eventTypeRegistry;

	public CustomBindingService(BindingServiceProperties bindingServiceProperties, BinderFactory binderFactory, EventTypeRegistry eventTypeRegistry)
	{
		super(bindingServiceProperties, binderFactory);
		this.bindingServiceProperties = bindingServiceProperties;
		this.binderFactory = binderFactory;
		this.validator = new CustomValidatorBean();
		this.validator.afterPropertiesSet();
		this.eventTypeRegistry = eventTypeRegistry;
	}

	@SuppressWarnings({"unchecked","rawtypes"})
	public <T> Collection<Binding<T>> bindConsumer(T input, String inputName)
	{
		if(!Processor.INPUT.equals(inputName))
		{
			return super.bindConsumer(input, inputName);
		}
		//String[] bindingTargets = new String[eventTypeRegistry.getEventTypes().size()];
		//eventTypeRegistry.getEventTypes().toArray(bindingTargets);
		String[] bindingTargets ={EventType.USER_CREATED.name()};
		Collection<Binding<T>> bindings = new ArrayList<>();
		Binder<T, ConsumerProperties, ?> binder = (Binder<T, ConsumerProperties, ?>) getBinder(inputName, input.getClass());
		ConsumerProperties consumerProperties = this.bindingServiceProperties.getConsumerProperties(inputName);
		if (binder instanceof ExtendedPropertiesBinder)
		{
			Object extension = ((ExtendedPropertiesBinder) binder).getExtendedConsumerProperties(inputName);
			ExtendedConsumerProperties extendedConsumerProperties = new ExtendedConsumerProperties(extension);
			BeanUtils.copyProperties(consumerProperties, extendedConsumerProperties);
			consumerProperties = extendedConsumerProperties;
		}
		validate(consumerProperties);
		for (String target : bindingTargets)
		{
			System.err.println("bindConsumer- target="+target+", inputName="+inputName+", group="+bindingServiceProperties.getGroup(inputName)+",input="+input);
			Binding<T> binding = binder.bindConsumer(target, bindingServiceProperties.getGroup(inputName), input, consumerProperties);
			bindings.add(binding);
		}
		bindings = Collections.unmodifiableCollection(bindings);
		this.consumerBindings.put(inputName, new ArrayList<Binding<?>>(bindings));
		return bindings;
	}

	@SuppressWarnings({"unchecked","rawtypes"})
	public <T> Binding<T> bindProducer(T output, String outputName)
	{
		if(!Processor.OUTPUT.equals(outputName))
		{
			return super.bindProducer(output, outputName);
		}

		String bindingTarget = this.bindingServiceProperties.getBindingDestination(outputName);
		Binder<T, ?, ProducerProperties> binder = (Binder<T, ?, ProducerProperties>) getBinder(outputName, output.getClass());
		String commonOutputName = Processor.OUTPUT;
		ProducerProperties producerProperties = this.bindingServiceProperties.getProducerProperties(commonOutputName);
		if (binder instanceof ExtendedPropertiesBinder)
		{
			Object extension = ((ExtendedPropertiesBinder) binder).getExtendedProducerProperties(commonOutputName);
			ExtendedProducerProperties extendedProducerProperties = new ExtendedProducerProperties<>(extension);
			BeanUtils.copyProperties(producerProperties, extendedProducerProperties);
			producerProperties = extendedProducerProperties;
		}
		validate(producerProperties);
		Binding<T> binding = binder.bindProducer(bindingTarget, output, producerProperties);
		this.producerBindings.put(outputName, binding);
		return binding;
	}

	public void unbindConsumers(String inputName)
	{
		List<Binding<?>> bindings = this.consumerBindings.remove(inputName);
		if (bindings != null && !CollectionUtils.isEmpty(bindings))
		{
			for (Binding<?> binding : bindings)
			{
				binding.unbind();
			}
		} else if (log.isWarnEnabled())
		{
			log.warn("Trying to unbind '" + inputName + "', but no binding found.");
		}
	}

	public void unbindProducers(String outputName)
	{
		Binding<?> binding = this.producerBindings.remove(outputName);
		if (binding != null)
		{
			binding.unbind();
		} else if (log.isWarnEnabled())
		{
			log.warn("Trying to unbind '" + outputName + "', but no binding found.");
		}
	}

	private <T> Binder<T, ?, ?> getBinder(String channelName, Class<T> bindableType)
	{
		String transport = this.bindingServiceProperties.getBinder(channelName);
		return binderFactory.getBinder(transport, bindableType);
	}

	/**
	 * Provided for backwards compatibility. Will be removed in a future
	 * version.
	 * 
	 * @return
	 */
	@Deprecated
	public BindingServiceProperties getChannelBindingServiceProperties()
	{
		return this.bindingServiceProperties;
	}

	public BindingServiceProperties getBindingServiceProperties()
	{
		return this.bindingServiceProperties;
	}

	private void validate(Object properties)
	{
		RelaxedDataBinder dataBinder = new RelaxedDataBinder(properties);
		dataBinder.setValidator(validator);
		dataBinder.validate();
		if (dataBinder.getBindingResult().hasErrors())
		{
			throw new IllegalStateException(dataBinder.getBindingResult().toString());
		}
	}

}
