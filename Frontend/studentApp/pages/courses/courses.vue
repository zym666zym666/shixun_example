<template>
	<view class="content">
		<image class="logo" src="../../static/查看数据.png"></image>
		<view class="title2-box">
			<text class="title2">开设课程：</text>
		</view>
		<view class="course-allbox">
			<view class="course-box" v-if="course[0]">
				<text>{{course[0].courseName}}</text>
				<image src="../../static/coursephoto1.png" @click="course_informs(0)">
				</image>
			</view>
			<view class="course-box" v-if="course[1]">
				<text>{{course[1].courseName}}</text>
				<image src="../../static/coursephoto2.png" @click="course_informs(1)"></image>
			</view>
			<view class="course-box" v-if="course[2]">
				<text>{{course[2].courseName}}</text>
				<image src="../../static/coursephoto3.png" @click="course_informs(2)"></image>
			</view>
			<view  class="course-box" v-if="course[3]">
				<text>{{course[3].courseName}}</text>
				<image src="../../static/coursephoto4.png" @click="course_informs(3)"></image>

			</view>
		</view>

		<view class="page-change-box">
			<image class="prev-page" src="../../static/arrow-back-outline.png" @click="prev_page()"></image>
			<text class="page-num">{{course_page}}</text>
			<image class="next-page" src="../../static/icon-kfckfc.png" @click="next_page()"></image>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				course_page: 1,
				course: [],
				imurl1: "",
				imurl2: "",
				imurl3: "",
				imurl4: ""
			}
		},
		onLoad() {
			var that = this;
			//初始化页面
			uni.request({
				url: "http://127.0.0.1:8081/searchCourse?coursepage=" + this.course_page,
				method: "POST",
				dataType: "json",
				success(res) {
					console.log(res)
					uni.setStorageSync("courses", res.data.data);
					var courses = uni.getStorageSync("courses");
					that.course = res.data.data;
				}
			})


		},
		methods: {
			//课程信息跳转
			course_informs: function(order) {
				var course_informs = this.course[order];
				uni.setStorageSync("course_informs", course_informs);
				uni.navigateTo({
					url: "../course_informs/course_informs"
				})
			},
			//上一页的函数实现
			prev_page: function() {
				var that = this;
				if (this.course_page > 1) {
					this.course_page = this.course_page - 1;
					uni.request({
						url: "http://127.0.0.1:8081/searchCourse?coursepage=" + this.course_page,
						method: "POST",
						dataType: "json",
						success(res) {
							if (res.data.code == 200) {
								uni.showToast({
									title: "上一页",
									icon: "none",
									duration: 1000,
								})
								uni.setStorageSync("courses", res.data.data);
								var courses = uni.getStorageSync("courses");
								that.course = res.data.data;
								var pictureUrl0 = that.course[0].course_image; //后台传入的图片路径，url指的是数据库中图片的路径
								that.imurl1 = pictureUrl0;
								var pictureUrl1 = that.course[1].course_image;
								that.imurl2 = pictureUrl1;
								var pictureUrl2 = that.course[2].course_image;
								that.imurl3 = pictureUrl2;
								var pictureUrl3 = that.course[3].course_image;
								that.imurl4 = pictureUrl3;
							} else {
								this.course_page = this.course_page + 1;
								uni.showToast({
									title: "换页失败",
									icon: "none",
									duration: 1000
								})
							}
						}
					})
				}
			},
			//下一页的函数实现

			next_page: function() {
				var that = this;
				this.course_page = this.course_page + 1;
				uni.request({
					url: "http://127.0.0.1:8081/searchCourse?coursepage=" + this.course_page,
					method: "POST",
					dataType: "json",
					success(res) {
						if (res.data.code == 200) {
							uni.showToast({
								title: "下一页",
								icon: "none",
								duration: 1000,
							})
							if (res.data.data.length!=0) {
								uni.setStorageSync("coures", res.data.data);
								var courses = uni.getStorageSync("courses");
								console.log(res.data.data);
								that.course = res.data.data;
								var pictureUrl0 = that.course[0].course_image; //后台传入的图片路径，url指的是数据库中图片的路径
								that.imurl1 = pictureUrl0;
								var pictureUrl1 = that.course[1].course_image;
								that.imurl2 = pictureUrl1;
								var pictureUrl2 = that.course[2].course_image;
								that.imurl3 = pictureUrl2;
								var pictureUrl3 = that.course[3].course_image;
								that.imurl4 = pictureUrl3;
							} else {
								that.course_page = that.course_page - 1;
							}
						} else {
							this.course_page = this.course_page - 1;
							uni.showToast({
								title: "换页失败",
								icon: "none",
								duration: 1000
							})
						}
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
	}

	.title-box {
		margin-top: 10rpx;
		width: 100%;
		height: 100rpx;
	}

	.title-name {
		margin-left: 40%;
		font-size: 40rpx;
		font-weight: bold;
	}

	.logo {
		margin-top: 10rpx;
		margin-left: 50rpx;
		height: 400rpx;
		width: 100%;
		align-self: center;
	}

	.title2-box {
		width: 100%;
		height: 75rpx;
	}

	.title2 {
		width: 100%;
		margin-left: 30rpx;
		font-size: 50rpx;
		font-weight: bold;
	}

	.course-allbox {
		width: 100%;
		height: 500rpx;
	}

	.course-box {
		width: 45%;
		height: 250rpx;
		margin-bottom: 100rpx;
		margin-left: 3%;
		float: left;
	}

	.course-box image {
		width: 90%;
		height: 200rpx;
		margin-left: 5%;
		border: 2px solid #333;
	}

	.course-box text {
		margin-left: 50rpx;
		margin-top: 10rpx;
		font-size: 40rpx;
		font-weight: bold;
	}

	.course-name-box {
		margin-top: 20rpx;
		margin-left: 20%;
		background-color: antiquewhite;
	}

	.page-change-box {
		width: 100%;
		height: 80rpx;
		margin-top: 150rpx;
		background-color: #e1e1e1;
	}

	.prev-page {
		width: 60rpx;
		height: 50rpx;
		float: left;
		margin-left: 300rpx;
		margin-top: 10rpx;
	}

	.page-num {
		width: 100rpx;
		font-size: 40rpx;
		margin-top: 10rpx;
	}

	.next-page {
		width: 60rpx;
		height: 50rpx;
		margin-top: 10rpx;
	}
</style>