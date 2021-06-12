package com.suita.tarumi.SpringNihaoMemo.controller;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import com.suita.tarumi.SpringNihaoMemo.model.Memo;
import com.suita.tarumi.SpringNihaoMemo.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.ResourceBundle;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://www.nihaomemo.com")
@RestController
public class MemoController {
    @Autowired
    MemoRepository memoRepository;

    /**
     * 日本語と翻訳した中国語をDBに格納
     */
    @PostMapping("/")
    public ResponseEntity<Memo> register(@RequestBody Memo memo) throws IOException {
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
                .withText(memo.getJapanese())
                .withSourceLanguageCode("ja")
                .withTargetLanguageCode("zh");
        TranslateTextResult result  = translate.translateText(request);
        System.out.println(result.getTranslatedText());

        //ここでDBに日本語中国語格納
        try {
            Memo responseMemo = memoRepository.save(new Memo(memo.getJapanese(), result.getTranslatedText()));
            return new ResponseEntity<>(responseMemo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 全てのメモを取得
     */
    @GetMapping("/")
    public Iterable<Memo> getMemos() throws IOException {
        return memoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    /**
     * 削除ボタン押下したメモを削除
     */
    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable("id") Long id){
        memoRepository.deleteById(id);
    }

}
