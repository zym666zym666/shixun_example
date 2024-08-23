<template>
	<view class="content">
		<view v-for="item in items" :key="item.id" class="box">
			<image :src="item.url"></image>
			<view class="text-box">
				<text id="one">{{item.course}}</text>
				<text id="two">授课教师：{{item.teacher}}</text>
				<text id="two">开课时间：{{item.Start}}至{{item.end}}</text>
				<text id="two">授课地点：{{item.place}}</text>
			</view>
		</view>
		<view class="my-sub-tabbar-wrapper">
			<custom-tab-bar direction="horizontal" :show-icon="true" :selected="selectedIndex" @onTabItemTap="tabbarTaped"></custom-tab-bar>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				items:[{
					id:1,
					course:"微信小程序开发",
					url:"../../static/微信小程序开发.png",
					teacher:"熊慧志",
					Start:"2024/8/1",
					end:"2024/12/31",
					place:"实训室1"
				},
				{
					id:2,
					course:"Java面向对象编程",
					url:"../../static/Java面向对象编程.png",
					teacher:"熊慧志",
					Start:"2024/8/1",
					end:"2024/12/31",
					place:"实训室2"
				},
				{
					id:3,
					course:"MySQL数据库基础",
					url:"../../static/MySQL数据库基础.png",
					teacher:"熊慧志",
					Start:"2024/8/1",
					end:"2024/12/31",
					place:"实训室1"
				},
				{
					id:4,
					course:"JavaWeb应用开发",
					url:"../../static/JavaWeb应用开发.png",
					teacher:"熊慧志",
					Start:"2024/8/1",
					end:"2024/12/31",
					place:"实训室1"
				},
				{
					id:5,
					course:"前端开发基础",
					url:"../../static/前端开发基础.png",
					teacher:"熊慧志",
					Start:"2024/8/1",
					end:"2024/12/31",
					place:"实训室1"
				},
				{
					id:6,
					course:"SpringBoot开发",
					url:"../../static/SpringBoot开发.png",
					teacher:"熊慧志",
					Start:"2024/8/1",
					end:"2024/12/31",
					place:"实训室1"
				}]
			}
		},
		methods: {
			tabbarSelected(e){
				
			},
			tabbarTaped(e){
				console.log(e.pagePath)
				uni.switchTab({
					url:"/"+e.pagePath
				})
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
	}
	.box{
		display: flex;
		border-radius: 10rpx;
		width: 95%;
		height: 220rpx;
		margin-left: 2.5%;
		margin-right: 2.5%;
		margin-top: 10rpx;
		border: solid #7d7d7d 2rpx;
	}
	.box image{
		width: 300rpx;
		height: 90%;
		margin-left: 10rpx;
		margin-top: 10rpx;
		margin-right: 20rpx;
	}
	.text-box{
		display: flex;
		flex-direction: column;
	}
	#one{
		font-weight: bold;
		margin-top: 15rpx;
	}
	#two{
		font-size: 24rpx;
		margin-top: 18rpx;
	}
	.my-sub-tabbar-wrapper{
		position: absolute;
		bottom: 0;
		left: 0;
		width: 100%;
		height: 96rpx;
	}
	/deep/.uni-tabbar__icon img{
		width: 50rpx;
		height: 50rpx;
	}
	/deep/.uni-tabbar__bd{
		margin: 5rpx;
	}
</style>
