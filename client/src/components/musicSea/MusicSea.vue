<template>
  <div class="content">
    <SeaDisplay  class="seaDisplay"></SeaDisplay>
  </div>
</template>

<script>
  import SeaDisplay from "./SeaDisplay";
    export default {
        name: "MusicSea",
        components: {SeaDisplay}
    }
</script>

<style scoped>
  .content{
    top: 50px;
    width: 60%;
    margin-left: 20%;
  }
  .seaDisplay {

  }
</style>
