<template>
  <div>
    <nav-menu class="nav"></nav-menu>
    <router-view/>
  </div>
</template>

<script>
    import NavMenu from "../common/NavMenu";
    export default {
        name: "Home",
        components: {NavMenu}
    }
</script>

<style scoped>
  .nav {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }
</style>
