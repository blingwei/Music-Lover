<template>
  <div>
    <el-carousel :interval="4000" type="card" height="400px" style="margin-top: 100px">
      <el-carousel-item v-for="(item , index) in recommends" :key="index">
        <h3 class="medium" @click="display(item.essaySongId)">
          <img :src="item.url" :fit="fit" height="400px" width="100%"/>
        </h3>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>


<script>
  export default {
    name: "Index",
    data() {
      return {
        recommends: [],

      }
    },
    mounted() {
      this.init();
    },
    methods: {
      init() {
        this.$axios.get("getAllRecommend").then(res => {
          if (res && res.status === 200) {
            this.recommends = res.data.data;
            // this.recommends.forEach(item => {
            //    item.url = "require('"+ item.url + "')"
            // });

            this.recommends.forEach(item => {
              console.log(item.url)
            });
          }
        })
      },
      display(id) {
        let routeData = this.$router.resolve({
          path: '/essayDisplay',
          query: {id: id}
        });
        window.open(routeData.href, '_blank');
      },
    }

  }
</script>

<style scoped>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 400px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
