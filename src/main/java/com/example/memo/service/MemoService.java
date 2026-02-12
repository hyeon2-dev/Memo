package com.example.memo.service;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto saveMemo(MemoRequestDto dto) {
        Memo memo = new Memo(dto.getTitle(), dto.getContents(), LocalDateTime.now(), LocalDateTime.now());
        Memo savedMemo = memoRepository.save(memo);

        return new MemoResponseDto(
                savedMemo.getId(),
                savedMemo.getTitle(),
                savedMemo.getContents(),
                savedMemo.getCreatedAt(),
                savedMemo.getModifiedAt()
                );

    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> getMemo() {
        List<Memo> memos = memoRepository.findAll();

        List<MemoResponseDto> memoDtos = new ArrayList<>();
        for (Memo memo : memos) {
            memoDtos.add(new MemoResponseDto(
                    memo.getId(),
                    memo.getTitle(),
                    memo.getContents(),
                    memo.getCreatedAt(),
                    memo.getModifiedAt()
                    ));
        }

        return memoDtos;
    }

    @Transactional(readOnly = true)
    public MemoResponseDto getMemoById(Long memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("id에 맞는 메모가 없어요.")
        );

        return new MemoResponseDto(
                memo.getId(),
                memo.getTitle(),
                memo.getContents(),
                memo.getCreatedAt(),
                memo.getModifiedAt()
        );
    }

    @Transactional
    public MemoResponseDto updateMemo(Long memoId, MemoRequestDto dto) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("id에 맞는 메모가 없어요.")
        );

        memo.update(dto.getTitle(), dto.getContents(), LocalDateTime.now());

        return new MemoResponseDto(
                memo.getId(),
                memo.getTitle(),
                memo.getContents(),
                memo.getCreatedAt(),
                memo.getModifiedAt()
        );
    }

    public void deleteById(Long memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("id에 맞는 메모가 없어요.")
        );

        memoRepository.deleteById(memoId);
    }
}
