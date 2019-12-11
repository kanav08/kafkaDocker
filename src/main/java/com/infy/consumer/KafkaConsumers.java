package com.infy.consumer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class KafkaConsumers {

	private final static String TOPIC = "kafka_topic";
	private final static String BOOTSTRAP_SERVERS = "localhost:9098";

	Map<String, String> map = new LinkedHashMap<String, String>();

	@KafkaListener(topics = TOPIC, groupId = "group_id")

	public void consume(String message) throws InterruptedException, JsonProcessingException {
		map.put(getDate(), message);

//map.forEach((key,value)->System.out.println(key +" "+ value));

	}

	public String getAllMessagesFromKafkaTopic() throws JsonProcessingException {

		return new JSONObject(map).toString();
	}

	public String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		// System.out.println(dtf.format(now));
		return dtf.format(now).toString();
	}
}
