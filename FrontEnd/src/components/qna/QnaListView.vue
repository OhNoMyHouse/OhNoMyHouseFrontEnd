<template>
  <div class="container">
    <div>
      <h1 style="margin-top: 100px">QnA</h1>
      <b-button variant="outline-primary"
        ><router-link to="/qna/makeqna_form">추가</router-link></b-button
      >
    </div>
    <div v-if="qnas.length > 0">
      <b-row>
        <b-col lg="12">
          <b-table
            striped
            bordered
            hover
            dark
            fixed
            outlined
            :items="qnas"
            :fields="fields"
          >
            <template #cell(index)="data">
              {{ data.index + 1 }}
            </template>
            <template #cell(answer)="data">
              <router-link :to="'detail/' + data.item.idx">{{
                data.item.answer | ellipsis(12, "......")
              }}</router-link>
            </template>
          </b-table>
        </b-col>
      </b-row>
    </div>
    <div v-else class="text-center">등록된 QnA가 없습니다.</div>
  </div>
</template>

<script>
import Constant from "@/common/Constant.js";
import { mapGetters, mapActions } from "vuex";

export default {
  data() {
    return {
      fields: [
        { key: "index", label: "번호", thClass: "w5" },
        { key: "question", label: "Question", thClass: "w25" },
        { key: "answer", label: "Answer", thClass: "w45" },
      ],
    };
  },
  computed: mapGetters(["qnas"]),
  created() {
    console.log("QnaList Comp.");
    this.getQnas();
  },
  methods: {
    ...mapActions([Constant.GET_QNAS]),
  },
  filters: {
    ellipsis: function (value, length, endString) {
      if (value.length <= length) return value;
      return value.slice(0, length) + endString;
    },
  },
};
</script>

<style>
table.b-table thead th.w5 {
  width: 5%;
}
table.b-table thead th.w10 {
  width: 10%;
}
table.b-table thead th.w20 {
  width: 20%;
}
table.b-table thead th.w30 {
  width: 30%;
}
table.b-table thead th.w40 {
  width: 40%;
}
table.b-table thead th.w50 {
  width: 50%;
}
table.b-table thead th.w60 {
  width: 60%;
}
table.b-table thead th.w70 {
  width: 70%;
}
table.b-table thead th.w80 {
  width: 80%;
}
table.b-table thead th.w90 {
  width: 90%;
}
table.b-table thead th.w15 {
  width: 15%;
}
table.b-table thead th.w25 {
  width: 25%;
}
table.b-table thead th.w35 {
  width: 35%;
}
table.b-table thead th.w45 {
  width: 45%;
}
table.b-table thead th.w55 {
  width: 55%;
}
table.b-table thead th.w65 {
  width: 65%;
}
table.b-table thead th.w75 {
  width: 75%;
}
table.b-table thead th.w85 {
  width: 85%;
}
table.b-table thead th.w95 {
  width: 95%;
}
</style>
