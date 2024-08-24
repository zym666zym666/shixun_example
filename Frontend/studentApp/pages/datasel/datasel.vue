<template>
	<view class="content">
		<view class="data-box">
			<view class="image-upload" @click="chooseImage">
				<image v-if="image" :src="fullImageUrl" class="show"></image>
				<view v-else class="hide">上传图片</view>
			</view>
			<view class="sub">
				<image src="../../static/学生信息.png"></image>
				<text>学生信息</text>
			</view>
			<view class="text-box">
				<text class="label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</text>
				<view class="write">
					<input type="text" v-model="name" />
				</view>
			</view>
			<view class="text-box">
				<text class="label">身份证号：</text>
				<view class="write">
					<input type="text" v-model="idcard" placeholder="" />
				</view>
			</view>
			<view class="text-box">
				<text class="label">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;院：</text>
				<view class="write">
					<input type="text" v-model="institute" />
				</view>
			</view>
			<view class="text-box">
				<text class="label">专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</text>
				<view class="write">
					<input type="text" v-model="major" />
				</view>
			</view>
			<view class="text-box">
				<text class="label">班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</text>
				<view class="write">
					<input type="text" v-model="classes" />
				</view>
			</view>
			<view class="text-box">
				<text class="label">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</text>
				<view class="write">
					<input type="text" v-model="stu_id" />
				</view>
			</view>
			<view class="text-box">
				<text class="label">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</text>
				<view class="write">
					<input type="text" v-model="tel" />
				</view>
			</view>
			<view class="text-box">
				<text class="label">民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族：</text>
				<view class="write">
					<input type="text" v-model="nation" />
				</view>
			</view>
			<view class="text-box">
				<text class="label">家庭住址：</text>
				<view class="write">
					<input type="text" v-model="address" />
				</view>
			</view>
		</view>
		<button class="btn" @click="submit()">提交</button>
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
				// id: null,
				tel: '',
				name: '',
				idcard: '',
				institute: '',
				major: '',
				classes: '',
				stu_id: '',
				nation: '',
				address: '',
				image: '',
				// password: null,
				selectedIndex: 0,
				status: 1,
			}
		},
		computed: {
			fullImageUrl() {
				if (this.image) {
					return `http://127.0.0.1:8081/file${this.image}`;
				}
				return "";
			}
		},
		methods: {
			tabbarSelected(e) {

			},
			tabbarTaped(e) {
				console.log(e.pagePath)
				uni.switchTab({
					url: "/" + e.pagePath
				})
			},
			// 点击图片选择图片
			chooseImage() {
				uni.chooseImage({
					count: 1, // 默认为9，设置图片的选择数量
					sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
					success: (res) => {
						// 成功选择图片后的回调
						const tempFilePaths = res.tempFilePaths;
						// 上传图片到服务器
						this.uploadImage(tempFilePaths[0]);
					},
					fail: (err) => {
						// 选择图片失败的回调
						console.log('选择图片失败', err);
					}
				});
			},
			// 上传图片到服务器
			uploadImage(imagePath) {
				uni.uploadFile({
					url: 'http://127.0.0.1:8081/file/upload', // 服务器上传接口地址
					filePath: imagePath, // 要上传文件资源的路径
					name: 'file', // 必填，文件对应的 key
					formData: {
						// 附带的额外参数
					},
					success: (uploadFileRes) => {
						// 上传成功的回调
						const resdata = JSON.parse(uploadFileRes.data); // 假设服务器返回的是JSON格式数据
						this.image = resdata.data; // 将返回的图片URL存储到image变量中
						console.log(resdata.data);
						console.log('图片上传成功', uploadFileRes);
					},
					fail: (uploadFileErr) => {
						// 上传失败的回调
						console.log('图片上传失败', uploadFileErr);
					}
				});
			},
			submit() {
				if (this.tel != this.$store.getters.tel) {
					uni.showToast({
						title: "手机号未注册",
						icon: "none",
						duration: 2000
					});
					return;
				}
				const info = {
					tel: this.tel,
					name: this.name,
					idcard: this.idcard,
					institute: this.institute,
					major: this.major,
					classes: this.classes,
					stuId: this.stu_id,
					nation: this.nation,
					address: this.address,
					image: this.image,
					status: this.status,
					password: null,
					id: null
				};
				uni.request({
					url: "http://localhost:8081/datasel", // 后端接口
					method: "POST",
					data: info,
					header: {
						'Content-Type': 'application/json'
					},
					success: (res) => {
						if (res.data.code === 200) {
							uni.showToast({
								title: "提交成功",
								icon: "success",
								duration: 2000
							});
							uni.switchTab({
								url: "/pages/index/index"
							});
						} else {
							uni.showToast({
								title: res.data.message || "提交失败，请重试",
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
		/* 居中对齐 */
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
		/* 水平居中 */
		align-items: center;
		/* 垂直居中 */
		font-weight: bold;
		/* 加粗文字 */
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
		/* 根据需要调整间距 */
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

	.write input {
		text-align: center;
	}

	.btn {
		width: 80%;
		height: 80rpx;
		background: rgba(62, 177, 209, 0.8);
		font-size: 30rpx;
		color: #fff;
		margin-top: 30rpx;
		border-radius: 50rpx;
		margin-bottom: 20rpx;
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
</style>