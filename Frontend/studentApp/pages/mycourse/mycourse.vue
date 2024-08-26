<template>
	<view class="content">
		<view class="header">
			<text>我的课程</text>
			
		</view>
		<view class="top">
			<text>课程名</text>
			<text>授课教师</text>
			<text>授课地点</text>
		</view>
		<view class="course_box" v-for="(course,index) in Data":key="index">
			<!-- 课程名 -->
			<view class="course_box1">
				<text>{{course.courseName}}</text>
			</view>
			<!-- 授课老师 -->
			<view class="course_box1">
				<text>{{course.courseTeacher}}</text>
			</view>
			<!-- 授课地点 -->
			<view class="course_box1">
				<text>{{course.coursePlace}}</text>
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
				Data:[],
			}
		},
		onLoad() {
			this.Autodisp();
		},
		methods: {
			Autodisp()
				{
					
					const it=this.$store.getters.tel;//学生电话号码
					console.log(it);
					uni.request({
						url:"http://127.0.0.1:8088/Mycourse?phone="+it,
						method:'GET',
						dataType:"json",
						success: (res) => {
							this.Data=res.data.data;
						}
					})
				},
				Refresh()
				{
					this.Autodisp();
				}
		}
	}
</script>

<style>
.content{
	display: flex;
	flex-direction: column;
}
.header{
	width: 100%;
	height: 100rpx;
	border-bottom: solid #d5d5d5 1rpx;
}
.header text{
	line-height: 90rpx;
	margin-left: 300rpx;
	
}
.top{
	width: 100%;
	border-bottom: solid #d5d5d5 1rpx;
	height: 100rpx;
}
.top text{
	line-height: 80rpx;
	margin-left: 100rpx;
}
.course_box{
		display: flex;
		text-align: center;
		width: 100%;
		margin-top: 10rpx;
		height: 120rpx;
	}
.course_box1{
		width: 25%;
		height: 120rpx;
		margin-left: 40rpx;
		border-bottom: #d5d5d5 solid 1rpx;
	}
.course_box1 text{
		line-height: 100rpx;
		font-size: 25rpx;
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
