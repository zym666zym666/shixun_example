<template>
	<view class="content">
		<view class="data-box">
			<view class="image-upload">
				<image v-if="image" :src="fullImageUrl" class="show"></image>
				<view v-else class="hide">无头像</view>
			</view>
			<view class="sub">
				<image src="../../static/学生信息.png"></image>
				<text>学生信息</text>
			</view>
			<view class="text-box">
				<text class="label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</text>
				<view class="write">
					<text>{{ name }}</text>
				</view>
			</view>
			<view class="text-box">
				<text class="label">身份证号：</text>
				<view class="write">
					<text>{{ idcard }}</text>
				</view>
			</view>
			<view class="text-box">
				<text class="label">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;院：</text>
				<view class="write">
					<text>{{ institute }}</text>
				</view>
			</view>
			<view class="text-box">
				<text class="label">专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</text>
				<view class="write">
					<text>{{ major }}</text>
				</view>
			</view>
			<view class="text-box">
				<text class="label">班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</text>
				<view class="write">
					<text>{{ classes }}</text>
				</view>
			</view>
			<view class="text-box">
				<text class="label">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</text>
				<view class="write">
					<text>{{ stu_id }}</text>
				</view>
			</view>
			<view class="text-box">
				<text class="label">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</text>
				<view class="write">
					<text>{{ tel }}</text>
				</view>
			</view>
			<view class="text-box">
				<text class="label">民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族：</text>
				<view class="write">
					<text>{{ nation }}</text>
				</view>
			</view>
			<view class="text-box">
				<text class="label">家庭住址：</text>
				<view class="write">
					<text>{{ address }}</text>
				</view>
			</view>
		</view>
		<button class="btn" @click="settings()">设置</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				name: '',
				idcard: '',
				institute: '',
				major: '',
				classes: '',
				stu_id: '',
				tel: '',
				nation: '',
				address: '',
				image: '',
				selectedIndex: 0 // TabBar 的选中项索引
			};
		},
		computed: {
			fullImageUrl() {
				if (this.image) {
					return `http://127.0.0.1:8081/file${this.image}`;
				}
				return "";
			}
		},
		onLoad() {
			console.log(uni.getStorageSync("userTel"));
			this.getStudentInfo();
			// this.onShow();
		},
		methods: {
			getStudentInfo() {
				const storedTel = uni.getStorageSync('userTel');
				this.tel=storedTel;
				console.log('Retrieved tel:', storedTel); // 检查是否成功读取
				uni.request({
					url: 'http://localhost:8081/getInfo',
					method: 'POST',
					data: {
						tel: storedTel
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if (res.data.code === 200) {
							const student = res.data.data;
							this.name = student.name;
							this.idcard = student.idcard;
							this.institute = student.institute;
							this.major = student.major;
							this.classes = student.classes;
							this.stu_id = student.stuId;
							this.tel = student.tel;
							this.nation = student.nation;
							this.address = student.address;
							this.image = student.image;
						} else {
							uni.showToast({
								title: res.data.message || "信息加载失败",
								icon: "none",
								duration: 2000
							});
						}
					},
					fail: (err) => {
						uni.showToast({
							title: "网络错误，请重试",
							icon: "none",
							duration: 2000
						});
					}
				});
			},
			settings() {
				uni.navigateTo({
					url: "/pages/settings/settings"
				});
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
	}

	.data-box {
		display: flex;
		flex-direction: column;
		border-radius: 30rpx;
		width: 90%;
		height: 1300rpx;
		margin-left: 5%;
		margin-right: 5%;
		margin-top: 15rpx;
		box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.5);
	}

	.image-upload {
		overflow: hidden;
		width: 200rpx;
		height: 280rpx;
		margin-top: 10%;
		margin-left: 35%;
		display: flex;
		justify-content: center;
	}

	.show {
		width: 100%;
		height: 100%;
		object-fit: contain;
	}

	.hide {
		width: 100%;
		height: 100%;
		background-color: #dadada;
		display: flex;
		justify-content: center;
		align-items: center;
		font-weight: bold;
	}

	.sub {
		display: flex;
		align-items: center;
		justify-content: start;
		width: 90%;
		height: 120rpx;
		margin-left: 5%;
		margin-right: 5%;
		margin-top: 30rpx;
		margin-bottom: 70rpx;
	}

	.sub image {
		width: 60rpx;
		height: 60rpx;
		margin-left: 3%;
		margin-top: 60rpx;
		margin-right: 20rpx;
	}

	.sub text {
		margin-top: 70rpx;
		font-size: 40rpx;
		font-weight: bold;
		color: #999999;
	}

	.text-box {
		display: flex;
		align-items: center;
		width: 90%;
		height: 85rpx;
		margin-left: 5%;
		margin-right: 5%;
	}

	.label {
		width: 240rpx;
		font-size: 34rpx;
		font-weight: bold;
		color: #999999;
	}

	.write {
		display: flex;
		align-items: center;
		justify-content: center;
		margin-bottom: 10rpx;
		height: 90%;
		width: 100%;
		border-bottom: solid #dadada 2rpx;
		font-weight: bold;
	}

	.btn {
		width: 80%;
		height: 80rpx;
		background: rgba(0, 170, 0, 0.8);
		font-size: 30rpx;
		color: #fff;
		margin-top: 30rpx;
		border-radius: 50rpx;
		margin-bottom: 20rpx;
	}
</style>