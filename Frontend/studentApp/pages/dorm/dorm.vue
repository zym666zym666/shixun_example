<template>
	<view class="content">
		<view class="banner">
			<image src='../../static/宿舍.png'></image>
		</view>
		<view class="box">
			<view v-for="item in info" :key="item.id" class="inner_box" @click="toSel(item)">
				<image src='../../static/宿舍.png'></image>
				<view class="text_box">
					<text id="one">房间号：{{item.id}}</text>
					<text id="two">费用标准：￥{{item.cost}}/学年</text>
					<text id="two">宿舍规格：标准{{item.ren}}人间</text>
					<text id="two">宿舍设施：{{item.equipment}}</text>
				</view>
			</view>
		</view>
		<view class="my-sub-tabbar-wrapper">
			<custom-tab-bar direction="horizontal" :show-icon="true" :selected="selectedIndex"
				@onTabItemTap="tabbarTaped"></custom-tab-bar>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				info: []
			};
		},
		created() {
			// 发起请求从后端获取房间号
			this.fetchRoomInfo();
		},
		methods: {
			tabbarSelected(e) {

			},
			tabbarTaped(e) {
				console.log(e.pagePath);
				uni.switchTab({
					url: "/" + e.pagePath
				});
			},
			toSel(item) {
				uni.setStorageSync("dormId", item.id);
				uni.navigateTo({
					url: '/pages/dormsel/dormsel'
				});
			},
			fetchRoomInfo() {
				axios.get('http://127.0.0.1:8081/getDormId') // 假设只获取房间号
					.then(response => {
						if (response.data.code === 200) {
							// 遍历后端返回的房间号，填入固定的 cost, ren, equipment 信息
							this.info = response.data.data.map(id => ({
								id: id, // 动态的房间号
								cost: '1000', // 固定的费用
								ren: '4', // 固定的宿舍规格
								equipment: '独立卫浴，空调' // 固定的宿舍设施
							}));
						} else {
							console.error('查询失败:', response.data.msg);
						}
					})
					.catch(error => {
						console.error('请求失败:', error);
					});
			}
		}
	};
</script>


<style>
	.content {
		display: flex;
		flex-direction: column;
	}

	.banner {
		width: 100%;
		height: 400rpx;
	}

	.banner image {
		width: 100%;
		height: 100%;
	}

	.box {
		display: flex;
	    overflow-y: auto;
		flex-direction: column;
		border-radius: 30rpx;
		width: 90%;
		height: 1000rpx;
		margin-left: 5%;
		margin-right: 5%;
		margin-top: 15rpx;
		box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.5);
	}

	.inner_box {
		display: flex;
		margin-left: 2.5%;
		width: 95%;
		border-bottom: solid #dadada 2rpx;
		margin-top: 10rpx;
		height: 23%;
	}

	.inner_box image {
		margin-top: 3%;
		margin-right: 20rpx;
		width: 40%;
		height: 80%;
	}

	.text_box {
		margin-top: 3%;
		display: flex;
		flex-direction: column;
	}

	#one {
		display: inline-block;
		font-weight: bold;
		margin-top: 0%;
		font-size: 35rpx;
	}

	#two {
		display: inline-block;
		color: #999999;
		margin-top: 5rpx;
		font-size: 28rpx;
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