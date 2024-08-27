<template>
	<view class="checkin-container">
		<h1>开学报到流程</h1>

		<!-- 流程步骤 -->
		<view class="step">
			<h2>步骤 1: 身份验证</h2>
			<p>学生需要输入自己的学号和身份信息，系统会进行身份验证。</p>
		</view>

		<view class="step">
			<h2>步骤 2: 宿舍选择</h2>
			<p>学生可在自选宿舍模块中选择自己的宿舍。</p>
		</view>

		<view class="step">
			<h2>步骤 3: 信息填写</h2>
			<p>学生需要填写基本信息，如姓名、联系电话等，确认无误后点击下一步。</p>
		</view>

		<view class="step">
			<h2>步骤 4: 确认报到</h2>
			<p>系统显示已填写的信息，学生确认无误后完成报到流程。</p>
		</view>

		<!-- 底部说明 -->
		<view class="footer">
			<p>请按照以上流程完成报到，确保信息的准确性和完整性。</p>
		</view>
	</view>
	<view class="my-sub-tabbar-wrapper">
		<custom-tab-bar direction="horizontal" :show-icon="true" :selected="selectedIndex"
			@onTabItemTap="tabbarTaped"></custom-tab-bar>
	</view>
</template>

<script>
	export default {
		data() {
			return {};
		}
	};
</script>

<style scoped>
	.checkin-container {
		padding: 20px;
		max-width: 600px;
		margin: auto;
		font-family: Arial, sans-serif;
	}

	h1 {
		text-align: center;
		margin-bottom: 20px;
	}

	.step {
		background-color: #f9f9f9;
		padding: 20px;
		margin-bottom: 15px;
		border-radius: 8px;
		box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	}

	h2 {
		color: #007bff;
	}

	p {
		font-size: 16px;
		line-height: 1.5;
	}

	.footer {
		text-align: center;
		margin-top: 30px;
		font-size: 14px;
		color: #666;
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