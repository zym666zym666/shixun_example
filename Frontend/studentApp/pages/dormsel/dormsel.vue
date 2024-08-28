<template>
	<view class="content">
		<view class="box">
			<view v-for="(item,index) in info" :key="item.id" class="inner_box" @click="handleClick(item,index)">
				<image :src="item.isOccupied ? `http://127.0.0.1:8081/file${this.stuImg}` : '../../static/待入住.png'"
					id="wait"></image>
				<view class="right">
					<view class="top">
						<text :id="item.isOccupied ? 'one' : 'two'">{{ getBedText(item) }}</text>
						<view :class="item.isOccupied ? 'zhu' : 'kong'">
							<text>{{ item.isOccupied ? '已入住' : '待入住' }}</text>
						</view>
					</view>
					<view class="text-box" v-if="item.isOccupied">
						<text id="one">手机：{{item.phone}}</text>
						<text id="one">专业：{{item.major}}</text>
						<text id="one">籍贯：{{item.nation}}</text>
					</view>
					
				</view>
				<view v-if="item.isOccupied && item.phone === stuPhone" class="checkout-btn">
					<button @click="handleCheckout(item)">退房</button>
				</view>
			</view>
		</view>

		<!-- 弹窗 -->
		<view v-if="showModal" class="modal">
			<view class="modal-content">
				<text>是否确认入住</text>
				<view class="modal-footer">
					<button @click="submitForm">确认</button>
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
				info: [],
				phone: "",
				buildingId: "",
				bunk: "",
				status: "",
				stuName: "",
				stuPhone: "",
				stuMajor: "",
				stuImg: "",
				stuNation: "",
				showModal: false,
				// selectedIndex:0
			};
		},
		created() {
			this.buildingId = uni.getStorageSync("dormId");
			this.fetchBedsData(this.buildingId);
			this.getStu(this.phone)
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
			handleClick(item, index) {
				this.bunk = index + 1;
				if (!item.isOccupied) {
					this.selectedBed = item.id;
					this.showModal = true;
				}
			},
			// 处理退房
			handleCheckout(item) {
				// 确认退房
				uni.showModal({
					title: '退房确认',
					content: `确定要退房吗？`,
					success: (res) => {
						if (res.confirm) {
							this.submitCheckout(item.phone);
						}
					}
				});
			},
			getBedText(item) {
				return item.isOccupied ? `${item.id}号床位 ${item.name}` : `${item.id}号床位 待入住`;
			},
			closeModal() {
				this.showModal = false;
			},
			//获取学生信息，传到this.data中
			getStu(phone) {
				this.phone = this.$store.getters.tel;
				uni.request({
					url: "http://127.0.0.1:8081/getStu",
					method: "GET",
					dataType: "json",
					data: {
						phone: this.phone
					},
					success: (res) => {
						/* console.log(res.data.data) */
						if (res.data.code === 200) {
							// 使用等号进行赋值
							this.stuName = res.data.data.name;
							this.stuPhone = res.data.data.tel;
							this.stuMajor = res.data.data.major;
							this.stuImg = res.data.data.image;
							this.stuNation = res.data.data.nation;
						} else {
							// 使用 uni 框架的 showToast 函数显示加载失败的提示
						}
					},
				})
			},
			//办理入住
			submitForm() {
				console.log(this.stuName);
				uni.request({
					url: "http://127.0.0.1:8081/stay",
					method: "POST",
					dataType: "json",
					data: {
						buildingId: this.buildingId,
						bunk: this.bunk,
						status: this.status,
						stuName: this.stuName,
						stuPhone: this.stuPhone,
						stuMajor: this.stuMajor,
						stuImg: this.stuImg,
						stuNation: this.stuNation
					},
					success(res) {

						if (res.data.code == 200) {
							uni.showToast({
								title: "办理入住成功",
								icon: "none",
								duration: 3000,
							})
							setTimeout(function() {
								window.location.reload();
							}, 1000);

						} else {
							uni.showToast({
								icon: "error",
								title: "您已经入住，不可再次选择"
							})
						}
					}
				})
			},

			//从数据库中调出宿舍床位信息
			fetchBedsData(buildingId) {
				axios.get('http://127.0.0.1:8081/getDorm', {
						params: {
							buildingId: this.buildingId
						}
					})
					.then(response => {
						console.log(response)
						// 检查返回的数据结构，确保正确处理
						if (response.data.code === 200) {
							// 假设后端返回的数据结构是如你提供的JSON格式
							const bedsData = response.data.data; // 获取data字段中的数据


							console.log(bedsData);
							this.info = bedsData.map(bed => ({
								id: bed.bunk,
								isOccupied: bed.status === "1",
								name: bed.stuName || '', // 如果没有名字，显示为空字符串
								phone: bed.stuPhone || '', // 如果没有电话号码，显示为空字符串
								major: bed.stuMajor || '', // 专业信息
								img: bed.stuImg || '', // 图片信息
								nation: bed.stuNation || '' // 民族信息
							}));
						} else {
							console.error('返回的数据格式不正确或请求失败:', response.data.msg);
						}
					})
					.catch(error => {
						console.error('Error fetching beds data:', error);
					});
			},
			submitCheckout(phone) {
				console.log("提交退房的手机号:", phone); // 打印传递的手机号
				axios.post(`http://127.0.0.1:8081/checkOut?phone=${phone}`)
					.then((response) => {
						if (response.data.code === 200) {
							uni.showToast({
								title: '退房成功',
								icon: 'none',
								duration: 3000,
							});
							// 手动更新对应床位状态
							this.info = this.info.map(bed => {
								if (bed.phone === phone) {
									return {
										...bed,
										isOccupied: false,
										name: '',
										phone: '',
										major: '',
										img: '',
										nation: ''
									};
								}
								return bed;
							});
						} else {
							uni.showToast({
								title: response.data.msg || '退房失败',
								icon: 'error',
							});
						}
					})
					.catch((error) => {
						console.error('退房请求失败:', error);
						uni.showToast({
							title: '请求失败',
							icon: 'error',
						});
					});
			},
		}
	};
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
	}

	#wait {
		width: 220rpx;
		height: 280rpx;
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
	.checkout-btn {
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    margin-top: 100px; /* 缩小按钮与上方内容的间距 */
		margin-right: 2%;
	}
	
	.checkout-btn button {
	    background-color: #f76c6c; /* 红色背景 */
	    color: white; /* 白色字体 */
	    border: none;
	    border-radius: 3px; /* 边角略微圆滑 */
	    padding: 5px 10px; /* 缩小内边距 */
	    font-size: 12px; /* 缩小字体 */
	    cursor: pointer;
	    transition: background-color 0.3s, transform 0.2s;
	}
	
	.checkout-btn button:hover {
	    background-color: #d9534f; /* 浅红色背景 */
	    transform: scale(1.05); /* 放大效果 */
	}
	
	.checkout-btn button:active {
	    background-color: #c9302c; /* 更深红色背景 */
	}
</style>