<template>
  <div>
    <main id="main">
      <br />
      <section id="services">
        <div class="container">
          <div class="col p-5 rounded">
            <header class="section-header wow fadeInUp">
              <h3>QnA 등록</h3>
            </header>
            <div class="poll-content">
              <form class="was-validated">
                <div class="mb-3 mt-3">
                  <input
                    type="text"
                    class="form-control"
                    id="question"
                    placeholder="질문을 입력해주세요"
                    v-model="question"
                    required
                  />
                </div>
                <div class="mb-3">
                  <textarea
                    class="form-control"
                    rows="15"
                    id="answer"
                    placeholder="답변을 입력해주세요"
                    v-model="answer"
                    required
                  ></textarea>
                </div>
                <button @click="registQna" class="btn btn-outline-success">
                  등록
                </button>
                <button @click="moveList" class="btn btn-outline-dark">
                  취소
                </button>
              </form>
            </div>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
import Constant from "@/common/Constant";
export default {
  data() {
    return {
      question: "",
      answer: "",
    };
  },
  created() {
    console.log("QnaRegistForm Comp.");
  },
  methods: {
    registQna() {
      this.$store
        .dispatch(Constant.REGIST_QNA, {
          qna: { question: this.question, answer: this.answer },
        })
        .then(() => {
          alert("Qna 등록에 성공하였습니다.");
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
