<template>
	<view class="content">
		<view class="header">
			<text>我的消息</text>
		</view>
		<view class="message-box">
			<view class="msg-cntent" v-for="notice in notices" :key="notice.id" @click="Tospecific(notice.id)">
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
				notices: [],
				selectedIndex:0
			}
		},
		onLoad() {
			this.Autodisp();
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
			Autodisp() {
				uni.request({
					url: "http://127.0.0.1:8081/notice",
					method: "GET",
					dataType: "json",
					success: (res) => {
						this.notices = res.data.data;
					}
				})
			},
			Tospecific(id) {
				uni.setStorageSync("NOTICEID", id),
					uni.navigateTo({
						url: "/pages/specificinformation/specificinformation",
					})
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-wrap: wrap;
	}

	.header {
		width: 90%;
		margin-left: 5%;
		height: 100rpx;
		border: solid #dfdfdf 1rpx;
		border-radius: 20rpx;
		background: #acacac;
	}

	.header text {
		font-size: 50rpx;
		margin-left: 225rpx;
		line-height: 100rpx;
	}

	/* .message-box {
		width: 95%;
		height: 250rpx;
		background: #fff;
		margin-left: 2.5%;
		margin-right: 2.5%;
		margin-top: 15rpx;
		display: flex;
		flex-wrap: wrap;
	} */

	.msg-cntent {
		width: 100%;
		height: 90rpx;
		border-bottom: solid #d5d5d5 1rpx;
		margin-left: 20rpx;
		margin-top: 20rpx;
	}

	.msg-title {
		font-size: 35rpx;
		line-height: 70rpx;
		white-space: nowrap;
		display: inline-block;
		vertical-align: middle;
		overflow: hidden;
		text-overflow: ellipsis;
		max-width: 50%;
	}

	.msg-date {
		display: inline-block;
		float: right;
		margin-top: 30rpx;
		margin-right: 40rpx;
	}
</style>