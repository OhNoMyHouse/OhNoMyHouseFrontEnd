<template>
  <div>
    <main id="main">
      <br />
      <section id="services">
        <div class="container">
          <div class="col p-5 rounded">
            <header class="section-header wow fadeInUp">
              <h3>Qna 수정</h3>
            </header>
            <div class="poll-content">
              <input type="hidden" v-model="qna.idx" name="idx" />
              <div class="mb-3 mt-3">
                <input
                  type="text"
                  class="form-control"
                  id="question"
                  placeholder="내용을 입력해주세요"
                  v-model="qna.question"
                  required
                />
              </div>
              <div class="mb-3">
                <textarea
                  class="form-control"
                  rows="15"
                  id="content"
                  placeholder="제목을 입력해주세요"
                  v-model="qna.answer"
                  required
                ></textarea>
              </div>
              <button @click="modifyQna" class="btn btn-outline-success">
                수정완료
              </button>
              <button @click="deleteQna" class="btn btn-outline-danger">
                삭제
              </button>
              <button @click="moveList" class="btn btn-outline-dark">
                목록
              </button>
            </div>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
import Constant from "@/common/Constant.js";
import { mapActions } from "vuex";

export default {
  data() {
    return {
      idx: 0,
    };
  },
  computed: {
    qna() {
      let qna = { ...this.$store.state.qna };
      return qna;
    },
  },
  created() {
    console.log("QnaDetail Comp.");
    this.idx = this.$route.params.idx;
    this.getQna({ idx: this.idx });
  },
  methods: {
    ...mapActions([Constant.GET_QNA]),
    modifyQna() {
      this.$store.dispatch(Constant.MODIFY_QNA, { qna: this.qna }).then(() => {
        alert("수정에 성공하였습니다.");
        this.moveList();
      });
    },
    deleteQna() {
      this.$store.dispatch(Constant.DELETE_QNA, this.idx).then(() => {
        alert("삭제에 성공하였습니다.");
        this.moveList();
      });
    },
    moveList() {
      this.$router.push("/qna");
    },
  },
};
</script>

<style></style>
