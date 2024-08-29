<template>
	<view class="content">
		<view class="notice">
			<view class="title">
				<text>标题：{{notice.title}}</text>
			</view>
			<view class="date">
				<text>日期：{{notice.date}}</text>
			</view>
			<view class="msg-Content">
				<p>{{notice.title}}</p>
				<br>
				<text>{{notice.content}}</text>
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
				notice: [],
				selectedIndex: 1
			}
		},
		onLoad() {
			this.Disp();
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
			Disp() {
				const it = uni.getStorageSync("NOTICEID");
				uni.request({
					url: "http://127.0.0.1:8081/noticeid?id=" + it,
					method: "GET",
					dataType: "json",
					success: (res) => {
						if (res.data.code == 200) {
							this.notice = res.data.data;
						}
					}
				})

			},
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-wrap: wrap;
	}

	.notice {
		width: 95%;
		margin-left: 2.5%;
		height: 1350rpx;
		border: solid #c5c5c5 1rpx;
		border-radius: 20rpx;
		display: flex;
		flex-wrap: wrap;
		position: relative;
	}

	.title {
		width: 45%;
		margin-left: 10rpx;
		margin-top: 20rpx;
		height: 60rpx;
		border: solid #c5c5c5 1rpx;
		border-radius: 20rpx;
		position: absolute;
		white-space: nowrap;
		/* display: inline-block; */
		vertical-align: middle;
		overflow: hidden;
		text-overflow: ellipsis;
		max-width: 50%;
	}

	.date {
		width: 45%;
		margin-left: 350rpx;
		margin-top: 20rpx;
		height: 60rpx;
		border: solid #c5c5c5 1rpx;
		border-radius: 20rpx;
		position: absolute;
	}

	.title text {
		line-height: 60rpx;
		font-size: 25rpx;
		margin-left: 20rpx;
		white-space: nowrap;
		/* display: inline-block; */
		vertical-align: middle;
		overflow: hidden;
		text-overflow: ellipsis;
		max-width: 20%;
	}

	.date text {
		line-height: 60rpx;
		font-size: 25rpx;
		margin-left: 20rpx;
	}

	.msg-Content {
		width: 95%;
		margin-left: 2.5%;
		height: 1360rpx;

		position: absolute;
		margin-top: 100rpx;
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

	.msg-Content p {
		font-weight: bold;
		/* 加粗 */
		text-align: center;
		/* 居中 */
	}

	.msg-Content text {
		font-size: 40rpx;
	}
</style>