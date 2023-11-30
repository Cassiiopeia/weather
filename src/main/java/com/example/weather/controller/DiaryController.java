package com.example.weather.controller;

import com.example.weather.domain.Diary;
import com.example.weather.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;


    @Operation(summary = "일기 테스트, 날짜를 이용해서 DB에 저장합니다.",
            description = "Date, text를 입력합니다.")
    @PostMapping("/create/diary")
    void createDiary(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "일기 데이터 저장 날짜를 입력합니다."
                    ,example = "2023-11-30")
            LocalDate date,
            @RequestBody
            @Parameter(description = "일기 텍스트를 입력합니다."
                    ,example = "example diary text")
            String text
    ) {
        diaryService.createDiary(date,text);
    }

    @Operation(summary = "선택날짜의 모든 일기 데이터를 가져옵니다.",
            description = "Date를 입력합니다.")
    @GetMapping("/read/diary")
    List<Diary> readDiary(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "테이터를 가져올 날짜를 입력합니다."
                    ,example = "2023-11-30")
            LocalDate date
    ){
        return diaryService.readDiary(date);
    }


    @Operation(summary = "선택기간동안의 모든 일기 데이터를 가져옵니다.",
            description = "시작날짜와 끝날짜를 입력합니다.")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "시작 날짜를 입력합니다."
                    ,example = "2023-11-30")
            LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "끝 날짜를 입력합니다."
                    ,example = "2023-12-30")
            LocalDate endDate
    ){
        return diaryService.readDiaries(startDate,endDate);
    }

    @Operation(summary = "선택한 날짜의 일기데이터의 텍스트를 변경하여 저장합니다.",
            description = "날짜와 텍스트를 입력합니다.")
    @PutMapping("/update/diary")
    void updateDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "변경할 날짜를 입력합니다."
                    ,example = "2023-12-30")
            LocalDate date,
            @Parameter(description = "변경할 텍스트를 입력합니다."
                    ,example = "example diary text")
            @RequestBody String text
    ){
        diaryService.updateDiary(date, text);
    }

    @Operation(summary = "선택한 날짜의 전체 일기를 삭제합니다.",
            description = "날짜를 입력합니다.")
    @DeleteMapping("/delete/diary")
    void deleteDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "일기를 삭제할 날짜를 입력합니다."
                    ,example = "2023-12-30")
            LocalDate date
    ){
        diaryService.deleteDiary(date);
    }


}
