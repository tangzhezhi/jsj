<template>
      <div>
        <!-- 轮播图区域 -->
          <mt-swipe :auto="4000">
              <!-- 在组件中，使用v-for循环的话，一定要使用 key -->
              <mt-swipe-item v-for="item in lunbotuList" :key="item.url">
                  <img :src="item.img" alt="">
              </mt-swipe-item>
          </mt-swipe>

          <div>
              <div class="row">
                  <div class="col-xs-4">
                      <router-link to="/home/newslist"  style="padding: 10px">
                          <img src="../../images/menu1.png" alt="" style="width:80px;height: 80px">
                          <div class="mui-media-body">新闻资讯</div>
                      </router-link>
                  </div>
                  <div class="col-xs-4"  style="padding: 10px">
                      <router-link to="/home/newslist">
                          <img src="../../images/menu2.png" alt="" style="width:80px;height: 80px">
                          <div class="mui-media-body">图片分享</div>
                      </router-link>
                  </div>
                  <div class="col-xs-4"  style="padding: 10px">
                      <router-link to="/home/newslist">
                          <img src="../../images/menu3.png" alt="" style="width:80px;height: 80px">
                          <div class="mui-media-body">商品购买</div>
                      </router-link>
                  </div>
              </div>
              <div class="row">
                  <div class="col-xs-4"  style="padding: 10px">
                      <router-link to="/home/newslist" >
                          <img src="../../images/menu4.png" alt="" style="width:80px;height: 80px">
                          <div class="mui-media-body">留言反馈</div>
                      </router-link>
                  </div>
                  <div class="col-xs-4"  style="padding: 10px">
                      <router-link to="/home/newslist">
                          <img src="../../images/menu5.png" alt="" style="width:80px;height: 80px">
                          <div class="mui-media-body">视频专区</div>
                      </router-link>
                  </div>
                  <div class="col-xs-4" style="padding: 10px">
                      <router-link to="/home/newslist">
                          <img src="../../images/menu6.png" alt="" style="width:80px;height: 80px">
                          <div class="mui-media-body">联系我们</div>
                      </router-link>
                  </div>
              </div>
          </div>
      </div>
    <!--<ul class="mui-table-view mui-grid-view mui-grid-9">-->
      <!--<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">-->
        <!--<router-link to="/home/newslist">-->
              <!--<img src="../../images/menu1.png" alt="">-->
              <!--<div class="mui-media-body">新闻资讯</div></router-link></li>-->
      <!--<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a href="#">-->
              <!--<img src="../../images/menu2.png" alt="">-->
              <!--<div class="mui-media-body">图片分享</div></a></li>-->
      <!--<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a href="#">-->
              <!--<img src="../../images/menu3.png" alt="">-->
              <!--<div class="mui-media-body">商品购买</div></a></li>-->
      <!--<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a href="#">-->
              <!--<img src="../../images/menu4.png" alt="">-->
              <!--<div class="mui-media-body">留言反馈</div></a></li>-->
      <!--<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a href="#">-->
              <!--<img src="../../images/menu5.png" alt="">-->
              <!--<div class="mui-media-body">视频专区</div></a></li>-->
      <!--<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a href="#">-->
              <!--<img src="../../images/menu6.png" alt="">-->
              <!--<div class="mui-media-body">联系我们</div></a></li>-->
  <!--</ul> -->
</template>

<script>
import { Toast } from "mint-ui";



export default {
  data() {
    return {
      lunbotuList: [

      ] // 保存轮播图的数组
    };
  },
  created() {
    this.getLunbotu();
  },
  methods: {
    getLunbotu() {
      // 获取轮播图数据的方法
      this.$http.get("api/getlunbo").then(result => {
        console.log(result);
        if (result.status==200) {
          // 成功了
            this.lunbotuList = result.body;
        } else {
          // 失败的
          Toast("加载轮播图失败。。。");
        }
      });
    }
  }
};
</script>

<style lang="scss" scoped>

    .mint-swipe {
        height: 200px;

        .mint-swipe-item {
            &:nth-child(1) {
                background-color: red;
            }
            &:nth-child(2) {
                background-color: blue;
            }
            &:nth-child(3) {
                background-color: cyan;
            }

            img {
                width: 100%;
                height: 100%;
            }
        }
    }
</style>
