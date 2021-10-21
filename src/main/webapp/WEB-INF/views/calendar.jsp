<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- fullcalender css, js cdn -->
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.0/main.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.0/main.min.css">

    <!-- jquery -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>

    <script>
        const empNo = '${empNo}';
        $(document).ready(function(){
            loadingCalendar();
            $("input[name='type']").change(function() {
                loadingCalendar();
            });

            empInfo();
        });

        function empInfo() {
            fetch('/get-empInfo/${empNo}')
            .then(res => res.json())
            .then(info => {
                console.log(info);
                let infoStr = info.dep.dptName + " " + info.empName + " " + info.pos.posName + "님 안녕하세요"
                $('#emp-info').html(infoStr);
            })
        }

        //document.addEventListener('DOMContentLoaded', function() {
        function loadingCalendar() {
            var calendarEl = document.getElementById('calendar');

            var calendar = new FullCalendar.Calendar(calendarEl, {
                
                initialView: 'dayGridMonth',
                customButtons: {
                    add_event: {
                        text: '일정추가',
                        click: function() {
                            location.href = '/schedule/add/' + empNo
                        }
                    }
                },
                headerToolbar: {
                    left: 'prev,next today add_event',
                    center: 'title',
                    right: 'dayGridMonth,timeGridDay'
                },
                buttonText: {
                    today : '오늘',
                    month : '월',
                    day : '일'
                },
                navLinks: true, // can click day/week names to navigate views
                editable: true,
                dayMaxEvents: true, // 셀 크기보다 많은 이벤트 동록되어 있는 경우 'more'로 표기함
                selectable: true,
                contentHeight:"auto",
                locale: 'ko',
                eventClick: function(arg) {  // 이벤트 클릭시
                    location.href = '/schedule/modify/' + empNo + '?scdNo=' + arg.event.id;
                },
                events: function(info, successCallback, failureCallback){ // ajax 처리로 데이터를 로딩 시킨다. 
                    var events = [];
                    var types = "";
                    $("input[name='type']:checked").each(function() {
                        types += $(this).val() + " ";
                        
                    });
                    var type = {"type": types};
                    $.ajax({
                        type:"GET",
                        data: type,
                        url:"/schedule/get-schedule/" + empNo, 
                        dataType:"json",
                        success: function(result){
                            $.each(result, function(index, schedule) {
                                events.push({
                                                id: schedule.scdNo,
                                                title: schedule.scdTitle,
                                                start: schedule.start,
                                                end: schedule.end,
                                                color: schedule.color                                               
                                            }); //.push()
                            });
                        console.log(events);
                        successCallback(events);
                        }
                    }); 
                    
                }
            });

            calendar.render();
        }

        function calDateWhenResize(event) {

            var newDates = {
                startDate: '',
                endDate: ''
            };

            newDates.startDate = moment(event.start._d).format('YYYY-MM-DD HH:mm');
            newDates.endDate = moment(event.end._d).format('YYYY-MM-DD HH:mm');

            return newDates;
        }

    </script>
    <title></title>
</head>
<body>
    <div>
        <span id="emp-info"></span>
    </div>
    <label>
        #일정 구분: 
        <input type="checkbox" name="type" value="C" checked> 회사 일정
        <input type="checkbox" name="type" value="D"> 부서 일정
        <input type="checkbox" name="type" value="P"> 개인 일정
    </label>
    <div id='calendar'></div>
</body>
</html>