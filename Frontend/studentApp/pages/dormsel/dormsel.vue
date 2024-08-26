<template>
	<view class="content">
		<view class="box">
			<view v-for="(item,index) in info" :key="item.id" class="inner_box" @click="handleClick(item,index)">
				<image :src="item.isOccupied ? '../../static/人像.png' : '../../static/待入住.png'" id="wait" ></image>
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
			</view>
		</view>

		<!-- 弹窗 -->
		<view v-if="showModal" class="modal">
			<view class="modal-content">
				<text>填写入住信息</text>
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
	      info: [],
		  phone:"19562228497",
		  buildingId: "",
		  bunk: "",
		  status: "",
		  stuName: "",
		  stuPhone: "",
		  stuMajor: "",
		  stuImg: "",
		  stuNation: "",
	      showModal: false,
	    };
	  },
	  created() {
		this.buildingId=uni.getStorageSync("dormId");
	    this.fetchBedsData(this.buildingId);
		this.getStu(this.phone)
	  },
	  
	  		
	  methods: {
		handleClick(item,index) {
			this.bunk=index+1;
		  	if (!item.isOccupied) {
		  		this.selectedBed = item.id;
		  		this.showModal = true;
		  	}
		  },
	    getBedText(item) {
	      return item.isOccupied ? `${item.id}号床位 ${item.name}` : `${item.id}号床位 待入住`;
	    },
		closeModal() {
			this.showModal = false;
		},
		//获取学生信息，传到this.data中
		getStu(phone){
			uni.request({
				url:"http://127.0.0.1:8088/getStu",
				method:"GET",
				dataType:"json",
				data:{
					phone:this.phone
				},
				success: (res) => {
					/* console.log(res.data.data) */
				  if (res.data.code === 200) {
				    // 使用等号进行赋值
				    this.stuName = res.data.data.stuName;
				    this.stuPhone = res.data.data.phone;
				    this.stuMajor = res.data.data.major;
				    this.stuImg = res.data.data.image;
				    this.stuNation = res.data.data.nation;
				  } else {
				    // 使用 uni 框架的 showToast 函数显示加载失败的提示
				    uni.showToast({
				      title: "信息加载失败",
				      icon: "none",
				      duration: 2000 // 显示时长为2000毫秒
				    });
				  }
				},
			})
		},
		//办理入住
		submitForm(){
			/* console.log(this.stuName); */
			uni.request({
				url:"http://127.0.0.1:8088/stay",
				method:"POST",
				dataType:"json",
				data:{
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
				
					if(res.data.code==200){
						uni.showToast({
							title: "办理入住成功",
							icon: "none",
							duration:3000,
						})
						setTimeout(function() {
						  window.location.reload();
						}, 1000);
						
					}else{
						uni.showToast({
							icon:"error",
							title:"您已经入住，不可再次选择"
						})
					}
				}
			})
		},
		
		//从数据库中调出宿舍床位信息
	    fetchBedsData(buildingId){
	      axios.get('http://127.0.0.1:8088/getDorm', {
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