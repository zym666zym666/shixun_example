<template>
	<view class="content">
		<view class="banner">
			<swiper class="swiper" indicator-dots indicator-active-color="#fff" circular autoplay>
				<swiper-item v-for="item in rotation" :key="item.id">
					<image :src="item.url"></image>
				</swiper-item>
			</swiper>
		</view>
		<view class="nav">
			<view class="nav-item" @click="toData()">
				<image src="../../static/数据采集.png"></image>
				<text>数据采集</text>
			</view>
			<view class="nav-item" @click="toService()">
				<image src="../../static/咨询.png"></image>
				<text>在线咨询</text>
			</view>
			<view class="nav-item" @click="toCourse()">
				<image src="../../static/课程.png"></image>
				<text>我的课程</text>
			</view>
			<view class="nav-item">
				<image src="../../static/预约.png"></image>
				<text>体检预约</text>
			</view>
			<view class="nav-item">
				<image src="../../static/导航图标.png"></image>
				<text>学院导航</text>
			</view>
			<view class="nav-item">
				<image src="../../static/报到流程.png"></image>
				<text>报到流程</text>
			</view>
			<view class="nav-item" @click="toDorm()">
				<image src="../../static/宿舍管理-分配宿舍.png"></image>
				<text>自选宿舍</text>
			</view>
			<view class="nav-item">
				<image src="../../static/院校.png"></image>
				<text>学院介绍</text>
			</view>
		</view>
		<text class="message">最新消息</text>
		<view class="message-box">
			<view class="msg-content" v-for="notice in notices" :key="notice.id" @click="Tos(notice.id)">
				<text class="msg-title">{{notice.title}}</text>
				<text class="msg-date">{{notice.date}}</text>
			</view>
			
		</view>
	</view>

</template>

<script>
	export default {
		data() {
			return {
				rotation: [{
					id: 1,
					url: "../../static/banner1.png"
				}, {
					id: 2,
					url: "../../static/banner2.png"
				}, {
					id: 3,
					url: "../../static/banner3.png"
				}],
				tel: "",
				notices:[],
			}
		},
		onLoad()
		{
			this.Autodisp();
		},
		methods: {
		
			Autodisp()
			{
				uni.request({
					url:"http://127.0.0.1:8081/noticelimit",
					method:"GET",
					dataType:"json",
					success: (res) => {
						this.notices=res.data.data;
					},
					
				}),
				console.log(this.notices);
			},
			Tos(id)
			{
				uni.setStorageSync("NOTICEID",id),
				uni.navigateTo({
					url:"/pages/specificinformation/specificinformation",
				})
			},
			tabbarSelected(e) {

			},
			tabbarTaped(e) {
				console.log(e.pagePath)
				uni.switchTab({
					url: "/" + e.pagePath
				})
			},
			toData: function() {
				uni.navigateTo({
					url: "/pages/datasel/datasel"
				})
			},
			toDorm: function() {
				uni.navigateTo({
					url: "/pages/dorm/dorm"
				})
			},
			toCourse: function() {
				uni.navigateTo({
					url: "/pages/course/course"
				})
			},
			toService:function()
			{
				uni.navigateTo({
					url:"/pages/onlineService/onlineService"
				})
			}
		},
		created() {
			// 在组件创建时，将 Vuex store 中的值赋给组件的 data
			this.tel = this.$store.getters.tel;
		},
		Autodisp()
		{
			uni.request({
				url:"http://127.0.0.1:8081/notice",
				method:"GET",
				dataType:"json",
				success: (res) => {
					this.notices=res.data.data;
				}
			})
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		justify-content: center;
	}

	.banner {
		width: 100%;
		height: 300rpx;
	}

	.swiper-item {
		width: 100%;
		height: 100%;
	}

	.banner image {
		width: 100%;
		height: 100%;
	}

	.nav {
		width: 95%;
		height: 400rpx;
		background: #fff;
		margin-left: 2.5%;
		margin-right: 2.5%;
		margin-top: 15rpx;
		display: flex;
		flex-wrap: wrap;
	}

	.nav-item {
		width: 155rpx;
		height: 155rpx;
		margin: 10rpx;
	}

	.nav-item image {
		width: 90rpx;
		height: 90rpx;
		margin-left: 20%;
	}

	.nav-item text {
		display: inline-block;
		font-size: 30rpx;
		margin-left: 10%;
	}

	.message {
		display: inline-block;
		font-size: 30rpx;
		font-weight: bold;
		margin: 20rpx;
	}

	.message-box {
		width: 95%;
		height: 600rpx;
		background: #fff;
		margin-left: 2.5%;
		margin-right: 2.5%;
		margin-top: 15rpx;
		
	}

	.msg-content {
		margin-top: 10rpx;
		width: 100%;
		height: 90rpx;
		border-bottom: solid #d5d5d5 1rpx;
		
	}

	.msg-title {
		font-size: 35rpx;
		line-height: 90rpx;
	}

	.msg-date {
		display: inline-block;
		float: right;
		margin-top: 20rpx;
		margin-right: 20rpx;
	}

	/deep/.uni-tabbar__icon img {
		width: 50rpx;
		height: 50rpx;
	}

	/deep/.uni-tabbar__bd {
		margin: 5rpx;
	}
</style>