<template>
	<view class="content">
		<view class="notice">
			<view class="title">
				<text>{{notice.title}}</text>
			</view>
			<view class="date">
				<text>{{notice.date}}</text>
			</view>
			<view class="msg-content">
				{{notice.content}}
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				notice:[],
			}
		},
		onLoad() {
			this.AtuoDisp();
		},
		methods: {
			AutoDisp()
			{
				const it=uni.getStorageSync("NOTICEID");
				uni.request({
					url:"http://127.0.0.1:8088/noticeid?id="+it,
					method:"GET",
					dataType:"json",
					success: (res) => {
						if(res.data.code==200)
						{
							this.notice=res.data.data;
						}
						
					}
				})
			}
		}
	}
</script>

<style>
.content{
	display: flex;
	flex-wrap: wrap;
}
.notice{
	width: 95%;
	margin-left: 2.5%;
	height: 1500rpx;
	border:solid #c5c5c5 1rpx;
	border-radius: 20rpx;
    display: flex;
	flex-wrap: wrap;
	position: relative;
}
.title{
	width: 40%;
	margin-left: 20rpx;
	margin-top: 20rpx;
	height: 60rpx;
	border:solid #c5c5c5 1rpx;
	border-radius: 20rpx;
}
.date{
	width: 40%;
	margin-left: 100rpx;
	margin-top: 20rpx;
	height: 60rpx;
	border:solid #c5c5c5 1rpx;
	border-radius: 20rpx;
}
.title text{
	line-height: 60rpx;
	font-size: 25rpx;
	
}
.date text{
	line-height: 60rpx;
	font-size: 25rpx;
}
.msg-content{
	width: 95%;
	margin-left: 2.5%;
	height: 1360rpx;

	position: absolute;
	margin-top: 100rpx;
}
</style>
