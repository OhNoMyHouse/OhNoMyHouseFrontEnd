<template>
  <div>
    <main id="main">
      <br />
      <section id="services">
        <div class="container">
          <div class="col p-5 rounded">
            <header class="section-header wow fadeInUp">
              <h3>Notice 등록</h3>
            </header>
            <div class="poll-content">
              <form class="was-validated">
                <div class="mb-3 mt-3">
                  <input
                    type="text"
                    class="form-control"
                    id="title"
                    placeholder="제목을 입력해주세요"
                    v-model="title"
                    required
                  />
                </div>
                <div class="mb-3">
                  <textarea
                    class="form-control"
                    rows="15"
                    id="content"
                    placeholder="내용을 입력해주세요"
                    v-model="content"
                    required
                  ></textarea>
                </div>
                <button @click="registNotice" class="btn btn-outline-success">
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
    <nuxt-child />
  </div>
</template>

<script>
import Constant from "@/common/Constant";
export default {
  data() {
    return {
      title: "",
      content: "",
    };
  },
  created() {
    console.log("NoticeRegistForm Comp.");
  },
  methods: {
    registNotice() {
      this.$store
        .dispatch(Constant.REGIST_NOTICE, {
          notice: { title: this.title, content: this.content },
        })
        .then(() => {
          alert("Notice 등록에 성공하였습니다.");
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
