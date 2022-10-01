$(document).ready(function() {
  //get all role
   $.ajax({
    url:"http://localhost:8080/crm/api/role",
    method:'GET',


   }).done(function(result) {
    $("#example tbody").empty()

    $.each(result,function(index,value) {
           console.log(value)

           var row = `<tr>
           <td>${value.id}</td>
           <td>${value.name}</td>
           <td>${value.description}</td>
           <td>
               <a href="#" class="btn btn-sm btn-primary btn-update"role-id=${value.id} >Sửa</a>
               <a href="#" class="btn btn-sm btn-danger btn-delete" role-id=${value.id}>Xóa</a>
           </td>
       </tr>`
        $("#example tbody").append(row);
        })
       
   })
   //delete  role
   $("body").on('click',".btn-delete",function() {
      var roleId  = $(this).attr("role-id")
      //location.reload();
      var This = $(this)
     
      $.ajax({
        url:`http://localhost:8080/crm/api/role?id=${roleId}`,
        method:'DELETE',
    
    
       }).done(function(result) {
         
           if(result.isSuccess === true) {
               This.closest('tr').remove();
           }else {
             console.log("xoa that bai")
           }
       
           
       })
   })
   //add role
   $('#btn-save-role').click(function(e) {
     e.preventDefault();

     var dataName  = $('#name').val()
     var dataDescription = $('#description').val();
     //location.reload();
       console.log(dataName ,dataDescription)
    
     $.ajax({
       url:"http://localhost:8080/crm/api/role",
       method:'POST',
       datatype: 'json',
       data : {
         name:dataName,
         description:dataDescription
       }
   
   
      }).done(function(result) {
        
          if(result.isSuccess === true) {
              console.log("them thanh cong")
          }else {
            console.log("them that bai")
          }
      
          
      })
   })
   //update role 
    //update user profile
    $(document).on('click', 'a.btn-update', function(e) {
      e.preventDefault(); 
      const roleId = e.target.getAttribute("role-id");
      localStorage.setItem("roleId", roleId);
      
      window.location.replace("http://127.0.0.1:5500/role-update.html")
  });
    $('#btn-update-role').click(function(e) {
      
      e.preventDefault();
      const roleId = localStorage.getItem("roleId")
      console.log(roleId)
      //location.reload();
     
      
       
       var name  = $('#name').val()
       var description = $('#description').val()
      
     
     
      const data = {
          id:roleId,
          name:name,
          description:description
      }
      $.ajax({
        url:"http://localhost:8080/crm/api/role",
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