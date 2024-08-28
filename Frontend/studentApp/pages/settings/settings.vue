<template>
	<view class="settings-container">
		<text class="settings-title">设置</text>
		<view class="settings-item">
			<button class="update-phone-btn" @click="showModal">修改手机号</button>
		</view>
		<view class="settings-item">
			<!-- 绑定点击事件到 logout 方法 -->
			<button class="logout-btn" @click="logout">登出</button>
		</view>

		<!-- 弹窗 -->
		<view v-if="isModalVisible" class="modal-overlay">
			<view class="modal-content">
				<text class="modal-title">修改手机号</text>
				<input ref="phoneInput" focus="true" type="text" v-model="phone" placeholder="请输入新的手机号" />
				<view class="modal-buttons">
					<button @click="confirmUpdatePhone">确认</button>
					<button @click="hideModal">取消</button>
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				phone: "",
				isModalVisible: false, // 控制弹窗显示
			};
		},
		methods: {
			showModal() {
				this.phone = "";
				this.isModalVisible = true;
				this.$nextTick(() => {
					this.$refs.phoneInput.focus(); // 在下一次 DOM 更新后聚焦输入框
				});
			},
			hideModal() {
				this.isModalVisible = false;
			},
			confirmUpdatePhone() {
				const phoneRegex = /^1[3-9]\d{9}$/; // 大陆手机号正则表达式
				if (!phoneRegex.test(this.phone)) {
					uni.showToast({
						title: "手机号格式不正确",
						icon: "none",
						duration: 2000
					});
					return;
				}

				this.checkTel(this.phone);
			},
			checkTel(phone) {
				uni.request({
					url: 'http://127.0.0.1:8081/checkTel', // 检查手机号是否已注册的接口
					method: 'POST',
					data: {
						tel: phone
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if (res.data.code === 200) {
							if (res.data.data) {
								uni.showToast({
									title: "该手机号已注册",
									icon: "none",
									duration: 2000
								});
							} else {
								this.updateTel();
								// 登出
							}
						} else {
							uni.showToast({
								title: res.data.message || "服务端错误",
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
			updateTel() {
				uni.request({
					url: 'http://127.0.0.1:8081/updateTel', // 更新手机号的接口
					method: 'POST',
					data: {
						tel: this.$store.getters.tel,
						phone: this.phone
					},
					header: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					success: (res) => {
						if (res.data.code === 200 && res.data.data) {
							console.log(res.data);
							this.$store.commit('setTel', this.phone);
							uni.setStorageSync('userTel', this.phone);
							this.isModalVisible = false;
							uni.showToast({
								title: "修改成功",
								icon: "success",
								duration: 2000
							});
							// uni.navigateBack();
						} else {
							uni.showToast({
								title: res.data.message || "修改失败",
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
			logout() {
				this.$store.commit('setTel', '');
				uni.removeStorageSync('userTel');
				uni.navigateTo({
					url: '/pages/login/login'
				});
			}
		}
	}
</script>

<style>
	.settings-container {
		display: flex;
		flex-direction: column;
		padding: 20px;
		background-color: #f9f9f9;
		border-radius: 8px;
		box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
		margin: 20px;
	}

	.settings-title {
		font-size: 24px;
		font-weight: bold;
		color: #333;
		text-align: center;
		margin-bottom: 20px;
	}

	.settings-item {
		display: flex;
		align-items: center;
		justify-content: space-between;
		padding: 15px;
		background-color: #ffffff;
		border-radius: 6px;
		margin-bottom: 15px;
		box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
	}

	.item-label {
		flex: 1;
		font-size: 16px;
		color: #555;
	}

	.update-phone-btn,
	.logout-btn {
		padding: 8px 20px;
		background-color: #007aff;
		color: #ffffff;
		border: none;
		width: 100%;
		border-radius: 4px;
		justify-content: center;
		/* 居中对齐 */
		cursor: pointer;
		font-size: 16px;
	}

	.logout-btn {
		background-color: #e64340;
	}

	.modal-overlay {
		position: fixed;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		background-color: rgba(0, 0, 0, 0.5);
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.modal-content {
		background-color: #fff;
		font-weight: 'bold';
		padding: 20px;
		height: 180px;
		border-radius: 8px;
		width: 300px;
	}

	.modal-content input {
		margin-top: 30px;
		font-weight: 'bold';
		height: 20px;
		border-bottom: solid #dadada 2rpx;
	}

	.modal-title {
		font-size: 20px;
		margin-bottom: 10px;
		font-weight: 'bold';
	}

	.modal-buttons {
		display: flex;
		justify-content: space-between;
		margin-top: 20px;
	}

	.modal-buttons button {
		margin-top: 10px;
		padding: 10px 20px;
	}
</style>