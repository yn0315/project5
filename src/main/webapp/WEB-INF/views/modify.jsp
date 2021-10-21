<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
   <meta charset="UTF-8">
   <title>Insert title here</title>
   <script src="http://code.jquery.com/jquery-latest.js"></script>

   <style>
      /* reset */
      a {
         color: inherit;
         text-decoration: none;
      }
      input,
      button {
         /* background: none; */
         outline: none;
         border: none;
      }
      /* layout */
      .wrapper {
         background: #e9ecef;
         padding-top: 64px;
         height: 200vh;
      }
      section.todo-template {
         /* background: rgb(48, 44, 43); */
         width: 512px;
         border-radius: 5px;
         margin: 0 auto;
         overflow: hidden;
         /* box-shadow: 0 0 10px rgba(0, 0, 0, 0.8); */
      }
      section.todo-template .app-title {
         background: #505050;
         color: #fff;
         height: 64px;
         font-size: 1.5em;
         display: flex;
         justify-content: center;
         align-items: center;
      }
      section.todo-template .content {
         background: rgb(141, 138, 138);
         height: 800px;
         text-align: center;
         
      }
      section.todo-template .content .todo-insert input.form-control {
         margin-top: 10px;
         width: 280px;
         padding: 8px;
         color: #000;
      }
      span {
         color: #fff;
         font-weight: bold;
         font-size: 18px;
      }
      span.code {
         font-size: 16px;
         font-weight: normal;
         margin-right: 5px;
      }
   </style>
</head>

<body>

   <div class="wrapper">
      <section class="todo-template">
         <h1 class="app-title">스케줄 수정</h1>
         <div class="content">

            <!-- form으로 구성해놔야 서버로 보낼 수 있음 -->
            <form class="todo-insert" action="/schedule/mod-schedule/${empNo}" method="POST">
               <input type="hidden" name="scdNo" value="${schedule.scdNo}">
               <!-- autocomplete off는 자동완성 끔 -->
               <label class="scheduleTitle">
                  <br>
                  <span class="text">## 일정입력 ##</span><br>
                  <input type="text" class="form-control" name="scdTitle"  value="${schedule.scdTitle}"><br>
               </label>

               <label class="schedule daytime">
                  <span class="text">## 시작날짜 ##</span><br>
                  <input type="date" class="form-control" name="startDay" value="${schedule.startDay}"><br>

                  <span class="text">## 시작시간 ##</span><br>
                  <input type="time" class="form-control" name="startTime" value="${schedule.startTime}"><br>
               </label>
               <label class="schedule daytime">
                  <span class="text">## 종료날짜 ##</span><br>
                  <input type="date" class="form-control" name="endDay" value="${schedule.endDay}"><br>

                  <span class="text">## 종료시간 ##</span><br>
                  <input type="time" class="form-control" name="endTime" value="${schedule.endTime}"><br>
               </label>
               <label class="schedule radio">
                  <span class="text">## 일정구분 ##</span><br>
                  <br>
                  <input id="C-type" type="radio" name="type" value="C" disabled> <span class="code">회사 일정</span>
                  <input id="D-type" type="radio" name="type" value="D" disabled> <span class="code">부서 일정</span>
                  <input id="P-type" type="radio" name="type" value="P" disabled> <span class="code">개인 일정</span>
                  <br>
                  <br>
                  <button type="submit" class="btn btn-dark">수정</button>
                  <button type="button"class="btn btn-dark"  onclick="location.replace('/schedule/calendar/${empNo}') ">뒤로가기</button>
                  <button id="delBtn">삭제</button>
               </label>

            </form>

         </div>
      </section>
      
   </div>
<!--    
   <div class="write-container">
      <h1>일정 수정</h1>

      <form class="write-form" action="/schedule/mod-schedule/${empNo}" method="POST">
         <div class="input-box">
            <input type="hidden" name="scdNo" value="${schedule.scdNo}">
            
            # 제목: <input class="form-control" type="text" name="scdTitle" value="${schedule.scdTitle}"><br>

            # 시작날짜: <input type="date" name="startDay" value="${schedule.startDay}"><br>
            # 시작시간: <input type="time" name="startTime" value="${schedule.startTime}"><br>
            # 종료날짜: <input type="date" name="endDay" value="${schedule.endDay}"><br>
            # 종료시간: <input type="time" name="endTime" value="${schedule.endTime}"><br>

            <label>
                #일정 구분: 
                <input id="C-type" type="radio" name="type" value="C" disabled> 회사 일정
                <input id="D-type" type="radio" name="type" value="D" disabled> 부서 일정
                <input id="P-type" type="radio" name="type" value="P" disabled> 개인 일정
            </label>

         </div>
         <button type="submit">수정</button>
         
      </form>
      <button type="button" onclick="location.replace('/schedule/calendar/${empNo}') ">취소</button>
      <button id="delBtn">삭제</button>
   </div> -->

</body>

<script>
   $(function() {
      // 라디오 체크 부여
      let type = '${schedule.scdCode}'.substr(0,1);
      let checkbox = '#' + type + '-type';
      $(checkbox).prop('checked', true);

      // 삭제 버튼 클릭시 삭제

      $('#delBtn').on('click', e =>{
        
         fetch('/schedule/del-schedule/${schedule.scdNo}')
            .then(res => res.text())
            .then(msg => {
               if(msg === 'deleteSuccess') {
                  location.replace('/schedule/calendar/${empNo}')
               }
            })
      });
   })
   
   

</script>

</html>