

$(document).ready(function() {
    //get all job
    $.ajax({
        url:"http://localhost:8080/crm/api/job",
        method:"GET"
    }).done(function(result) {
       $("#example tbody").empty();
       $.each(result,function(index,value) {
        console.log(value);
         
        
        var row = ` <tr>
        <td>${value.id}</td>
        <td>${value.name}</td>
        <td>${ConvertDateToString(value.startDate.date)}</td>
        <td>${ConvertDateToString(value.endDate.date)}</td>
        <td>
            <a href="#" class="btn btn-sm btn-primary btn-update"job-id=${value.id}>Sửa</a>
            <a href="#" class="btn btn-sm btn-danger btn-delete" job-id=${value.id}>Xóa</a>
            <a href="#" class="btn btn-sm btn-info btn-profile" job-id=${value.id}>Xem</a>
        </td>
    </tr>`
    $("#example tbody").append(row);
       })
    })
  
      //add role
   $('#btn-save-job').click(function(e) {
    e.preventDefault();

    var name  = $('#name').val()
    var startDate = $('#start-date').val();
    var endDate = $('#end-date').val();
    //location.reload();
      console.log(name ,startDate,endDate);
   
    $.ajax({
      url:"http://localhost:8080/crm/api/job",
      method:'POST',
      datatype: 'json',
      data : {
        name:name,
        startDate:startDate,
        endDate:endDate
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
        var jobId  = $(this).attr("job-id")
        //location.reload();
        var This = $(this)
       
        $.ajax({
          url:`http://localhost:8080/crm/api/job?id=${jobId}`,
          method:'DELETE',
      
      
         }).done(function(result) {
           
             if(result.isSuccess === true) {
                 This.closest('tr').remove();
             }else {
               console.log("xoa that bai")
             }
         
             
         })
     })
      //update role 
    //update user profile
    $(document).on('click', 'a.btn-update', function(e) {
        e.preventDefault(); 
        const jobId = e.target.getAttribute("job-id");
        localStorage.setItem("jobId", jobId);
        
        window.location.replace("http://127.0.0.1:5500/job-update.html")
    });
    $(document).on('click', 'a.btn-profile', function(e) {
      e.preventDefault(); 
      const jobId = e.target.getAttribute("job-id");
      localStorage.setItem("jobId", jobId);
      
      window.location.replace("http://127.0.0.1:5500/groupwork-details.html")
  });
      $('#btn-update-job').click(function(e) {
        
        e.preventDefault();
        const jobId = localStorage.getItem("jobId")
        console.log(jobId)
        //location.reload();
       
        
         
         var name  = $('#name').val()
         var startDate = $('#start-date').val()
         var endDate = $('#end-date').val()
         
         const data = {
            id:jobId,
            name:name,
            startDate:startDate,
            endDate:endDate
         }
      
        $.ajax({
          url:"http://localhost:8080/crm/api/job",
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