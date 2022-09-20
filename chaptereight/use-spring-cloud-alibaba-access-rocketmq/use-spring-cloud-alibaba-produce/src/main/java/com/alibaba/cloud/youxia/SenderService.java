package com.alibaba.cloud.youxia;
import com.alibaba.cloud.youxia.source.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
@Service
public class SenderService {
	@Autowired
	private MySource source;

	public void send(String msg) {
		source.output1().send(MessageBuilder.withPayload(msg).build());
	}
}
