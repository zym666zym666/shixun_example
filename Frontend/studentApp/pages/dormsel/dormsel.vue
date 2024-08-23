<template>
	<view class="content">
		<view class="box">
			<view v-for="item in info" :key="item.id" class="inner_box">
				<image :src="item.isOccupied ? '../../static/人像2.png' : '../../static/待入住.png'"></image>
				<view class="right">
					<view class="top">
						<text :id="item.isOccupied ? 'one' : 'two'">{{ getBedText(item) }}</text>
						<view :class="item.isOccupied ? 'zhu' : 'kong'">
							<text>{{ item.isOccupied ? '已入住' : '待入住' }}</text>
						</view>
					</view>
					<view class="text-box" v-if="item.isOccupied">
						<text id="one">手机：{{item.tel}}</text>
						<text id="one">专业：{{item.major}}</text>
						<text id="one">籍贯：{{item.addr}}</text>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				info: [{
						id: 1,
						name: "张琪",
						tel: "13456",
						major: "计算机科学与技术",
						addr: "常德市",
						isOccupied: true
					},
					{
						// 假设这是2号床位的数据，待入住
						id: 2,
						name: "待入住",
						tel: "",
						major: "",
						addr: "",
						isOccupied: false
					},
					{
						id: 1,
						name: "张琪",
						tel: "13456",
						major: "计算机科学与技术",
						addr: "常德市",
						isOccupied: true
					},
					{
						id: 1,
						name: "张琪",
						tel: "13456",
						major: "计算机科学与技术",
						addr: "常德市",
						isOccupied: true
					}
				]
			}
		},
		methods: {
			getBedText(item) {
				return item.isOccupied ? `${item.id}号床位 ${item.name}` : `${item.id}号床位 待入住`;
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
	}

	.box {
		display: flex;
		flex-direction: column;
		border-radius: 10rpx;
		width: 95%;
		height: 1200rpx;
		margin-left: 2.5%;
		margin-right: 2.5%;
		margin-top: 15rpx;
		box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.5);
	}

	.inner_box {
		display: flex;
		width: 100%;
		border: solid #dadada 2rpx;
		height: 25%;
	}

	.inner_box image {
		width: 200rpx;
		height: 90%;
		margin-left: 20rpx;
		margin-top: 16rpx;
		margin-right: 20rpx;
	}

	.right {
		display: flex;
		flex-direction: column;
		width: 69%;
		height: 100%;
	}

	.top {
		display: flex;
		margin-top: 20rpx;
		margin-right: 20rpx;
		width: 100%;
		height: 60rpx;
	}

	.text-box {
		display: flex;
		flex-direction: column;
		width: 100%;
		height: 100%;
	}

	#one {
		margin-top: 25rpx;
		display: inline-block;
		font-weight: bold;
		font-size: 33rpx;
		color: #999999;
	}

	#two {
		margin-top: 23%;
		margin-left: 30rpx;
		display: inline-block;
		font-weight: bold;
		font-size: 33rpx;
		color: #999999;
	}

	.zhu {
		display: flex;
		justify-content: center;
		margin-left: auto;
		margin-right: 10rpx;
		margin-top: 0rpx;
		width: 150rpx;
		height: 60rpx;
		background-color: greenyellow;
		text-align: center;
	}

	.zhu text {
		margin-top: 5%;
		color: #fff;
		font-weight: bold;
	}

	.kong {
		display: flex;
		justify-content: center;
		margin-left: auto;
		margin-right: 10rpx;
		margin-top: 0rpx;
		width: 150rpx;
		height: 60rpx;
		background-color: burlywood;
		text-align: center;
	}

	.kong text {
		margin-top: 5%;
		color: #fff;
		font-weight: bold;
	}
</style>