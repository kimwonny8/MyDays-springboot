import { createApp } from 'vue'
import App from './App.vue'
import { store } from './store/store';
import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:9959/';
axios.defaults.headers.get['Content-Type'] = 'application/json';
axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.defaults.headers.put['Content-Type'] = 'application/json';

const app = createApp(App);

app.config.globalProperties.axios = axios;

app.use(store).mount('#app')