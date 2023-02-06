<template>
    <div>
        <h1>Home</h1>
        <login v-if="!$store.state.user.name"></login>
        <calendar v-else></calendar>
    </div>
</template>
<script>
import {reactive} from "vue";
import axios from "axios";
import store from "@/scripts/store";
import router from "@/scripts/router";
import login from "@/views/Login.vue";
import calendar from "@/views/Calendar.vue";

export default {
    name: 'Home',
    components: {
        login, calendar
    }
}
</script>