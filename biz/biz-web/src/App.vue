<template>
  <div class="app-container">

    <!-- 顶部 Header 区域 -->
    <mt-header fixed title="Vue项目"></mt-header>


    <!-- 中间的 路由 router-view 区域 -->
		<transition>
			<router-view></router-view>
		</transition>


    <!-- 底部 Tabbar 区域 -->
	  <!--<mt-tabbar>-->
			  <!--<mt-tab-item id="/home">-->
				  <!--<router-link  to="/home">-->
				  <!--<img slot="icon" src="" alt="首页" style="width: 80px;height: 80px">-->
				  <!--首页-->
				  <!--</router-link>-->
			  <!--</mt-tab-item>-->

			  <!--<mt-tab-item id="/member">-->
				  <!--<router-link  to="/member">-->
				  <!--<img slot="icon" src="" alt="订单" style="width: 80px;height: 80px">-->
				  <!--订单-->
				  <!--</router-link>-->
			  <!--</mt-tab-item>-->


			  <!--<mt-tab-item id="购物车">-->
				  <!--<router-link  to="/shopcar">-->
				  <!--<img slot="icon" src="" alt="购物车" style="width: 80px;height: 80px">-->
				  <!--购物车-->
				  <!--</router-link>-->
			  <!--</mt-tab-item>-->


			  <!--<mt-tab-item id="搜索">-->
				  <!--<router-link  to="/search">-->
				  <!--<img slot="icon" src="" alt="搜索" style="width: 80px;height: 80px">-->
				  <!--搜索-->
				  <!--</router-link>-->
			  <!--</mt-tab-item>-->
	  <!--</mt-tabbar>-->
	  <nav class="mui-bar mui-bar-tab">
		  <router-link class="mui-tab-item" to="/home">
			  <span class="mui-icon mui-icon-home"></span>
			  <span class="mui-tab-label">首页</span>
		  </router-link>
		  <router-link class="mui-tab-item" to="/member">
			  <span class="mui-icon mui-icon-contact"></span>
			  <span class="mui-tab-label">会员</span>
		  </router-link>
		  <router-link class="mui-tab-item" to="/shopcar">
				<span class="mui-icon mui-icon-extra mui-icon-extra-cart">
					<span class="mui-badge">0</span>
				</span>
			  <span class="mui-tab-label">购物车</span>
		  </router-link>
		  <router-link class="mui-tab-item" to="/search">
			  <span class="mui-icon mui-icon-search"></span>
			  <span class="mui-tab-label">搜索</span>
		  </router-link>
	  </nav>

  </div>
</template>

<script>
</script>


<style lang="scss" scoped>
.app-container {
  padding-top: 40px;
	padding-bottom: 50px;
  overflow-x: hidden;
}

.v-enter {
  opacity: 0;
  transform: translateX(100%);
}

.v-leave-to {
  opacity: 0;
  transform: translateX(-100%);
  position: absolute;
}

.v-enter-active,
.v-leave-active {
  transition: all 0.5s ease;
}
</style>
