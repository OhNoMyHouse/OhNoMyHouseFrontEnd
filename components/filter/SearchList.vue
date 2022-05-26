<template>
  <aside class="serach">
    <div style="width: 250px">가격 비교:</div>
    <b-form-input v-model="start" placeholder="Start"></b-form-input>
    <div>~</div>
    <b-form-input v-model="end" placeholder="end"></b-form-input>
  </aside>
</template>

<script>
export default {
  data: () => ({
    start: null,
    end: null,
  }),
  computed: {},
};
</script>

<style lang="scss">
@import "@/assets/css/mixins.scss";

aside.content#home {
  section {
    aside {
      &.serach {
        display: flex;
        align-items: unset;
        flex-direction: column;
        justify-content: unset;
        overflow-x: auto;
        padding: 12px 24px;
        padding-right: 124px;
        @include flex(center, $gap: 8px);
      }
    }
  }
}
</style>
