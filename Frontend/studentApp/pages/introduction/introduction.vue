<template>
  <div class="school-introduction">
    <h1>{{ schoolName }}</h1>

    <!-- 首页内容 -->
    <div v-if="currentPage === 'home'" class="school-content">
      <p>{{ schoolDescription }}</p>
      <img src="../../static/重大.png" alt="Chongqing University" class="school-image"/>
    </div>

    <!-- 校历页面内容 -->
    <div v-if="currentPage === 'xiaoli'" class="xiaoli-content">
      <h2>校历页面</h2>
      <p>以下是重庆大学2024年的校历：</p>
      <img src="../../static/校历.png" alt="School Calendar 2024" class="school-image" @click="showFullScreenCalendar"/>
    </div>

    <!-- 全屏显示校历 -->
    <div v-if="isFullScreenCalendarVisible" class="fullscreen-overlay" @click="hideFullScreenCalendar">
      <img src="../../static/校历.png" alt="Full Screen School Calendar" class="fullscreen-image"/>
    </div>

    <!-- 师资力量及教学设施页面内容 -->
    <div v-if="currentPage === 'contact'" class="contact-content">
      <h2>师资力量及教学设施</h2>
      <p>大力实施人才强校战略，系统构建起“3+7”人事人才制度体系和人才引育“金字塔”，积极营造“近悦远来”的人才发展氛围，以先进体制机制激发高层次人才持续汇聚的内生动力。现有专任教师3200余人，其中包括9位院士在内的国家级人才300余人次，博士生导师1900余人，副高级以上专业技术人员2200余人。</p>
      <img src="../../static/设施1.jpg" alt="Faculty and Facilities" class="school-image"/>
    </div>

    <!-- 按钮组 -->
    <div class="button-group">
      <button @click="goToHomePage">回到介绍</button>
      <button @click="goToXiaoliPage">查询校历</button>
      <button @click="goToContactPage">师资力量及教学设施</button>
      <button @click="goToInitialPage">回到首页</button> <!-- 新增的按钮 -->
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      schoolName: '重庆大学',
      schoolDescription: `重庆大学，创办于1929年，是中央直管、教育部直属的全国重点大学。1998年获批国家“211工程”重点建设高校。2000年原重庆建筑大学、重庆建筑高等专科学校与重庆大学合并组建为新的重庆大学，2001年成为“985工程”重点建设高校。2017年入选国家“世界一流大学建设高校（A类）”，2022年入选第二轮“双一流”建设高校。学校以工科为主，涵盖理、工、经、管、法、文、史、哲、医、教育、艺术等12个学科门类。现有7个学部、35个学院和8所附属医院。校园占地面积5300余亩，拥有多个校区。作为国家“双一流”高校，重庆大学在全国享有盛誉，拥有5300余名教职工和5万余名在校学生。`,
      currentPage: 'home', // 初始化为首页
      initialPage: 'home',
      isFullScreenCalendarVisible: false, // 控制全屏校历显示的变量
    };
  },
  methods: {
    goToHomePage() {
      this.currentPage = 'home'; // 上一页
	  window.scrollTo({ top: 0, behavior: 'smooth' });
    },
    goToXiaoliPage() {
      this.currentPage = 'xiaoli'; // 切换到校历内容
    },
    goToContactPage() {
      this.currentPage = 'contact'; // 切换到师资力量及教学设施内容
    },
    goToInitialPage() {
      this.$router.push('/'); // 使用Vue Router返回首页
    },
    showFullScreenCalendar() {
      this.isFullScreenCalendarVisible = true; // 显示全屏校历
    },
    hideFullScreenCalendar() {
      this.isFullScreenCalendarVisible = false; // 隐藏全屏校历
    }
  }
}
</script>

<style scoped>
.school-introduction {
  text-align: center;
  padding: 30px;
  background-color: #f9f9f9; /* 背景颜色 */
  border-radius: 10px; /* 圆角边框 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 添加阴影 */
}

.school-introduction h1 {
  font-size: 48px; /* 调整标题字体大小 */
  font-weight: bold;
  color: #0073e6; /* 标题颜色 */
  margin-bottom: 20px; /* 标题下方间距 */
}

.school-content, .xiaoli-content, .contact-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  background: #ffffff; /* 内容背景色 */
  padding: 20px;
  border-radius: 10px; /* 圆角边框 */
  margin-bottom: 20px; /* 内容下方间距 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 内容块阴影 */
}

.school-image {
  max-width: 100%;
  height: auto;
  margin-top: 20px;
  border-radius: 10px; /* 图片圆角 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 图片阴影 */
}

.fullscreen-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.8); /* 半透明背景 */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999; /* 确保在最上层 */
}

.fullscreen-image {
  max-width: 90%;
  max-height: 90%;
  border-radius: 10px; /* 图片圆角 */
}

p {
  font-size: 18px; /* 字体大小 */
  line-height: 1.8; /* 行高 */
  color: #333333; /* 字体颜色 */
  max-width: 800px; /* 段落最大宽度 */
  margin: 10px auto;
  text-align: justify; /* 两端对齐 */
}

.button-group {
  margin-top: 20px;
}

button {
  margin: 5px;
  padding: 10px 20px;
  font-size: 16px;
  color: #ffffff; /* 按钮文字颜色 */
  background-color: #0073e6; /* 按钮背景色 */
  border: none;
  border-radius: 5px;
  cursor: pointer;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 按钮阴影 */
  transition: background-color 0.3s ease; /* 交互效果 */
}

button:hover {
  background-color: #005bb5; /* 按钮悬停时背景色 */
}
</style>
