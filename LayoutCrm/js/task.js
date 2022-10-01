

$(document).ready(function() {
    //get all task
    $.ajax({
        url:"http://localhost:8080/crm/api/task",
        method:"GET"
    }).done(function(result) {
       $("#example tbody").empty();
       $.each(result,function(index,value) {
        console.log(value);
        //get user by id
        $.ajax({
            url:`http://localhost:8080/crm/api/user/by-id?id=${value.userId}`,
            method:"GET",
            success:function(data) {
                var username = data.fullName;
                GetJobById(value,username)
            }
        })
       
       })
    })
    //get all job
    $.ajax({
      url:"http://localhost:8080/crm/api/job",
      method:"GET"
  }).done(function(result) {
     $("#job-id").empty();
     $.each(result,function(index,value) {
      console.log(value);
       
      
      var row = `  <option value=${value.id}>${value.name}</option> `
 
  $("#job-id").append(row);
     })
  })
    //get all users
  
         $.ajax({
          method:"GET",
          url:"http://localhost:8080/crm/api/user",
          success:function(result) {
              $("#user-id").empty();
              $.each(result,function(index,value) {
                var row = `<option value=${value.id}>${value.fullName}</option>`
            $("#user-id").append(row);
              })
              
          }
         })
   
      //add role
   $('#btn-save-task').click(function(e) {
    e.preventDefault();

    var name  = $('#name').val()
    var startDate = $('#start-date').val();
    var endDate = $('#end-date').val();
    var jobId = $('#job-id').val();
    var userId = $('#user-id').val();
    //location.reload();
    
   
    $.ajax({
      url:"http://localhost:8080/crm/api/task",
      method:'POST',
      datatype: 'json',
      data : {
        name:name,
        startDate:startDate,
        endDate:endDate,
        jobId:jobId,
        userId:userId,
        statusId:1
      }
  
  
     }).done(function(result) {
       
         if(result.isSuccess === true) {
             console.log("them thanh cong")
         }else {
           console.log("them that bai")
         }
     
         
     })
  })
    //delete job 
    $("body").on('click',".btn-delete",function() {
        var taskId  = $(this).attr("task-id")
        //location.reload();
        var This = $(this)
       
        $.ajax({
          url:`http://localhost:8080/crm/api/task?id=${taskId}`,
          method:'DELETE',
      
      
         }).done(function(result) {
           
             if(result.isSuccess === true) {
                 This.closest('tr').remove();
             }else {
               console.log("xoa that bai")
             }
         
             
         })
     })
      //update task 
    //update task profile
    $(document).on('click', 'a.btn-update', function(e) {
        e.preventDefault(); 
        const taskId = e.target.getAttribute("task-id");
        localStorage.setItem("taskId", taskId);
        
        window.location.replace("http://127.0.0.1:5500/task-update.html")
    });
      $('#btn-update-task').click(function(e) {
        
        e.preventDefault();
        const taskId = localStorage.getItem("taskId")
        console.log(taskId)
        //location.reload();
       
        
         
         var name  = $('#name').val()
         var startDate = $('#start-date').val()
         var endDate = $('#end-date').val()
         var jobId = $('#job-id').val();
         var userId = $('#user-id').val();
         var statusId = $('#status-id').val()
         const data = 
         {
          id:taskId,
          name:name,
          startDate:startDate,
          endDate:endDate,
           userId:userId,
           jobId:jobId,
           statusId:statusId
       }
         console.log(data)
      
        $.ajax({
          url:"http://localhost:8080/crm/api/task",
          method:'PUT',
          datatype: 'json',
          data : JSON.stringify(data)
      
      
         }).done(function(result) {
           
             if(result.isSuccess === true) {
                 console.log("cap nhat thanh cong")
             }else {
               console.log("cap nhat that bai")
             }
         
             
         })
      })
})
function ConvertDateToString(date) {
    return (
      `${date.day}/${date.month}/${date.year}`
    )
  }
//get job by id
function GetJobById(value,username) {
  return $.ajax({
    url:`http://localhost:8080/crm/api/job/by-id?id=${value.jobId}`,
    method:"GET",
    success:function(job) {
        var jobName = job.name;
        GetStatusById(value,username,jobName)
    }
})
   
}
//get status by id
function GetStatusById(value,username,jobName) {
  return $.ajax({
    url:`http://localhost:8080/crm/api/status?id=${value.statusId}`,
    method:"GET",
    success:function(status) {
        var status = status.name;
        var row = ` <tr>
        <td>${value.id}</td>
        <td>${value.name}</td>
        <td>${jobName}</td>
        <td>${username}</td>
        <td>${ConvertDateToString(value.startDate.date)}</td>
        <td>${ConvertDateToString(value.endDate.date)}</td>
        <td>${status}</td>
        <td>
            <a href="#" class="btn btn-sm btn-primary btn-update"task-id=${value.id}>Sửa</a>
            <a href="#" class="btn btn-sm btn-danger btn-delete" task-id=${value.id}>Xóa</a>
        </td>
    </tr>`
    $("#example tbody").append(row);
    }
        
    })

}