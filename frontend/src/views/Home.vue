<template>
    <div>
        <login v-if="!$store.state.user.name"></login>
        <calendar v-else></calendar>
    </div>
</template>
<script>
import {reactive} from "vue";
import axios from "axios";
import store from "@/scripts/store";
import router from "@/scripts/router";
import login from "@/components/Login.vue";
import calendar from "@/components/Calendar.vue";

export default {
    name: 'Home',
    components: {
        login, calendar
    }
}
</script>