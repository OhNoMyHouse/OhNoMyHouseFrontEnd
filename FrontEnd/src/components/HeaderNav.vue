<template>
  <header id="header">
    <div class="container-fluid">
      <div id="logo" class="pull-left">
        <h1>
          <a href="#intro" class="scrollto">OhNoMyHouse</a>
        </h1>
        <!-- Uncomment below if you prefer to use an image logo -->
        <!-- <a href="#intro"><img src="img/logo.png" alt="" title="" /></a>-->
      </div>

      <nav id="nav-menu-container">
        <ul class="nav-menu">
          <li class="menu-active"><a href="#intro">Home</a></li>
          <li class="menu-active"><a href="#info">공지사항</a></li>
          <li class="menu-has-children">
            <a href="#">Search</a>
            <ul>
              <li><a href="#1">아파트 조회</a></li>
              <li><a href="#2">아파트별 실거래가 조회</a></li>
              <li><a href="#3">동별 실거래가 조회</a></li>
              <li><a href="#4">관심 지역 등록</a></li>
              <li><a href="#5">주변 상가 조회</a></li>
              <li><a href="#6">주변 환경</a></li>
              <li><a href="#7">사이트 맵</a></li>
            </ul>
          </li>
          <li>
            <router-link :to="{ name: 'qna' }" class="link">Q&A</router-link>
          </li>
          <!-- <li data-wow-duration="0.7s" id="searchBox"><input type="text" id="searchBox-id" /></li>
          <li onclick="search()"><i class="ion-search" style="color: white"></i></li> -->
        </ul>
      </nav>
      <!-- #nav-menu-container -->
    </div>
  </header>
</template>

<script>
export default {
  name: "HeaderNav",
};
</script>

<style>
#header {
  background: rgba(0, 0, 0, 0.9);
  padding: 20px 0;
  height: 72px;
  transition: all 0.5s;
}
</style>
