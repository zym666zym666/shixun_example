<template>
	<view class="content">
		<text class="title">账户激活</text>
		<text class="sub">请输入手机号</text>
		<view class="phone-box">
			<text id="phone_title">+86</text>
			<image src="../../static/sjx.png"></image>
			<input v-model="tel" name="tel" type="text" placeholder="请输入手机号" />
		</view>
		<button class="btn" @click="activate()">同意并激活账号</button>
		<view class="bottom">
			<text id="one">我已阅读并同意：</text>
			<text id="two">###软件服务协议</text>
		</view>
	</view>
</template>

<script>
	import graceChecker from '@/js_sdk/graceui-dataChecker/graceChecker.js';
	export default {
		data() {
			return {
				tel: ''
			}
		},
		methods: {
			activate() {
				var rule = [{
					name: "tel",
					checkType: "phoneno",
					checkRule: "^1[3-9]\\d{9}$",
					errorMsg: "请输入正确的电话号码"
				}];
				var data = {
					tel: this.tel
				};
				var check = graceChecker.check(data, rule);
				if (check) {
					// 发送请求检查手机号是否存在
					uni.request({
						url: "http://localhost:8081/checkTel",
						method: "POST",
						data: {
							tel: this.tel
						},
						header: {
							'Content-Type': 'application/x-www-form-urlencoded'
						},
						success: (res) => {
							if (res.data.code === 200) {
								if (res.data.data) {
									// 手机号已存在，提示用户并跳转到登录页面
									uni.showToast({
										title: "手机号已注册，请直接登录",
										icon: "none",
										duration: 2000
									});
									setTimeout(() => {
										uni.navigateTo({
											url: "/pages/login/login"
										});
									}, 1000);
								} else {
									// 手机号不存在，跳转到设置密码页面
									uni.navigateTo({
										url: "/pages/setpwd/setpwd?tel=" + this.tel
									});
								}
							} else {
								uni.showToast({
									title: "检查手机号失败",
									icon: "none",
									duration: 2000
								});
							}
						}
					});
				} else {
					uni.showToast({
						icon: "none",
						title: graceChecker.error
					});
				}
			}
		}
	}
</script>


<style>
	.content {
		display: flex;
		flex-direction: column;
	}

	.title {

		font-size: 50rpx;
		font-weight: bold;
		margin-top: 50rpx;
		margin-left: 10%;
		margin-bottom: 30rpx;
	}

	.sub {
		font-size: 30rpx;
		color: #999999;
		margin-left: 10%;
	}

	.phone-box {
		width: 80%;
		height: 60px;
		margin-left: 10%;
		margin-top: 80rpx;
		border-bottom: solid #dadada 2rpx;
	}

	.phone-box image {
		width: 40rpx;
		height: 40rpx;
		margin-top: 30rpx;
		margin-left: 20rpx;
	}

	.phone-box input {
		float: right;
		margin-right: 10%;
		margin-top: 65rpx;
	}

	#phone_title {
		line-height: 160rpx;
	}

	.btn {
		width: 80%;
		height: 100rpx;
		background: rgba(62, 177, 209, 0.8);
		font-size: 30rpx;
		color: #fff;
		margin-top: 50rpx;
		border-radius: 20rpx;
		margin-bottom: 20rpx;
		text-align: center;
		line-height: 100rpx;
	}

	.bottom {
		margin-top: 10rpx;
	}

	#one {
		font-size: 25rpx;
		color: #999999;
		margin-left: 80rpx;
		margin-top: 50rpx;
	}

	#two {
		font-size: 25rpx;
		color: #55aa7f;
		margin-left: 10rpx;
		margin-top: 50rpx;
	}
</style>