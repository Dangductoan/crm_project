$(document).ready(function () {
   var jobId = parseInt(localStorage.getItem('jobId'));
   console.log(jobId);
   $.ajax({
    url:"http://localhost:8080/crm/api/task",
    method:'GET',


   }).done(function(tasks) {
    $(".container-fluid").empty()
   
   // $(".row-job").empty()
    var taskOfJob = tasks.filter(t => t.jobId === jobId);
    var taskOfJobFinished = taskOfJob.filter(t => t.statusId === 3)
    var taskOfJobDoing = taskOfJob.filter(t => t.statusId === 2)
    var taskOfJobUnStarted = taskOfJob.filter(t => t.statusId === 1)
    var percentFinished = taskOfJobFinished.length/taskOfJob.length*100 + "%"
    var percentDoing = taskOfJobDoing.length/taskOfJob.length*100 + "%"
    var percentUnStarted = taskOfJobUnStarted.length/taskOfJob.length*100 + "%"
    console.log(tasks,taskOfJob)
    var row = ` <div class="row bg-title">
    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
        <h4 class="page-title">Chi tiết công việc </h4>
    </div>
    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
        <ol class="breadcrumb">
            <li><a href="#">Dashboard</a></li>
            <li class="active">Blank Page</li>
        </ol>
    </div>

</div>
<div class="row row-statistical
">
    <!--col -->
    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
        <div class="white-box">
            <div class="col-in row">
                <div class="col-md-6 col-sm-6 col-xs-6"> <i data-icon="E"
                        class="linea-icon linea-basic"></i>
                    <h5 class="text-muted vb">CHƯA BẮT ĐẦU</h5>
                </div>
                <div class="col-md-6 col-sm-6 col-xs-6">
                    <h3 class="counter text-right m-t-15 text-danger">${percentUnStarted}</h3>
                </div>
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="progress">
                        <div class="progress-bar progress-bar-danger" role="progressbar"
                            aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: ${percentUnStarted}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.col -->
    <!--col -->
    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
        <div class="white-box">
            <div class="col-in row">
                <div class="col-md-6 col-sm-6 col-xs-6"> <i class="linea-icon linea-basic"
                        data-icon="&#xe01b;"></i>
                    <h5 class="text-muted vb">ĐANG THỰC HIỆN</h5>
                </div>
                <div class="col-md-6 col-sm-6 col-xs-6">
                    <h3 class="counter text-right m-t-15 text-megna">${percentDoing}</h3>
                </div>
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="progress">
                        <div class="progress-bar progress-bar-megna" role="progressbar"
                            aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: ${percentDoing}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.col -->
    <!--col -->
    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
        <div class="white-box">
            <div class="col-in row">
                <div class="col-md-6 col-sm-6 col-xs-6"> <i class="linea-icon linea-basic"
                        data-icon="&#xe00b;"></i>
                    <h5 class="text-muted vb">HOÀN THÀNH</h5>
                </div>
                <div class="col-md-6 col-sm-6 col-xs-6">
                    <h3 class="counter text-right m-t-15 text-primary">${percentFinished}</h3>
                </div>
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="progress">
                        <div class="progress-bar progress-bar-primary" role="progressbar"
                            aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: ${percentFinished}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.col -->
</div>`
$(".container-fluid").append(row);
if(taskOfJobUnStarted.length === 0) {
    rowUnStarted = "";
  }else {
    taskOfJobUnStarted.forEach(task => {
        rowUnStarted = ` <a href="#">
        <div class="mail-contnet">
            <h5>${task.name}</h5>
            <span class="mail-desc"></span>
            <span class="time">Bắt đầu: ${ConvertDateToString(task.startDate.date)}</span>
            <span class="time">Kết thúc: ${ConvertDateToString(task.endDate.date)}</span>
        </div>
    </a> `;
    })
  }
  if(taskOfJobDoing.length === 0) {
    rowDoing = "";
  }else {
    taskOfJobDoing.forEach(task => {
        rowDoing = ` <a href="#">
        <div class="mail-contnet">
            <h5>${task.name}</h5>
            <span class="mail-desc"></span>
            <span class="time">Bắt đầu: ${ConvertDateToString(task.startDate.date)}</span>
            <span class="time">Kết thúc: ${ConvertDateToString(task.endDate.date)}</span>
        </div>
    </a> `;
    })
  }
  if(taskOfJobFinished.length === 0) {
    rowFinished = "";
  }else {
    taskOfJobFinished.forEach(task => {
        rowFinished = ` <a href="#">
        <div class="mail-contnet">
            <h5>${task.name}</h5>
            <span class="mail-desc"></span>
            <span class="time">Bắt đầu: ${ConvertDateToString(task.startDate.date)}</span>
            <span class="time">Kết thúc: ${ConvertDateToString(task.endDate.date)}</span>
        </div>
    </a> `;
    })
  }
     taskOfJob.forEach(task => {
        var rowListJob;
       
        $.ajax({
            url:`http://localhost:8080/crm/api/user/by-id?id=${task.userId}`,
            method:'GET',
            success: function(user) {
                console.log(user);
                rowListJob = ` <div class="row row-job">
                <div class="col-xs-12">
                    <a href="#" class="group-title">
                        <img width="30" src="plugins/images/users/pawandeep.jpg" class="img-circle" />
                        <span>${user.fullName}</span>
                    </a>
                </div>
                <div class="col-md-4">
                    <div class="white-box">
                        <h3 class="box-title">Chưa thực hiện</h3>
                        <div class="message-center">
                           ${rowUnStarted}
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="white-box">
                        <h3 class="box-title">Đang thực hiện</h3>
                        <div class="message-center">
                           ${rowDoing}
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="white-box">
                        <h3 class="box-title">Đã hoàn thành</h3>
                        <div class="message-center">
                           ${rowFinished}
                        </div>
                    </div>
                </div>
            </div>`
        $(".container-fluid").append(rowListJob);
                 
            }
        })
     })
    })

})
function ConvertDateToString(date) {
    return (
      `${date.day}/${date.month}/${date.year}`
    )
  }