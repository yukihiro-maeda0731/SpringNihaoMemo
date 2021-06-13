package com.suita.tarumi.SpringNihaoMemo.repository;

import com.suita.tarumi.SpringNihaoMemo.model.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    Page<Memo> findAll(Pageable pageable);
}
