<template>
  <div>
    <!-- 상단 메뉴부분  -->
    <div class="monthChange">
      <div class="currentYM">
        <button class="currentYMBtn" @click="calendarData(-1)">◀</button>
        {{ year }}년 {{ month }}월
        <button class="currentYMBtn" @click="calendarData(1)">▶️</button>
      </div>
    </div>
    <div class="menu">
      <router-link to="/post">일기 쓰기</router-link>
      <router-link to="/diaryList">리스트로 보기</router-link>
    </div>
    <!-- 달력부분 -->
    <table class="calender">
      <thead>
        <th v-for="day in days" :key="day">{{ day }}</th>
      </thead>

      <tbody>
        <tr v-for="(date, idx) in dates" :key="idx">
          <td v-for="(day, secondIdx) in date" :key="secondIdx" :class="{
            colorWhite:
              (idx === 0 && day >= lastMonthStart) ||
              (dates.length - 1 === idx && nextMonthStart > day),
            colorBlue:
              day === today && month === currentMonth && year === currentYear
          }">
            <div class="oneDay" @click="selectDiary(day)">{{ day }}
              <div v-if="chkExercise(day)" class="dayBox exerciseBox">
                {{ exercise }}
              </div>
              <div v-if="chkDiary(day)" class="dayBox">{{ face }}</div>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
  
<script>
import store from "@/scripts/store";
import router from "@/scripts/router";
import axios from "axios";

export default {
  name: "calendar",
  data() {
    return {

      days: [
        "일요일",
        "월요일",
        "화요일",
        "수요일",
        "목요일",
        "금요일",
        "토요일"
      ],
      dates: [],
      currentYear: 0,
      currentMonth: 0,
      lastMonthStart: 0,
      nextMonthStart: 0,
      year: 0,
      month: 0,
      today: 0,
      diaryList: [],
      email: this.$store.state.user.email,
      face: null,
      exercise: null,
    };
  },
  async created() {
    await this.getDiary();
    const date = new Date();
    this.currentYear = date.getFullYear();
    this.currentMonth = date.getMonth() + 1;
    this.year = this.currentYear;
    this.month = this.currentMonth;
    this.today = date.getDate();
    this.calendarData();

    this.name = localStorage.getItem("name");
  },
  computed: {
    totalDate() {
      var date = this.year + "-0" + this.month;
      return date;
    }
  },
  methods: {
    async getDiary() {
      try {
        const res = await axios.get(`${process.env.VUE_APP_API_PATH}/api/v1/diary`, {
          params: { email: this.email },
          headers: this.$store.getters.headers
        });
        for (let i = 0; i < res.data.length; i++) {
          this.diaryList[i] = res.data[i];
        }
      }
      catch (err) {
        console.log(err);
        if (err.response && err.response.status === 401) {
          try {
            const accessTokenUpdated = await this.$store.dispatch("getAccessToken");
            if (accessTokenUpdated) {
              await this.getDiary();
            } else {
              store.commit('setAccessTokenAndUser', null);
            }
          } catch (err) {
            console.log(err);
          }
        }
      }
    },
    async selectDiary(arg) {
      let date;
      if (arg < 10) {
        date = this.totalDate + "-0" + arg;
      }
      else {
        date = this.totalDate + "-" + arg;
      }

      console.log(date);
      const res = await axios.get(`${process.env.VUE_APP_API_PATH}/api/v1/diary/date/` + date, {
        headers: this.$store.getters.headers
      })
      try {
        console.log(res.data[0]);
        store.commit("setDiary", res.data[0]);
        sessionStorage.setItem("diaryIdx", store.state.diary.diaryIdx);
        router.push("/selectDiary");
      }
      catch (err) {
        console.log(err);
        if (err.response && err.response.status === 401) {
          try {
            const accessTokenUpdated = await this.$store.dispatch("getAccessToken");
            if (accessTokenUpdated) {
              await this.selectDiary(arg);
            } else {
              store.commit('setAccessTokenAndUser', null);
            }
          } catch (err) {
            console.log(err);
          }
        }
        else alert("등록된 일기가 없습니다.")
      };
    },
    chkDiary(arg) {
      let day = 0;
      if (arg < 10) day = "0" + arg;
      else day = arg;

      const tmp = this.totalDate + "-" + day;

      for (let i = 0; i < this.diaryList.length; i++) {
        if (tmp == this.diaryList[i].date) {
          if (this.diaryList[i].face == "" || this.diaryList[i].face == null) {
            this.face = "기분 없음"
          }
          else {
            this.face = this.diaryList[i].face;
          }
          return true
        }
      }
    },
    chkExercise(arg) {
      let day = 0;
      if (arg < 10) day = "0" + arg;
      else day = arg;

      const tmp = this.totalDate + "-" + day;

      for (let i = 0; i < this.diaryList.length; i++) {
        if (tmp == this.diaryList[i].date) {
          if (this.diaryList[i].exercise == "" || this.diaryList[i].exercise == null) {
            this.exercise = "X"
          }
          else {
            this.exercise = this.diaryList[i].exercise;
          }
          return true
        }
      }
    },
    // 달력 찍기
    calendarData(arg) {
      if (arg < 0) {
        this.month -= 1;
      } else if (arg === 1) {
        this.month += 1;
      }

      if (this.month === 0) {
        this.year -= 1;
        this.month = 12;
      } else if (this.month > 12) {
        this.year += 1;
        this.month = 1;
      }
      console.log(this.year + " " + this.month);

      const [
        monthFirstDay,
        monthLastDate,
        lastMonthLastDate
      ] = this.getFirstDayLastDate(this.year, this.month);
      this.dates = this.getMonthOfDays(
        monthFirstDay,
        monthLastDate,
        lastMonthLastDate
      );
    },
    getFirstDayLastDate(year, month) {
      const firstDay = new Date(year, month - 1, 1).getDay();
      const lastDate = new Date(year, month, 0).getDate();
      let lastYear = year;
      let lastMonth = month - 1;
      if (month === 1) {
        lastMonth = 12;
        lastYear -= 1;
      }
      const prevLastDate = new Date(lastYear, lastMonth, 0).getDate();
      return [firstDay, lastDate, prevLastDate];
    },
    getMonthOfDays(monthFirstDay, monthLastDate, prevMonthLastDate) {
      let day = 1;
      let prevDay = prevMonthLastDate - monthFirstDay + 1;
      const dates = [];
      let weekOfDays = [];
      while (day <= monthLastDate) {
        if (day === 1) {
          for (let j = 0; j < monthFirstDay; j += 1) {
            if (j === 0) this.lastMonthStart = prevDay;
            weekOfDays.push("");
          }
        }
        weekOfDays.push(day);
        if (weekOfDays.length === 7) {
          dates.push(weekOfDays);
          weekOfDays = [];
        }
        day += 1;
      }
      const len = weekOfDays.length;
      if (len > 0 && len < 7) {
        for (let k = 1; k <= 7 - len; k += 1) {
          weekOfDays.push("");
        }
      }
      if (weekOfDays.length > 0) dates.push(weekOfDays);
      this.nextMonthStart = weekOfDays[0];
      return dates;
    }
  }
};
</script>
  
<style>
* {
  margin: 0 auto;
  --bodyWidth: 900px;
}

.monthChange {
  display: flex;
  width: var(--bodyWidth);
  margin-bottom: 10px;
  justify-content: space-between;
}

.currentYM {
  margin-left: 3vw;
}

.currentYMBtn {
  border: none;
  background-color: white;
}

.currentYMBtn:hover {
  color: rgb(255, 106, 0);
}

.calender {
  width: var(--bodyWidth);
  border: 1px solid gray;
  border-collapse: collapse;
}

th {
  background-color: rgb(202, 202, 202);
  border: 1px solid gray;
}

td {
  border: 1px solid gray;
}

.colorWhite {
  color: white;
}

.colorBlue {
  color: rgb(0, 0, 255);
}

.oneDay {
  height: 120px;
  width: 120px;
}

.dayBox {
  width: 100px;
  height: 40px;
  padding: 5px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.exerciseBox {
  margin-top: 5px;
  height: 20px;
  background-color: yellow;
}

.menu {
  display: flex;
  flex-direction: row;
  width: 200px;
  margin-top: 15px;
  margin-bottom: 15px;
}
</style>
  