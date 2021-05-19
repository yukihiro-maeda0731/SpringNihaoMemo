package com.suita.tarumi.SpringNihaoMemo.repository;

import com.suita.tarumi.SpringNihaoMemo.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
