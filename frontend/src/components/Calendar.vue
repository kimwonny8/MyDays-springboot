<template>
    <div>
       <!-- 상단 메뉴부분  -->
      <div class="monthChange">
        <!-- 현재 년, 달 적혀있는 div -->
        <div class="currentYM">
            <button class="currentYMBtn" @click="calendarData(-1)">◀</button>
            {{ year }}년 {{ month }}월
            <button class="currentYMBtn" @click="calendarData(1)">▶️</button>
          </div>
      </div>
      <router-link to="/post" >일기 쓰기</router-link>
      <br>
      <router-link to="/diaryList">리스트로 보기</router-link>
      <!-- 달력부분 -->
      <table class="calender">
        <!-- 요일 day -->
        <thead>
          <th v-for="day in days" :key="day">{{ day }}</th>
        </thead>
  
        <!-- 하루 date -->
        <tbody>
          <tr v-for="(date, idx) in dates" :key="idx">
            <!-- 오늘날짜에 파란색 -->
            <td
              v-for="(day, secondIdx) in date"
              :key="secondIdx"
              :class="{
                colorWhite:
                  (idx === 0 && day >= lastMonthStart) ||
                  (dates.length - 1 === idx && nextMonthStart > day),
                colorBlue:
                  day === today && month === currentMonth && year === currentYear
              }"
            >
              <div class="oneDay">{{ day }}
              <div v-if="chkDiary(day)" class="photoDay">{{ photo }}</div>
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
        email: sessionStorage.getItem("email"),
        photo: null
      };
    },
    created() {
      axios.get("/api/diary/list", { params: { email: this.email } })
            .then((res) => {
            for (let i = 0; i < res.data.length; i++) {
                this.diaryList[i] = res.data[i];
            }
        })
            .catch((err) => {
            console.log(err);
        });

      const date = new Date();
      this.currentYear = date.getFullYear(); // 이하 현재 년, 월 가지고 있기
      this.currentMonth = date.getMonth() + 1;
      this.year = this.currentYear;
      this.month = this.currentMonth;
      this.today = date.getDate(); // 오늘 날짜
      this.calendarData();
  
      this.name = localStorage.getItem("name");  
    },
    components: {
      
    },
    computed: {
      totalDate() {
      var date = this.year + "-0" + this.month;
      return date;
    }
    },
    methods: {
      chkDiary(arg) {
        let day = 0; 
        if(arg<10) day="0"+arg;
        else day=arg;
       
        const tmp=this.totalDate+"-"+day;

        for(let i=0; i<this.diaryList.length; i++){
            if(tmp == this.diaryList[i].date){
              if(this.diaryList[i].photo=="" || this.diaryList[i].photo==null) {
                this.photo="사진 없음"
              }    
              else {
                this.photo= this.diaryList[i].photo;
              } 
              return true
          }
        }
      },
      // 달력 찍기
      calendarData(arg) {
        if (arg < 0) {
          // -1이 들어오면 지난 달 달력으로 이동
          this.month -= 1;
        } else if (arg === 1) {
          // 1이 들어오면 다음 달 달력으로 이동
          this.month += 1;
        }
  
        if (this.month === 0) {
          // 작년 12월
          this.year -= 1;
          this.month = 12;
        } else if (this.month > 12) {
          // 내년 1월
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
        const firstDay = new Date(year, month - 1, 1).getDay(); // 이번 달 시작 요일
        const lastDate = new Date(year, month, 0).getDate(); // 이번 달 마지막 날짜
        let lastYear = year;
        let lastMonth = month - 1;
        if (month === 1) {
          lastMonth = 12;
          lastYear -= 1;
        }
        const prevLastDate = new Date(lastYear, lastMonth, 0).getDate(); // 지난 달 마지막 날짜
        return [firstDay, lastDate, prevLastDate];
      },
      getMonthOfDays(monthFirstDay, monthLastDate, prevMonthLastDate) {
        let day = 1;
        let prevDay = prevMonthLastDate - monthFirstDay + 1;
        const dates = [];
        let weekOfDays = [];
        while (day <= monthLastDate) {
          if (day === 1) {
            // 1일이 어느 요일인지에 따라 테이블에 그리기 위한 지난 셀의 날짜들을 구할 필요가 있다.
            for (let j = 0; j < monthFirstDay; j += 1) {
              if (j === 0) this.lastMonthStart = prevDay; // 지난 달에서 제일 작은 날짜
              weekOfDays.push("");
            }
          }
          weekOfDays.push(day);
          if (weekOfDays.length === 7) {
            // 일주일 채우면
            dates.push(weekOfDays);
            weekOfDays = []; // 초기화
          }
          day += 1;
        }
        const len = weekOfDays.length;
        if (len > 0 && len < 7) {
          for (let k = 1; k <= 7 - len; k += 1) {
            weekOfDays.push("");
          }
        }
        if (weekOfDays.length > 0) dates.push(weekOfDays); // 남은 날짜 추가
        this.nextMonthStart = weekOfDays[0]; // 이번 달 마지막 주에서 제일 작은 날짜
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
  .photoDay{
    width: 100px;
    height: 100px;
    padding: 5px;
  }

  </style>
  