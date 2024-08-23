<template>
	<view class="content">
		<view class="data-box">
			<!-- <image src="../../static/人像1.png"></image> -->
			<view class="image-upload">
				<view v-if="!image" class="image-placeholder" @click="showFileInput()">
					<text>上传人像</text>
				</view>
				<!-- <image v-else class="preview" :src="image"></image> -->
				<view v-else class="image-preview" :style="{ backgroundImage: 'url(' + image + ')' }"></view>
				<input type="file" id="imageInput" ref="imageInput" accept="image/*" @change="handleImageChange()" />
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
				id: 0,
				name: "张三",
				idcard: "430781000000000000",
				institute: "计算机学院",
				major: "计科",
				classes: "2004班",
				stu_id: "20221010",
				tel: "13007367562",
				nation: "汉族",
				address: "常德市",
				password: "zym777777",
				image: null,
				status: 0,
				selectedIndex: 0
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
			showFileInput() {
				console.log('showFileInput triggered');
				this.$nextTick(() => {
					const inputWrapper = this.$refs.imageInput.$el;
					const inputElement = inputWrapper.querySelector('input[type="file"]');
					console.log('Input element found inside uni-input:', inputElement);
					if (inputElement && typeof inputElement.click === 'function') {
						inputElement.click();
					} else {
						console.error('Input element inside uni-input not found or not a function.');
					}
				});
			},
			handleImageChange(e) {
				console.log('Change event triggered');
				const file = e.target.files[0];
				console.log('Selected file:', file);

				if (file) {
					if (file.size > 2 * 1024 * 1024) { // 2MB
						console.warn('File is too large.');
						return;
					}

					if (file.type.match('image.*')) {
						const reader = new FileReader();
						reader.onload = (event) => {
							const img = new Image();
							img.onload = () => {
								const canvas = document.createElement('canvas');
								const ctx = canvas.getContext('2d');
								canvas.width = img.width;
								canvas.height = img.height;
								ctx.drawImage(img, 0, 0);
								canvas.toDataURL('image/jpeg', 0.7, (dataUrl) => { // 压缩到70%质量
									console.log('Compressed image content:', dataUrl);
									this.image = dataUrl;
								});
							};
							img.src = event.target.result;
						};
						reader.onerror = (error) => {
							console.error('FileReader error:', error); // 捕获并输出读取错误
						};
						reader.readAsDataURL(file);
					} else {
						console.warn('Selected file is not an image.');
					}
				} else {
					console.warn('No file selected.');
				}
			},
			submit() {

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
		width: 100%;
		height: 280rpx;
		margin-top: 10%;
		margin-left: 35%;
	}

	.image-placeholder {
		display: flex;
		align-items: center;
		justify-content: center;
		background-color: antiquewhite;
		width: 200rpx;
		height: 200rpx;
		border: 1px dashed #ccc;
		/* 边框样式，可根据需要调整 */
		cursor: pointer;
		/* 鼠标悬停时显示为手形图标 */
		background-color: #f3f3f3;
		/* 背景色，可根据需要调整 */
	}

	.image-preview {
		width: 200px;
		/* 设置宽度 */
		height: 200px;
		/* 设置高度 */
		background-size: cover;
		background-position: center;
		border: 1px solid #ff5500;
		/* 添加边框以便更好地调试 */
	}


	.preview {
		width: 200rpx;
		height: 200rpx;
		background-size: cover;
		background-position: center;
		/* object-fit: cover; */
		/* 确保图片覆盖整个区域而不变形 */
	}

	#imageInput {
		/* opacity: 0; */
		/* position: absolute; */
		/* 		margin-top: 10rpx;
		margin-left: 0rpx; */
		width: 100%;
		height: 20rpx;
		cursor: pointer;
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