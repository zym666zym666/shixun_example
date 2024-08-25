<template>
	<view class="content">
		<view class="header">
			<text>我的消息</text>
		</view>
		<view class="message_box">
			
		</view>
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
.content{
	display: flex;
	flex-wrap: wrap;
}
.header{
	width: 90%;
	margin-left: 5%;
	height: 100rpx;
	border:solid #dfdfdf 1rpx;
	border-radius: 20rpx;
	background: #acacac;
}
.header text{
	font-size: 50rpx;
	margin-left: 225rpx;
	line-height: 100rpx;
}
.message_box{
	width: 95%;
	height:150rpx;
	margin-left: 2.5%;
	margin-top: 10rpx;
	border-bottom: solid #d4d4d4 1rpx;
	
}
</style>
