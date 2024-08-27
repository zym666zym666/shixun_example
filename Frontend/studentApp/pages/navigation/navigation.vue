<template>
	<view class="content">
		<movable-area class="movable">
			<movable-view direction="all" :scale="true" :scale-min="1" :scale-max="3"
				style="width: 100%; height: 100%;">
				<image src="../../static/地图01.jpg" id="map"></image>
			</movable-view>
		</movable-area>
		<movable-area class="movable">
			<movable-view direction="all" :scale="true" :scale-min="1" :scale-max="3"
				style="width: 100%; height: 100%;">
				<image src="../../static/重大A区.jpg" id="A"></image>
			</movable-view>
		</movable-area>
	</view>
	<!-- 添加一个新的按钮用于跳转到在线地图服务 -->
	<button class="map-service-button" @click="openWebPage">CQU地图</button>
	<!-- 平安重大公众号 -->
	<button class="cqu-service-button" @click="openModal">预约进校</button>
	<div class="modal" v-if="isModalOpen">
		<div class="modal-content">
			<!-- 关闭按钮，点击时调用closeModal方法 -->
			<span class="close" @click="closeModal">&times;</span>
			<!-- 图片显示 -->
			<img :src="imageUrl" alt="Modal Content">
		</div>
	</div>
	<view class="my-sub-tabbar-wrapper">
		<custom-tab-bar direction="horizontal" :show-icon="true" :selected="selectedIndex"
			@onTabItemTap="tabbarTaped"></custom-tab-bar>
	</view>
</template>


<script>
	export default {
		data() {
			return {
				selectedIndex: 0,
				// 控制模态框显示的数据属性
				isModalOpen: false,
				// 图片的URL地址
				imageUrl: "../../static/平安重大.jpg"
			}
		},
		methods: {
			// 打开模态框的方法
			openModal() {
				this.isModalOpen = true;
			},
			// 关闭模态框的方法
			closeModal() {
				this.isModalOpen = false;
			},
			tabbarSelected(e) {

			},
			tabbarTaped(e) {
				console.log(e.pagePath);
				uni.switchTab({
					url: "/" + e.pagePath
				});
			},
			openWebPage() {
				const url = 'https://www.cqu.edu.cn/xysh/xydt.htm?map3';
				window.open(url, '_blank');
			}
		}
	}
</script>

<style>
	.content {
		height: 1450rpx;
		position: relative;
	}

	.movable {
		width: 80%;
		height: 600rpx;
		margin-left: 10%;
	}

	#map,
	#A {
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

	.map-service-button {
		position: absolute;
		bottom: 180rpx;
		/* 根据需要调整位置 */
		left: 30%;
		transform: translateX(-50%);
		padding: 10rpx 20rpx;
		font-size: 30rpx;
		color: #ffffff;
		background-color: #0073e6;
		border: none;
		font-weight: 'bold';
		border-radius: 5rpx;
		cursor: pointer;
		box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	}

	.cqu-service-button {
		position: absolute;
		bottom: 180rpx;
		/* 根据需要调整位置 */
		right: 10%;
		transform: translateX(-50%);
		padding: 10rpx 20rpx;
		font-size: 30rpx;
		color: #ffffff;
		background-color: #0073e6;
		border: none;
		font-weight: 'bold';
		border-radius: 5rpx;
		cursor: pointer;
		box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	}

	.modal {
		display: flex;
		justify-content: center;
		align-items: center;
		position: fixed;
		z-index: 1000;
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		overflow: auto;
		background-color: rgba(0, 0, 0, 0.5);
	}

	.modal img {
		max-width: 100%;
		/* 确保图片在模态框内部适应宽度 */
		max-height: 80vh;
		/* 确保图片在视口内适应高度 */
		display: block;
		/* 确保图片块级显示 */
		margin: 0 auto;
		/* 图片水平居中 */
	}


	.close {
		color: #ffffff;
		float: right;
		font-size: 40px;
		font-weight: bold;
		cursor: pointer;
		z-index: 1001;
		/* 确保关闭按钮位于最前 */
	}
</style>