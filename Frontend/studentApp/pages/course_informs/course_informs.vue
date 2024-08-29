<template>
	<view class="content">
		<view class="back-photo-box">
			<image clas="back-photo" src="../../static/系统升级.png"></image>
		</view>

		<view class="informs-box">
			<view>
				<image src="../../static/name1.png" class="name1"></image>
				<text class="course-name">课程名称：{{course[0].course_name}}</text>
			</view>
			<view>
				<image src="../../static/name2.png" class="name1"></image>
				<text class="course-name">课程老师：{{course[0].course_teacher}}</text>
			</view>
			<view>
				<image src="../../static/name3.png" class="name1"></image>
				<text class="course-name">课程时间：\n{{course[0].course_begin_time}}--{{course[0].course_end_time}}</text>
			</view>
			<view>
				<image src="../../static/name4.png" class="name1"></image>
				<text class="course-name">课程地点：{{course[0].course_place}}</text>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				course: "",
			}
		},
		//加载数据
		onLoad() {
			var that=this;
			var course_informs=uni.getStorageSync("course_informs");
			console.log(course_informs);
			var id=course_informs.courseId;
			uni.request({
				url: "http://127.0.0.1:8081/idSearch",
				data:{
					id:id
				},
				method: "POST",
				header: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				success(res) {
					console.log('数据加载成功');
					
					that.course=res.data.data;
					console.log(that.course[0].course_name);
					}
			})
		},

		methods: {

		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
	}

	.title-box {
		margin-top: 10rpx;
		width: 100%;
		height: 100rpx;
	}

	.logo {
		margin-top: 10rpx;
		margin-left: 50rpx;
		height: 175rpx;
		width: 175rpx;
		float: left;
	}

	.back-photo-box {
		width: 100%;
		height: 400rpx;
		margin-left: 8%;
	}

	.back-photo {
		width: 200rpx;
		height: 100rpx;
	}

	.teacher-name {
		margin-left: 5%;
		margin-top: 50rpx;
	}

	.informs-box {
		width: 100%;
		height: 300rpx;
	}

	.informs-box view {
		width:95%;
		margin-left:2.5%;
		margin-top: 50rpx;
		border-bottom: solid #dadada 2rpx;
	}

	.course-time {
		margin-left: 5%;
		margin-top: 50rpx;
	}

	.course-place {
		margin-left: 5%;
		margin-top: 50rpx;
	}

	.course-name {
		margin-left: 3%;
		align-items: center;
		/* text-align: center; */
		/* justify-content: center; */
		margin-top: 0rpx;
		font-size: 50rpx;
		font-weight: 500;
	}

	.name1 {
		width: 70rpx;
		height: 70rpx;
	}

	.informs-box view {
		display: flex;
		/* 启用Flexbox布局 */
		align-items: center;
		/* 垂直居中对齐 */
		/* justify-content: center; */
		/* 水平居中对齐 */
		height: 120px;
		/* 容器的高度，根据需要调整 */
	}
</style>