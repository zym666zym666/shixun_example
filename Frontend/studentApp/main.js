import {
	createSSRApp
} from 'vue'
import App from './App.vue'
import {
	createStore
} from 'vuex'; //新增全局状态的存储工具
// 创建 Vuex store
const store = createStore({
	state() {
		return {
			tel: uni.getStorageSync('userTel') || '' // 从本地存储中恢复 tel
		};
	},
	mutations: {
		setTel(state, tel) {
			state.tel = tel;
			uni.setStorageSync('userTel', tel);
		}
	},
	getters: {
		tel(state) {
			return state.tel;
		}
	}
});
                        
export function createApp() {
	const app = createSSRApp(App)
	app.use(store); // 使用 Vuex store
	return {
		app
	}
}