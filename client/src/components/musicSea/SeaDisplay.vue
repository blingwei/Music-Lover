<template>
  <div class="infinite-list-wrapper" style="overflow:auto">
    <el-card
      class="list"
      v-infinite-scroll="load"
      infinite-scroll-disabled="disabled" v-for="i in count" :key="index">
       {{ i }}
    </el-card>
    <p v-if="loading">加载中...</p>
    <p v-if="noMore">没有更多了</p>
  </div>
</template>

<script>
    export default {
        data () {
            return {
                count: 10,
                loading: false
            }
        },
        computed: {
            noMore () {
                return this.count >= 20
            },
            disabled () {
                return this.loading || this.noMore
            }
        },
        methods: {
            load () {
                this.loading = true
                setTimeout(() => {
                    this.count += 2
                    this.loading = false
                }, 2000)
            }
        }
    }
</script>

<style scoped>

</style>
