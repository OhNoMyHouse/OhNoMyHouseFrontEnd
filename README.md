# 🎽 SSAFY 일곱 번째 관통 PJT 🎽

# Front-End & Back-End

# OhNoMyHouse

### 집이 없는 당신을 위한 Solution

###

---

<img src="https://img.shields.io/badge/SSAFY-0078D7?style=flat_square&logo=SSAFY&logoColor=white">
<img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=flat_square&logo=IntelliJ IDEA&logoColor=white">
<img src="https://img.shields.io/badge/GitLab-%23181717.svg?style=flat_square&logo=gitlab&logoColor=white">
<img src="https://img.shields.io/badge/bootstrap-%23563D7C.svg?style=flat_square&logo=bootstrap&logoColor=white">
<img src="https://img.shields.io/badge/Spring-6DB33F?style=flat_square&logo=Spring&logoColor=white">
<img src="https://img.shields.io/badge/JAVA-007396?style=flat_square&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/Mysql-4479A1?style=flat_square&logo=Mysql&logoColor=white">
<img src="https://img.shields.io/badge/HTML-E34F26?style=flat_square&logo=Html5&logoColor=black">
<img src="https://img.shields.io/badge/Css-1572B6?style=flat_square&logo=CSS3&logoColor=black">
<img src="https://img.shields.io/badge/Javascript-F7DF1E?style=flat_square&logo=Javascript&logoColor=black">
<img src="https://img.shields.io/badge/jquery-%230769AD.svg?style=flat_square&logo=jquery&logoColor=white">

---

### 팀원 소개

| 팀원                                              | 소개        |
| ------------------------------------------------- | ----------- |
| [민상규](https://lab.ssafy.com/qksl7504)          | 잘생긴 상규 |
| [정태영](https://lab.ssafy.com/taeyeong.jeong419) | 귀요미 탱구 |

---

### 🧩 단계별 구현

| 난이도 | 구현 기능                    | 완성 여부 |
| :----: | :--------------------------- | :-------: |
|  기본  | Vue - 게시글 목록            |    ⭕     |
|  기본  | Vue - 게시글 등록            |    ⭕     |
|  기본  | Vue - 게시글 수정            |    ⭕     |
|  기본  | Vue - 게시글 상세검색        |    ⭕     |
|  기본  | Server - 게시글 등록         |    ⭕     |
|  기본  | Server - 게시글 수정         |    ⭕     |
|  기본  | Server - 게시글 삭제         |    ⭕     |
|  기본  | Server - 게시글 상세검색     |    ⭕     |
|  기본  | Server - 게시글 목록         |    ⭕     |
|  추가  | Vue - 게시글 목록 filter적용 |    ⭕     |
|  심화  | Vue - 메인페이지             |    ⭕     |

---

### Usecase Diagram

<img src="./readme_img/MainUML.png" width="60%" height="auto"></img>
<img src="./readme_img/DefualtUML.png" width="60%" height="auto"></img>

---

### 💻 구현페이지 (웹 페이지)

#### No.01 메인페이지

<img src="./readme_img/1.png" width="60%" height="auto"></img>

- Vue.js가 적용된 메인페이지
- Q&A를 누르면 Q&A List를 볼 수 있는 페이지로 이동한다.

---

#### No.02 Q&A 등록

<img src="./readme_img/2.png" width="60%" height="auto"></img>

- Q&A를 등록할 수 있다.
- 등록 버튼을 누르면 Q&A DB에 Insert 되고, 취소 버튼을 누르면 Q&A List 페이지로 이동한다.

---

#### No.03 Q&A 목록

<img src="./readme_img/3.png" width="60%" height="auto"></img>

- Q&A 목록 조회가 가능하다.
- Q&A Answer 부분에 Filter를 적용하여 12글자가 넘어가면 "......" 문자열로 축약된다.

---

#### No.04 Q&A 상세내용

<img src="./readme_img/4.png" width="60%" height="auto"></img>

- Q&A 상세내용 조회가 가능하다
- Q&A를 수정하거나 삭제를 할 수 있으며 목록을 누르면 Q&A List 페이지로 이동한다.

---

### 🎁 구현Source

- Q&A 기능에 해당하는 Controller

```
package com.ssafy.happyhouse.controller;

import java.util.List;

import com.ssafy.happyhouse.model.dto.QnA;
import com.ssafy.happyhouse.model.service.QnAService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/qna")
@RestController
@CrossOrigin
public class QnAController {

    @Autowired
    QnAService qnAService;

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @GetMapping
    private ResponseEntity<List<QnA>> selectQnAList() {
        logger.debug("call by selectQnAList");
        List<QnA> list = qnAService.selectQnAList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    private ResponseEntity<QnA> selectQnA(@PathVariable int idx) {
        logger.debug("call by selectQnA");
        QnA board = qnAService.selectQnA(idx);
        if (board != null) {
            return ResponseEntity.ok(board);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity insertQnA(@RequestBody QnA qna) {
        logger.debug("call by insertQnA");
        qnAService.insertQnA(qna);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idx}")
    private ResponseEntity updateQnA(@PathVariable int idx, @RequestBody QnA qna) {
        logger.debug("call by updateQnA");
        if (qnAService.selectQnA(idx) != null) {
            qnAService.updateQnA(qna);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idx}")
    private ResponseEntity deleteQnA(@PathVariable int idx) {
        logger.debug("call by deleteQnA");
        if (qnAService.selectQnA(idx) != null) {
            qnAService.deleteQnA(idx);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

```

---
