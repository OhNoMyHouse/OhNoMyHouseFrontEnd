<template>
  <div>
    <main id="main">
      <br />
      <section id="services">
        <div class="container">
          <div class="col p-5 rounded">
            <header class="section-header wow fadeInUp">
              <h3>Notice 수정</h3>
            </header>
            <div class="poll-content">
              <input type="hidden" v-model="notice.idx" name="idx" />
              <div class="mb-3 mt-3">
                <input
                  type="text"
                  class="form-control"
                  id="title"
                  placeholder="내용을 입력해주세요"
                  v-model="notice.title"
                  required
                />
              </div>
              <div class="mb-3">
                <textarea
                  class="form-control"
                  rows="15"
                  id="content"
                  placeholder="제목을 입력해주세요"
                  v-model="qna.content"
                  required
                ></textarea>
              </div>
              <button @click="modifyNotice" class="btn btn-outline-success">
                수정완료
              </button>
              <button @click="deleteNotice" class="btn btn-outline-danger">
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
    notice() {
      let notice = { ...this.$store.state.notice };
      return notice;
    },
  },
  created() {
    console.log("NoticeDetail Comp.");
    this.idx = this.$route.params.idx;
    this.getNotice({ idx: this.idx });
  },
  methods: {
    ...mapActions([Constant.GET_NOTICE]),
    modifyNotice() {
      this.$store
        .dispatch(Constant.MODIFY_NOTICE, { qna: this.notice })
        .then(() => {
          alert("수정에 성공하였습니다.");
          this.moveList();
        });
    },
    deleteNotice() {
      this.$store.dispatch(Constant.DELETE_NOTICE, this.notice).then(() => {
        alert("삭제에 성공하였습니다.");
        this.moveList();
      });
    },
    moveList() {
      this.$router.push("/notice");
    },
  },
};
</script>

<style></style>
