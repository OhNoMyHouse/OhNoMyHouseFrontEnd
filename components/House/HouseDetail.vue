<template>
  <div>
    {{ house.sidoName }} {{ house.gugunName }}
    {{ house.dongName }}
  </div>
</template>

<script>
export default {
  name: "HouseDetail",
  props: {
    house: Object,
  },
};
</script>

<style></style>
