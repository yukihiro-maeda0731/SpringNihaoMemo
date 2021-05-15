package com.suita.tarumi.SpringNihaoMemo;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ResourceBundle;

@SpringBootApplication
public class SpringNihaoMemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringNihaoMemoApplication.class, args);
	}

}
