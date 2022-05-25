<script lang="ts">
import Vue from "vue";
import Constant from "@/common/Constant.js";
import { mapState, mapMutations } from "vuex";

export default Vue.extend({
  name: "DefaultLayout",
  data: () => ({
    sidebarCollapsed: false,
    word: "",
  }),
  computed: {
    ...mapState(["userInfo"]),
  },
  methods: {
    searchHouses() {
      if (this.word != "") {
        this.$store.dispatch(Constant.GET_HOUSES, { word: this.word });
        console.log(this.word);
      } else {
        alert("검색어를 입력해 주세요");
      }
    },
    ...mapMutations(["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ path: "/" });
    },
  },
});
</script>

<template>
  <div :id="$route.name ? $route.name + '-page' : 'error-page'">
    <header class="navigation">
      <aside class="brand">
        <button class="menu action" @click="sidebarCollapsed = !sidebarCollapsed">
          <Icon name="menu" />
        </button>
        <NuxtLink class="home" to="/"><Icon name="logo" /></NuxtLink>
      </aside>

      <aside class="search">
        <button class="action" @click="searchHouses">
          <Icon name="search" />
        </button>
        <input type="text" placeholder="Search" v-model="word" @keyup.enter="searchHouses" />
        <button class="action"><Icon name="microphone" /></button>
      </aside>

      <aside class="user">
        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <button to="/user/mypage" class="avatar action"><Icon name="user" /></button>
          <b-nav-item class="link align-self-center" @click.prevent="onClickLogout">로그아웃</b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-else>
          <b-button to="/user/login" style="background: #1a73e8; border: 1px solid transparent; margin-left: 8px; height: 35px">로그인</b-button>
        </b-navbar-nav>
      </aside>
    </header>

    <main :class="$route.name === null || $route.name === 'watch' ? { sidebar: sidebarCollapsed } : { compact: sidebarCollapsed }">
      <aside class="sidebar">
        <aside class="links">
          <NuxtLink to="/"
            ><Icon name="home" />
            <h6>Home</h6></NuxtLink
          >
          <NuxtLink to="/notice"
            ><Icon name="explore" />
            <h6>Notice</h6></NuxtLink
          >
          <NuxtLink to="/favorite"
            ><Icon name="library" />
            <h6>Favorite</h6></NuxtLink
          >
          <NuxtLink to="/history"
            ><Icon name="history" />
            <h6>History</h6></NuxtLink
          >
        </aside>
      </aside>

      <Nuxt />
    </main>
  </div>
</template>

<style lang="scss">
@import "@/assets/css/mixins.scss";

h6 {
  margin: 0;
}

div {
  header.navigation {
    padding: 8px 12px;
    background: var(--bg);
    position: sticky;
    top: 0;
    border-bottom: 1px solid var(--gray);
    @include flex(center, space-between, $gap: 16px);

    aside {
      &.brand {
        @include flex(center, $gap: 8px);

        button.menu {
          padding: 12px;
          @include breakpoint {
            display: none;
          }
        }

        a.home {
          @include flex(center, center);
          svg.logo {
            width: 90px;
          }
        }
      }

      &.search {
        width: 640px;
        padding: 4px 10px;
        border-radius: 8px;
        background: var(--gray);
        transition: 150ms background, 150ms box-shadow;
        @include flex(center);
        @include breakpoint {
          display: none;
        }

        input {
          flex-grow: 1;
          padding: 8px;
          font-size: 16px;
          background: transparent;
          &:focus {
            outline: none;
          }
        }

        &:focus-within {
          --hover: #0000000f;
          background: white;
          box-shadow: 0 1px 4px var(--shadow);
          svg path,
          input,
          input::placeholder {
            fill: #5f6368;
            color: #5f6368;
          }
        }
      }

      &.user {
        @include flex(center, $gap: 8px);
        @include breakpoint {
          button.search {
            display: flex !important;
          }
        }
        button.avatar {
          padding: 4px;
          svg {
            width: 32px;
          }
        }
        button.search {
          display: none;
        }
      }
    }
  }

  main {
    overflow-y: hidden;
    @include flex(flex-start);
    @include breakpoint {
      flex-direction: column-reverse;
    }

    > aside {
      &.sidebar {
        width: 256px;
        flex-shrink: 0;
        overflow-y: auto;
        background: var(--bg);
        border-right: 1px solid var(--gray);
        height: calc(100vh - var(--nav-height));
        position: sticky;
        // top: var(--nav-height);
        top: var(-height);
        @include breakpoint {
          width: 100%;
          height: auto;
          bottom: 0;
          border: none;
        }

        aside {
          padding: 12px 12px 12px 0;
          @include flex($dir: column);
          @include breakpoint {
            padding: 0;
            @include grid(4, 0);
          }

          > h1 {
            margin: 0 0 8px 24px;
            color: var(--icon);
            font-size: 14px;
            font-weight: 500;
          }

          a {
            font-weight: 500;
            padding: 10px 24px;
            border-radius: 0 9999px 9999px 0;
            @include flex(center, $gap: 16px);
            @include breakpoint {
              padding: 8px;
              border-radius: 0;
              flex-direction: column;
              flex-grow: 1;
              gap: 4px;
              h1 {
                font-size: 12px;
              }
            }

            &:hover {
              background: var(--hover);
            }
            &.nuxt-link-exact-active {
              background: var(--bg-red);
              svg path {
                fill: var(--red);
              }
              h1 {
                color: var(--red);
                font-weight: 500;
              }
              @include breakpoint {
                position: relative;
                background: transparent;
              }
            }
          }
        }

        &::-webkit-scrollbar-thumb {
          background: transparent;
        }
        &:hover::-webkit-scrollbar-thumb {
          background: var(--icon);
        }
      }
    }

    &.compact aside.sidebar {
      width: 72px;

      h6 {
        display: none;
      }
      aside {
        margin: 0 24px;
        padding-right: 0;
        @include flex(center, center, $dir: column);

        a {
          padding: 10px;
          border-radius: 9999px;
        }
        &.subscriptions {
          display: none;
        }
      }
    }
  }

  &#error-page {
    main {
      aside.content {
        @include breakpoint {
          max-height: calc(100vh - var(--nav-height));
        }
      }

      &:not(.sidebar) aside.sidebar {
        display: none;
      }
      &.sidebar aside {
        &.sidebar {
          position: fixed;
          z-index: 10;
        }
        &.content {
          filter: blur(48px);
          position: relative;
          pointer-events: none;
        }
      }
    }
  }
}
</style>
