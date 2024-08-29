<template>
	<view class="content">
		<view class="top">
			<button class="my_course" @click="getToMycourse()">我的课程</button>
			<view class="refresh">
				<!-- 刷新按钮 -->
				<button @click="Refresh()">刷新</button>
			</view>
		</view>
		<!-- 头部提示栏 -->
		<view class="header">
			<text>课程名</text>
			<text>授课教师</text>
			<text>选课人数</text>
			<text>操作</text>
		</view>

		<view class="course_box" v-for="(course,index) in courses" :key="index">
			<!-- 课程名 -->
			<view class="course_box1">
				<text>{{course.courseName}}</text>
			</view>
			<!-- 授课老师 -->
			<view class="course_box1">
				<text>{{course.courseTeacher}}</text>
			</view>
			<!-- 选课人数 -->
			<view class="course_box1">
				<text>{{course.courseNumber}}</text>/<text>{{course.courseCapacity}}</text>
			</view>
			<!-- 操作 -->
			<view class="course_box1">
				<!-- 选课按钮 -->
				<button class="course_selection" @click="CourseSelect(course)">选课</button>
				<!-- 退课按钮 -->
				<button class="drop_course" @click="CourseDrop(course)">退课</button>
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
				courses: [],
				selectedIndex:0
			};
		},
		//每次加载页面时,自动调用该函数一次
		onLoad() {
			this.AutoDisplay();
		},

		methods: {
			AutoDisplay() {
				uni.request({
					url: "http://127.0.0.1:8081/course",
					method: "GET",
					dataType: "json",
					success: (res) => {
						this.courses = res.data.data;
					}
				})
			},
			//刷新功能
			Refresh() { //添加this,防止报错
				this.AutoDisplay();
			},
			tabbarSelected(e) {

			},
			tabbarTaped(e) {
				console.log(e.pagePath)
				uni.switchTab({
					url: "/" + e.pagePath
				})
			},
			//选课功能
			CourseSelect(course) {

				const item = this.$store.getters.tel;
				console.log(item);
				console.log(course.courseId);
				uni.request({
					url: "http://127.0.0.1:8081/SelectCourse?phone=" + item + "&courseId=" + course.courseId,
					method: "POST",


					success: (res) => {

						// console.log(res);
						//选课成功
						if (res.data.code == 200) {
							if (res.data.data == 1) {
								uni.showToast({
									icon: "success",
									title: "选课成功"
								})
								this.Refresh();
							} else if(res.data.data==2){
								uni.showToast({
									icon: "error",
									title: "课程已满，请选择其他课程"
								})
								this.Refresh();
							}
							else
							{
								uni.showToast({
									icon: "error",
									title: "已选择该课程，请勿重复选课"
								})
								this.Refresh();
							}

						} else {
							uni.showToast({
								icon: "error",
								title: "选课失败"
							})
							this.Refresh();
						}
					},
					fail(ret) {
						uni.showToast({
							icon: "fail",
							title: "操作失败，请重试"
						})
						this.Refresh();
					}
				})
			},
			getToMycourse: function() {
				uni.navigateTo({
					url: "/pages/mycourse/mycourse"
				})
			},
			//退课功能
			CourseDrop(course) {
				const it = this.$store.getters.tel;
				console.log(it);
				uni.request({
					url: "http://127.0.0.1:8081/Dropcourse?phone=" + it + "&courseId=" + course.courseId,
					method: "POST",
					dataType: "json",
					success: (res) => {
						if (res.data.code == 200) {
							if (res.data.data == 1) {
								uni.showToast({
									icon: "success",
									title: "退课成功"
								})
								this.Refresh();
							} else {
								uni.showToast({
									icon: "error",
									title: "退课失败,请检查是否选择了该门课程"
								})
								this.Refresh();
							}
						} else {
							uni.showToast({
								icon: "error",
								title: "退课失败"
							})
							this.Refresh();
						}
					},
					fail(ret) {
						uni.showToast({
							icon: "fail",
							title: "操作失败，请重试"
						})
						this.Refresh();
					}
				})
			}

		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.refresh {
		width: 100%;
		height: 70rpx;

	}

	.refresh button {
		width: 18%;
		height: 60rpx;
		font-size: 25rpx;
		margin-top: 5%;
		margin-left: 590rpx;
		margin-top: 10rpx;
		position: absolute;
	}

	.header {
		display: flex;
		text-align: center;
		width: 95%;
		height: 70rpx;
		border-bottom: solid #d5d5d5 1rpx;
		background: #c3c3c3;
	}

	.header text {
		margin-top: 20rpx;
		margin-left: 60rpx;
	}

	.course_box {
		display: flex;
		text-align: center;
		width: 100%;
		margin-top: 10rpx;
		height: 120rpx;
	}

	.course_box1 {
		width: 25%;
		height: 120rpx;
		margin: 10rpx;
		border-bottom: #d5d5d5 solid 1rpx;
	}

	.course_box1 text {
		line-height: 100rpx;
		font-size: 25rpx;
	}

	.course_selection {
		width: 150rpx;
		height: 50rpx;
		font-size: 20rpx;
		background: greenyellow;
		margin-top: 5rpx;
	}

	.drop_course {
		width: 150rpx;
		height: 50rpx;
		font-size: 20rpx;
		background: #d5d5d5;
		margin-top: 8rpx;
	}

	.my_course {
		width: 25%;
		height: 60rpx;
		font-size: 25rpx;
		margin-left: 15px;
		margin-top: 10rpx;
		position: absolute;
	}

	.top {
		display: flex;
		flex-wrap: wrap;
		position: relative;
		width: 100%;
		height: 80rpx;
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