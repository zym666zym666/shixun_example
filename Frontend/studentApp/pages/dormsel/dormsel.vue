<template>
	<view class="content">
		<view class="box">
			<view v-for="item in info" :key="item.id" class="inner_box" @click="handleClick(item)">
				<image :src="item.isOccupied ? '../../static/img/人像.png' : '../../static/img/待入住.png'" id="wait" ></image>
				<view class="right">
					<view class="top">
						<text :id="item.isOccupied ? 'one' : 'two'">{{ getBedText(item) }}</text>
						<view :class="item.isOccupied ? 'zhu' : 'kong'">
							<text>{{ item.isOccupied ? '已入住' : '待入住' }}</text>
						</view>
					</view>
					<view class="text-box" v-if="item.isOccupied">
						<text id="one">手机：{{item.tel}}</text>
						<text id="one">专业：{{item.major}}</text>
						<text id="one">籍贯：{{item.addr}}</text>
					</view>
				</view>
			</view>
		</view>

		<!-- 弹窗 -->
		<view v-if="showModal" class="modal">
			<view class="modal-content">
				<text>填写入住信息</text>
				<input v-model="formData.name" placeholder="姓名" />
				<input v-model="formData.tel" placeholder="手机号码" />
				<input v-model="formData.major" placeholder="专业" />
				<input v-model="formData.addr" placeholder="籍贯" />
				<view class="modal-footer">
					<button @click="submitForm">提交</button>
					<button @click="closeModal">取消</button>
				</view>
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
				info: [
					{
						id: 1,
						name: "待入住",
						tel: "",
						major: "",
						addr: "",
						isOccupied: false
					},
					{
						id: 2,
						name: "待入住",
						tel: "",
						major: "",
						addr: "",
						isOccupied: false
					},
					{
						id: 3,
						name: "待入住",
						tel: "",
						major: "",
						addr: "",
						isOccupied: false
					},
					{
						id: 4,
						name: "待入住",
						tel: "",
						major: "",
						addr: "",
						isOccupied: false
					}
				],
				showModal: false,
				selectedBed: null,
				formData: {
					name: '',
					tel: '',
					major: '',
					addr: ''
				}
			}
		},
		methods: {
			getBedText(item) {
				return item.isOccupied ? `${item.id}号床位 ${item.name}` : `${item.id}号床位 待入住`;
			},
			handleClick(item) {
				if (!item.isOccupied) {
					this.selectedBed = item.id;
					this.showModal = true;
				}
			},
			closeModal() {
				this.showModal = false;
			},
			submitForm() {
				// 构建要发送到后端的请求数据
				const newBedData = {
					id: this.selectedBed,
					name: this.formData.name,
					tel: this.formData.tel,
					major: this.formData.major,
					addr: this.formData.addr,
					isOccupied: true
				};

				// 发送POST请求到后端API
				axios.post("http://127.0.0.1:8088/DormStu", newBedData)
					.then(response => {
						console.log(response.data);
						// 更新前端数据
						const index = this.info.findIndex(item => item.id === this.selectedBed);
						if (index !== -1) {
							this.$set(this.info, index, newBedData);
						}
						this.closeModal();
					})
					.catch(error => {
						console.error("Error submitting form:", error);
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

	.box {
		display: flex;
		flex-direction: column;
		border-radius: 10rpx;
		width: 95%;
		height: 1400rpx;
		margin-left: 2.5%;
		margin-right: 2.5%;
		margin-top: 15rpx;
		box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.5);
	}

	.inner_box {
		display: flex;
		width: 100%;
		border: solid #dadada 2rpx;
		height: 25%;
	}

	.inner_box image {
		width: 200rpx;
		height: 90%;
		margin-left: 20rpx;
		margin-top: 16rpx;
		margin-right: 20rpx;
	}

	.right {
		display: flex;
		flex-direction: column;
		width: 69%;
		height: 100%;
	}

	.top {
		display: flex;
		margin-top: 20rpx;
		margin-right: 20rpx;
		width: 100%;
		height: 60rpx;
	}

	.text-box {
		display: flex;
		flex-direction: column;
		width: 100%;
		height: 100%;
		border-bottom: solid #000000 5rpx;
	}
	#wait{
		width: 220rpx ;
		height:280rpx;
	}

	#one {
		margin-top: 25rpx;
		display: inline-block;
		font-weight: bold;
		font-size: 33rpx;
		color: #999999;
	}

	#two {
		margin-top: 23%;
		margin-left: 30rpx;
		display: inline-block;
		font-weight: bold;
		font-size: 33rpx;
		color: #999999;
	}

	.zhu {
		display: flex;
		justify-content: center;
		margin-left: auto;
		margin-right: 10rpx;
		margin-top: 0rpx;
		width: 150rpx;
		height: 60rpx;
		background-color: greenyellow;
		text-align: center;
	}

	.zhu text {
		margin-top: 5%;
		color: #fff;
		font-weight: bold;
	}

	.kong {
		display: flex;
		justify-content: center;
		margin-left: auto;
		margin-right: 10rpx;
		margin-top: 0rpx;
		width: 150rpx;
		height: 60rpx;
		background-color: burlywood;
		text-align: center;
	}

	.kong text {
		margin-top: 5%;
		color: #fff;
		font-weight: bold;
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
	/* 弹窗样式 */
	.modal {
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background-color: rgba(0, 0, 0, 0.5);
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.modal-content {
		background-color: white;
		padding: 20px;
		border-radius: 10px;
		width: 300px;
	}
	.modal-footer {
		display: flex;
		justify-content: space-between;
		margin-top: 20px;
	}
</style>