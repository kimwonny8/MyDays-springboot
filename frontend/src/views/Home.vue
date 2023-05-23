<template>
    <div>
        <login v-if="!$store.getters.isLogin"></login>
        <calendar v-else></calendar>
    </div>
</template>
<script>
import login from "@/components/Login.vue";
import calendar from "@/components/Calendar.vue";

export default {
    name: 'Home',
    components: {
        login, calendar
    }
}
</script>