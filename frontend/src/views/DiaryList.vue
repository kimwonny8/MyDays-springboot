<template>
    <div>
        <router-link to="/">달력으로 보기</router-link>
        <div v-if="this.diaryList.length===0">
                <p>등록된 일기가 없습니다.</p>
            </div>
        <table v-else>
            <tr>
                <th>날짜</th>
                <th>기분</th>
                <th>내용</th>
                <th>선택</th>
            </tr>
            <tr v-for="(value, key) in this.diaryList">
                <td>{{ value.date }}</td>
                <td>{{ value.face }}</td>
                <td>{{ value.content }}</td>
                <td><button @click="selectDiary(value.diaryIdx)">읽기</button></td>
            </tr>
        </table>
    </div>
</template>
<script>
import store from "@/scripts/store";
import router from "@/scripts/router";
import axios from "axios";
import { directive } from "@babel/types";

export default {
    name: "DiaryList",
    data() {
        return {
            diaryList: [],
            item: null,
            email: sessionStorage.getItem("email")
        };
    },
    created() {
        axios.get("/api/diary/list", { params: { email: this.email } })
            .then((res) => {
            for (let i = 0; i < res.data.length; i++) {
                //console.log(res.data[i]);
                this.diaryList[i] = res.data[i];
            }
            console.log(Object.values(this.diaryList[0]));
        })
            .catch((err) => {
            console.log(err);
        });
    },
    methods: {
        selectDiary(arg) {
            axios.get("/api/diary/select/" + arg)
                .then((res) => {
                console.log(res.data[0]);
                store.commit("setDiary", res.data[0]);
                sessionStorage.setItem("diaryIdx", store.state.diary.diaryIdx);
                router.push("/selectDiary");
            });
        }
    },
    components: { directive }
}

</script>
<style>

</style>