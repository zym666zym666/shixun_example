<template>
	<view class="content">
		<text class="title">请设置密码</text>
		<text class="sub">设置登录密码用于登录 智慧迎新</text>
		<view class="passwd-box">
			<text>密码</text>
			<view class="input-wrapper">
				<input v-model="password" :type="passwordVisible ? 'text' : 'password'" placeholder="请输入密码"
					class="passwd-input" />
				<image :src="passwordVisible ? '../../static/eye_hide.png' : '../../static/eye_show.png'"
					@click="togglePasswordVisibility"></image>
			</view>
		</view>
		<text class="zi" v-if="errorMessage">{{errorMessage}}</text>
		<button class="btn" @click="register()">确认注册</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				password: "",
				tel: '',
				passwordVisible: false, // 默认密码隐藏
				errorMessage: "密码至少8个字符，不能全是字母或数字"
			}
		},
		onLoad(options) {
			// 当页面加载时，options 参数包含了页面的查询参数
			this.tel = options.tel; // 这里的 'tel' 就是查询参数的键名
		},
		methods: {
			togglePasswordVisibility() {
				this.passwordVisible = !this.passwordVisible; // 切换密码显示状态
			},
			checkPassword() {
				// 检查密码长度是否至少为8个字符
				if (this.password.length < 8) {
					this.errorMessage = '密码至少8个字符，不能全是字母或数字';
				} else {
					// 检查密码是否全是字母或全是数字
					const allLetters = /^[a-zA-Z]+$/.test(this.password);
					const allNumbers = /^[0-9]+$/.test(this.password);
					if (allLetters || allNumbers) {
						this.errorMessage = '密码至少8个字符，不能全是字母或数字';
					} else {
						this.errorMessage = '';
					}
				}
			},
			register() {
				this.checkPassword();
				uni.request({
					url: "http://localhost:8081/register",
					method: "POST",
					data: {
						tel: this.tel,
						password: this.password
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if (res.data.code == 200) {
							uni.showToast({
								title: "操作成功",
								icon: "none",
								duration: 2000,
								success() {
									setTimeout(function() {
										uni.navigateTo({
											url: "/pages/login/login"
										})
									}, 1000)
								}
							})
						} else {
							uni.showToast({
								title: res.data.message || "操作失败",
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
				})
			}
		},
		watch: {
			// 监听密码的变化，实时进行验证
			password(newVal) {
				this.checkPassword();
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

	.passwd-box image {
		float: right;
		width: 30rpx;
		height: 30rpx;
		margin-right: 10%;
		margin-top: 0;
	}

	.passwd-box {
		width: 80%;
		height: 60px;
		margin-left: 10%;
		margin-top: 100rpx;
	}

	.input-wrapper {
		width: 100%;
		justify-content: space-between;
		align-items: center;
		flex: 1;
		display: flex;
		margin-top: 25rpx;
		border-bottom: solid #dadada 2rpx;
	}

	.passwd-box text {
		font-size: 30rpx;
	}

	.passwd-box input {
		vertical-align: middle;
	}

	.passwd-box image {
		margin-right: 10%;
	}

	.zi {
		margin-top: 15rpx;
		margin-left: 10%;
		font-size: 25rpx;
		font-weight: bold;
		color: red
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
</style>