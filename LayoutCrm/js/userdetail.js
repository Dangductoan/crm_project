$(document).ready(function () {
    var userId = localStorage.getItem("userId")
    console.log(userId)
    $.ajax({
      url:`http://localhost:8080/crm/api/user/by-id?id=${userId}`,
      method:'GET',
  
  
     }).done(function(result) {
      console.log(result);
      $(".profile-user").empty()
  
             var row = `<h4 class="text-white">${result.fullName}</h4>
                        <h5 class="text-white">${result.email}</h5>`
          $(".profile-user").append(row);
         
     })
     $.ajax({
        url:'http://localhost:8080/crm/api/task',
        method:'GET',
    
    
       }).done(function(result) {
        var taskOfUser = result.filter(t => t.userId == userId);
        var taskOfUserFinished = taskOfUser.filter(t => t.statusId === 3)
        var taskOfUserDoing = taskOfUser.filter(t => t.statusId === 2)
        var taskOfUserUnStarted = taskOfUser.filter(t => t.statusId === 1)
        var percentFinished = taskOfUserFinished.length/taskOfUser.length*100 + "%"
        var percentDoing = taskOfUserDoing.length/taskOfUser.length*100 + "%"
        var percentUnStarted = taskOfUserUnStarted.length/taskOfUser.length*100 + "%"
        console.log(percentFinished, percentDoing, percentUnStarted)
        $(".unStarted").empty()
        $(".doing").empty()
        $(".finished").empty()
        $(".percent-unStarted").empty()
        $(".percent-doing").empty()
        $(".percent-finished").empty()
              var rowUnStarted ;
              var rowDoing ;
              var rowFinished ;
              var rowPercentUnStarted = `
              <div class="white-box">
				<div class="col-in row">
					<div class="col-xs-12">
						<h3 class="counter text-right m-t-15 text-danger">${percentUnStarted}</h3>
                    </div>
                    <div class="col-xs-12">
						<i data-icon="E" class="linea-icon linea-basic"></i>
						<h5 class="text-muted vb text-center">CHƯA BẮT ĐẦU</h5>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="progress">
							<div class="progress-bar progress-bar-danger" role="progressbar"
								aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
								style="width: ${percentUnStarted}"></div>
						</div>
					</div>
				</div>
			</div>`;
              var rowPercentDoing  = `<div class="white-box">
              <div class="col-in row">
                  <div class="col-xs-12">
                      <h3 class="counter text-right m-t-15 text-megna">${percentDoing}</h3>
                  </div>
                  <div class="col-xs-12">
                      <i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>
                      <h5 class="text-muted vb text-center">ĐANG THỰC HIỆN</h5>
                  </div>
                  <div class="col-md-12 col-sm-12 col-xs-12">
                      <div class="progress">
                          <div class="progress-bar progress-bar-megna" role="progressbar"
                              aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                              style="width:${percentDoing}"></div>
                      </div>
                  </div>
              </div>
          </div>`;
              var rowPercentFinished = `<div class="white-box">
              <div class="col-in row">
                  <div class="col-xs-12">
                      <h3 class="counter text-right m-t-15 text-primary">${percentFinished}</h3>
                  </div>
                  <div class="col-xs-12">
                      <i class="linea-icon linea-basic" data-icon="&#xe00b;"></i>
                      <h5 class="text-muted vb text-center">HOÀN THÀNH</h5>
                  </div>
                  <div class="col-md-12 col-sm-12 col-xs-12">
                      <div class="progress">
                          <div class="progress-bar progress-bar-primary" role="progressbar"
                              aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                              style="width: ${percentFinished}"></div>
                      </div>
                  </div>
              </div>
          </div>`;
              if(taskOfUserUnStarted.length === 0) {
                rowUnStarted = "";
              }else {
                taskOfUserUnStarted.forEach(task => {
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
              if(taskOfUserDoing.length === 0) {
                rowDoing = "";
              }else {
                taskOfUserDoing.forEach(task => {
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
              if(taskOfUserFinished.length === 0) {
                rowFinished = "";
              }else {
                taskOfUserFinished.forEach(task => {
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
              $(".unStarted").append(rowUnStarted)
              $(".doing").append(rowDoing)
              $(".finished").append(rowFinished)
              $(".percent-unStarted").append(rowPercentUnStarted)
              $(".percent-doing").append(rowPercentDoing)
              $(".percent-finished").append(rowPercentFinished)
           
       })

})
function ConvertDateToString(date) {
    return (
      `${date.day}/${date.month}/${date.year}`
    )
  }