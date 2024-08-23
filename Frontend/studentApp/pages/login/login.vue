<template>
	<view class="content">
		<image class="logo" src="../../static/cqu.png"></image>
		<text class="title">欢迎使用智慧迎新</text>
		<view class="phone-box">
			<text id="phone_title">+86</text>
			<image src="../../static/sjx.png"></image>
			<input v-model="tel" type="text" placeholder="请输入手机号" />
		</view>
		<view class="passwd-box">
			<text>密码</text>
			<input v-model="password" type="safe-password" password placeholder="请输入密码" />
		</view>
		<button class="btn" @click="login()">登录</button>
		<view>
			<text id="forget_pwd" @click="forgotPassword()">忘记密码</text>
			<text id="register" @click="toregister()">账户激活</text>
		</view>
	</view>

</template>

<script>
	export default {
		data() {
			return {
				tel: "",
				password: ""
			}
		},
		methods: {
			toregister: function() {
				// 跳转注册
				uni.navigateTo({
					url: "/pages/register/register"
				})
			},
			forgotPassword: function() {
				if (this.tel === "") {
					uni.showToast({
						title: "请输入手机号",
						icon: "none",
						duration: 2000
					});
					return;
				}
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
						if (res.data.code === 200 && res.data.data) {
							// 手机号存在，跳转到设置密码页面
							uni.navigateTo({
								url: "/pages/setpwd/setpwd?tel=" + this.tel
							});
						} else {
							// 手机号不存在
							uni.showToast({
								title: "手机号不存在",
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
			login: function() {
				if (this.tel === "" || this.password === "") {
					uni.showToast({
						title: "手机号和密码不能为空",
						icon: "none",
						duration: 2000
					});
					return;
				}
				// 发送请求进行登录
				uni.request({
					url: "http://localhost:8081/login",
					method: "POST",
					data: {
						tel: this.tel,
						password: this.password
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if (res.data.code === 200) {
							// 登录成功，跳转到首页
							uni.navigateTo({
								url: "/pages/index/index"
							});
						} else {
							uni.showToast({
								title: res.data.message || "手机号或密码错误",
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

	.logo {
		width: 150rpx;
		height: 150rpx;
		margin-top: 100rpx;
		margin-left: 50rpx;
	}

	.title {
		font-size: 40rpx;
		margin-left: 50rpx;
		font-weight: bold;
		margin-top: 40rpx;
	}

	.phone-box,
	.passwd-box {
		width: 80%;
		height: 60px;
		margin-left: 10%;
		margin-top: 30rpx;
		border-bottom: solid #dadada 2rpx;
	}



	#phone_title {
		line-height: 160rpx;
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

	.passwd-box text {
		font-size: 30rpx;
	}

	.passwd-box input {
		margin-top: 20rpx;
	}

	.btn {
		width: 80%;
		height: 80rpx;
		background: rgba(62, 177, 209, 0.8);
		font-size: 30rpx;
		color: #fff;
		margin-top: 50rpx;
		border-radius: 50rpx;
		margin-bottom: 20rpx;
	}

	#forget_pwd {
		font-size: 30rpx;
		color: #999999;
		margin-left: 80rpx;
		margin-top: 30rpx;
	}

	#register {
		float: right;
		margin-right: 80rpx;
		color: #999999;
		font-size: 30rpx;
	}
</style>