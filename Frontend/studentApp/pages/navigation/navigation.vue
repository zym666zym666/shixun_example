<template>
	<view class="content">
		<movable-area class="movable">
			<movable-view direction="all" :scale="true" :scale-min="1" :scale-max="3" style="width: 100%; height: 100%;">
				<image src="../../static/虎溪地图.jpg" id="map"></image>
			</movable-view>
		</movable-area>
		<movable-area class="movable">
			<movable-view direction="all" :scale="true" :scale-min="1" :scale-max="3" style="width: 100%; height: 100%;">
				<image src="../../static/重大A区.jpg" id="A"></image>
			</movable-view>
		</movable-area>
	</view>
	<view class="my-sub-tabbar-wrapper">
		<custom-tab-bar direction="horizontal" :show-icon="true" :selected="selectedIndex"
			@onTabItemTap="tabbarTaped"></custom-tab-bar>
	</view>
</template>


<script>
	export default {
		data() {
			return {

			}
		},
		methods: {

		}
	}
</script>

<style>
	.content {
		background-color: #ff557f;
		height: 1450rpx;
		position: relative;
	}

	.movable {
		width: 80%;
		height: 600rpx;
		margin-left: 10%;
	}

	#map, #A {
		width: 100%;
		margin-top: 80rpx;
		height: 100%;
	}

	.my-sub-tabbar-wrapper {
		position: absolute;
		bottom: 0;
		left: 0;
		width: 100%;
		height: 96rpx;
	}

	/deep/.uni-tabbar__icon img {
		width: 50rpx;
		height: 50rpx;
	}

	/deep/.uni-tabbar__bd {
		margin: 5rpx;
	}
</style>
