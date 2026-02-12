package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public ResponseEntity<MemoResponseDto> saveMemo(@RequestBody MemoRequestDto dto) {
        return ResponseEntity.ok(memoService.saveMemo(dto));
    }

    @GetMapping("/memos")
    public ResponseEntity<List<MemoResponseDto>> getAllMemos() {
        return ResponseEntity.ok(memoService.getMemo());
    }

    @GetMapping("/memos/{memoId}")
    public ResponseEntity<MemoResponseDto> getMemoById(@PathVariable Long memoId) {
        return ResponseEntity.ok(memoService.getMemoById(memoId));
    }

    @PutMapping("/memos/{memoId}")
    public ResponseEntity<MemoResponseDto> updateMemo(
            @PathVariable Long memoId,
            @RequestBody MemoRequestDto dto
    ) {
        return ResponseEntity.ok(memoService.updateMemo(memoId, dto));
    }

    @DeleteMapping("/memos/{memoId}")
    public void deleteMemo(@PathVariable Long memoId){
        memoService.deleteById(memoId);
    }
}
