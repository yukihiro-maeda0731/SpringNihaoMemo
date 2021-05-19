package com.suita.tarumi.SpringNihaoMemo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "memo")
public class Memo {

    public Memo(){}

    public Memo(String japanese, String targetLang) {
        this.japanese = japanese;
        this.targetLang = targetLang;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "japanese")
    private String japanese;

    @Column(name = "target_lang")
    private String targetLang;


}
