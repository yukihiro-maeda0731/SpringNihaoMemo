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

		// 外部ファイルからAWSサービスにアクセスするためのキー取得
		final String RESOURCE_NAME = "application";
		ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_NAME);
		final String accessKey = rb.getString("accesskey");
		final String secretkey = rb.getString("secretkey");
		final String REGION = rb.getString("region");

		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretkey);


		AmazonTranslate translate = AmazonTranslateClient.builder()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(REGION)
				.build();

		TranslateTextRequest request = new TranslateTextRequest()
				.withText("私はプログラマー")
				.withSourceLanguageCode("ja")
				.withTargetLanguageCode("zh");
		TranslateTextResult result  = translate.translateText(request);
		System.out.println(result.getTranslatedText());
	}

}
