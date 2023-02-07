<template>
    <div>
        <h1>Calendar</h1>
        <router-link to="/post" >작성</router-link> 
        <table>
            <tr>
                <th>날짜</th>
                <th>기분</th>
                <th>내용</th>
            </tr>
            <tr v-for="(value, key) in this.diaryList">
                <td>{{ value.date }}</td>
                <td>{{ value.face }}</td>
                <td>{{ value.content }}</td>
            </tr>
        </table>
    </div>
</template>
<script>
import store from "@/scripts/store";
import router from "@/scripts/router";
import axios from "axios";

export default {
    name: 'Calendar',
    
    data() {
        return {
            diaryList: [],
            item: null,
            email:sessionStorage.getItem("email")
        }
    },

    async created() {
       await  axios.get("/api/diary/list", { params: { email:this.email  }})
            .then((res) => {
                for(let i=0; i<res.data.length; i++){
                    //console.log(res.data[i]);
                    this.diaryList[i] = res.data[i];
                }
               //console.log(Object.values(this.diaryList[0]));
            })
            .catch((err) => {
                console.log(err);
            })
    },

    methods: {
        fetchData() {
           
        
        }
    }

}

</script>
<style>

</style>