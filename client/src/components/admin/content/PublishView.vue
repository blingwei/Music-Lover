<template>
  <div>
    <el-dialog
      title="查看"
      :visible.sync="dialogFormVisible"
      @close="clear"
      width="50%">
      <MusicPlay ref="music"/>
      <div>
        <div class="content" v-html="content"/>
      </div>

    </el-dialog>
  </div>
</template>

<script>
  import MusicPlay from "../../essayWithSongDisplay/MusicPlay";

  export default {
    name: "PublishView",
    components: {MusicPlay},
    data() {
      return {
        title: "",
        introduce: "",
        content: "",
        publishId: "",
        dialogFormVisible: false
      }
    },
    methods: {
      clear() {
        this.dialogFormVisible = false;
        this.$refs.music.pause()
      },
      init(publishId) {
        let obj = this;
        console.log(publishId);
        this.$axios.get('/admin/assessor/viewPublish',
          {
            params: {
              id: publishId
            }
          }).then(res => {
            if (res && res.status === 200) {
              obj.dialogFormVisible = true;
              obj.content = res.data.data.content;
              this.$root.$emit("initSong", res.data.data.song)
            }
          }
        );

      }
    },
    mounted() {
      this.$root.$on("initView", value => {
        this.init(value)
      });
    }

  }
</script>

<style scoped>
  .content {
    text-align: left;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    margin-top: 10px;
  }
</style>