package com.suita.tarumi.SpringNihaoMemo.Controller;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.ResourceBundle;

@CrossOrigin(origins = "http://angularnihaomemo.s3-website-ap-northeast-1.amazonaws.com")
@RestController
public class RegisterController {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String transferImg(@RequestBody String translatedPhrase) throws IOException {
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
                .withText(translatedPhrase)
                .withSourceLanguageCode("ja")
                .withTargetLanguageCode("zh");
        TranslateTextResult result  = translate.translateText(request);
        System.out.println(result.getTranslatedText());
        return result.getTranslatedText();
    }

}
