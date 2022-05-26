<script lang="ts">
import Vue from "vue";
import Constant from "@/common/Constant.js";
import { getReadableDate } from "@/assets/utils";
import { mapState, mapActions } from "vuex";

export default Vue.extend({
  methods: {
    getReadableDate,
    getNotices() {
      this.$store.dispatch(Constant.GET_NOTICES);
    },
    ...mapState(["userInfo"]),
    ...mapActions(["getUserInfo"]),
    async onlyAuthUser() {
      if (this.userInfo().name !== "admin") {
        alert("관리자만 접근이 가능합니다..");
        this.$router.push({ path: `/notice` });
      } else {
        this.$router.push({ path: `/notice/regist` });
      }
    },
  },
  data: () => ({
    fields: [
      { key: "index", label: "번호", thClass: "w5" },
      { key: "title", label: "Title", thClass: "w25" },
      { key: "Content", label: "Content", thClass: "w45" },
    ],
  }),
  computed: {
    notices() {
      return this.$store.state.notices;
    },
    user() {
      return this.$store.state.userInfo;
    },
  },
  created() {
    console.log("NoticeList Comp.");
    this.getNotices();
  },
  filters: {
    ellipsis: function (value: string | any[], length: number, endString: any) {
      if (value.length <= length) return value;
      return value.slice(0, length) + endString;
    },
  },
});
</script>

<template>
  <aside class="content" id="explore">
    <!-- <section class="tags container">
      <button>
        <Icon name="trending" />
        <h1>Trending</h1>
      </button>
      <button>
        <Icon name="music" />
        <h1>Music</h1>
      </button>
      <button>
        <Icon name="gaming" />
        <h1>Gaming</h1>
      </button>
      <button>
        <Icon name="sports" />
        <h1>Sports</h1>
      </button>
      <button>
        <Icon name="live" />
        <h1>Live</h1>
      </button>
      <button>
        <Icon name="news" />
        <h1>News</h1>
      </button>
    </section>

    <section class="tags container">
      <button>
        <Icon name="live" />
        <h1>Live</h1>
      </button>
    </section> -->

    <!-- 추가 -->
    <div class="container">
      <div>
        <h1 style="margin-top: 100px">Notice</h1>
        <b-button variant="outline-primary" @click="onlyAuthUser()">추가</b-button>
      </div>
      <div v-if="notices.length > 0">
        <b-row>
          <b-col lg="12">
            <b-table striped bordered hover dark fixed outlined :items="notices" :fields="fields">
              <template #cell(index)="data">
                {{ data.index + 1 }}
              </template>
              <template #cell(content)="data">
                <NuxtLink
                  :to="{
                    path: `/notice/${data.item.idx}`,
                    params: { idx: data.item.idx },
                  }"
                  >{{ data.item.content | ellipsis(12, "......") }}</NuxtLink
                >
                <!-- <NuxtLink :to="`${data.item.idx}`">{{
                  data.item.content | ellipsis(12, "......")
                }}</NuxtLink> -->
              </template>
            </b-table>
          </b-col>
        </b-row>
      </div>
      <div v-else class="text-center">등록된 공지사항이 없습니다.</div>
    </div>
    <!-- 추가 -->
  </aside>
</template>

<style lang="scss">
@import "@/assets/css/mixins.scss";

aside.content#explore {
  section {
    &.tags {
      @include grid(6, 12px, 3);
      @include breakpoint {
        overflow-x: auto;
        @include flex($gap: 16px);
        &::-webkit-scrollbar {
          display: none;
        }
      }

      button {
        padding: 16px;
        cursor: pointer;
        border-radius: 8px;
        background: var(--gray);
        @include flex(center, $dir: column, $gap: 12px);
        @include breakpoint {
          flex: 0 0 32%;
        }

        svg {
          width: 32px;
          &.trending path {
            fill: var(--red);
          }
          &.music path {
            fill: #a855f7;
          }
          &.gaming path {
            fill: #06b6d4;
          }
          &.sports path {
            fill: #f59e0b;
          }
          &.live path,
          circle {
            fill: #10b981;
          }
          &.news path {
            fill: #ef4444;
          }
        }

        &:hover {
          background: var(--active);
        }
        h1 {
          font-size: 18px;
          font-weight: 500;
        }
      }
    }
  }
}

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
