<template>
  <view>
    <image src='../../static/在线咨询.jpg' class="online"></image>
    <text class="word">联系老师咨询更多信息</text>
    <button class="btn" @click="showContactInfo">
      <text class="getInfo">立即咨询</text>
    </button>

    <!-- 模态窗口 -->
    <view v-if="isModalVisible" class="modal">
      <view class="modal-content">
        <text class="modal-text">老师的联系电话: 12345678911</text>
        <button class="close-btn" @click="closeModal">关闭</button>
      </view>
    </view>
  </view>
</template>


<script>
  export default {
    data() {
      return {
        isModalVisible: false // 控制模态窗口是否显示
      };
    },
    methods: {
      showContactInfo() {
        this.isModalVisible = true; // 显示模态窗口
      },
      closeModal() {
        this.isModalVisible = false; // 隐藏模态窗口
      }
    }
  };
</script>

<style>
.online{
	display: flex;
	justify-content: center;
	margin-top: 50rpx;
	scale: 50%;
	margin-left: 50rpx;
}
.word{
	margin-top: 100rpx;
	display: flex;
	justify-content: center;
	font-size: 60rpx;
	font-weight: bold;

}
.getInfo{
	color: #fff;
}
.btn{
	width: 80%;
	margin-top: 50rpx;
	background-color: #00ff7f;
	border-radius: 50rpx;
}
.modal {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.6); /* 半透明黑色背景 */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}
.modal-content {
  background-color: #fff;
  padding: 40rpx;
  border-radius: 30rpx; /* 圆角 */
  width: 80%;
  text-align: center;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1); /* 阴影效果 */
  animation: fadeIn 0.3s ease; /* 动画效果 */
}
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: scale(0.9);
  }
  to {
    opacity: 1;
    transform: scale(1);
  }
}
.modal-text {
  font-size: 50rpx;
  margin-bottom: 20rpx;
  color: #333;
  font-weight: bold;
}
.phone-number {
  font-size: 60rpx;
  color: #00bfff; /* 明亮的蓝色 */
  font-weight: bold;
  margin-bottom: 40rpx;
}
.close-btn {
  width: 80%;
  height:80%;
  padding: 20rpx;
  background-color: #ffaaff;
  color: white;
  border: none;
  border-radius: 50rpx;
  font-size: 40rpx;
  cursor: pointer;
}

</style>
