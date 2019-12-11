package com.infy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infy.consumer.KafkaConsumers;
import com.infy.model.Message;
import com.infy.producer.KafkaProducer;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

	@Autowired
	private KafkaProducer kafkaProducer;

	@Autowired
	private KafkaConsumers kafkaConsumers;

	@PostMapping(value = "/publish")
	public String sendMessageToKafkaTopic(@RequestBody Message message) {
		kafkaProducer.send(message.getMessage());
		return "Message Sent Successfully to topic..";
	}

	@GetMapping(value = "/getAllMessages")
	public String getAllMessagesFromKafkaTopic() throws JsonProcessingException {
		return kafkaConsumers.getAllMessagesFromKafkaTopic();
	}

}
