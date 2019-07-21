<template>
  <div class="app-container">

    <!-- 顶部 Header 区域 -->
    <mt-header fixed title="Vue项目"></mt-header>


    <!-- 中间的 路由 router-view 区域 -->
		<transition>
			<router-view></router-view>
		</transition>


    <!-- 底部 Tabbar 区域 -->
	  <mt-tabbar>

			  <mt-tab-item id="/home" v-model="selected">
				  <router-link  to="/home">
				  <img slot="icon" src="" alt="首页" style="width: 80px;height: 80px">
				  首页
				  </router-link>
			  </mt-tab-item>


			  <mt-tab-item id="/member">
				  <router-link  to="/member">
				  <img slot="icon" src="" alt="订单" style="width: 80px;height: 80px">
				  订单
				  </router-link>
			  </mt-tab-item>


			  <mt-tab-item id="购物车">
				  <router-link  to="/shopcar">
				  <img slot="icon" src="" alt="购物车" style="width: 80px;height: 80px">
				  购物车
				  </router-link>
			  </mt-tab-item>


			  <mt-tab-item id="搜索">
				  <router-link  to="/search">
				  <img slot="icon" src="" alt="搜索" style="width: 80px;height: 80px">
				  搜索
				  </router-link>
			  </mt-tab-item>
	  </mt-tabbar>

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
