

$(document).ready(function() {
    //get  all user
    $.ajax({
        url:"http://localhost:8080/crm/api/role",
        method:"GET",
        dataType:"json",
        success: function(data) {
            console.log(data)
           $.ajax({
            method:"GET",
            url:"http://localhost:8080/crm/api/user",
            success:function(result) {
                $("#example tbody").empty();
                $.each(result,function(index,value) {
                console.log(value);
                const role = data.filter( d => d.id === value.roleId)
                console.log(role);
                  var row = ` <tr>
                  <td>${value.id}</td>
                  <td>${value.fullName}</td>
                  <td>${value.email}</td>
                  <td>${role[0].name}</td>
                  <td>${value.avatar}</td>
                  <td>
                      <a href="#" class="btn btn-sm btn-primary btn-update" user-id=${value.id} >Sửa</a>
                      <a href="#" class="btn btn-sm btn-danger btn-delete" user-id=${value.id}>Xóa</a>
                      <a href="#" class="btn btn-sm btn-info" user-id=${value.id}>Xem</a>
                  </td>
              </tr>`
              $("#example tbody").append(row);
                })
                
            }
           })
        }
    })
    //get all role
    $.ajax({
      url:"http://localhost:8080/crm/api/role",
      method:'GET',
  
  
     }).done(function(result) {
      $("#role").empty()
  
      $.each(result,function(index,value) {
             console.log(value)
  
             var row = `<option value=${value.id}>${value.name}</option>`
          $("#role").append(row);
          })
         
     })
    //add user 
    $('#btn-save-user').click(function(e) {
        e.preventDefault();
   
         var fullName  = $('#fullName').val()
         var email = $('#email').val()
         var password = $('#password').val()
         var avatar = $('#avatar').val()
         var roleId = $('#role').val();
       
       
          console.log(fullName,email,password,avatar,roleId) 
       
        $.ajax({
          url:"http://localhost:8080/crm/api/user",
          method:'POST',
          datatype: 'json',
          data : {
           fullName:fullName,
           password:password,
           email:email,
           avatar:avatar,
           roleId:roleId
          }
      
      
         }).done(function(result) {
           
             if(result.isSuccess === true) {
                 console.log("them thanh cong")
             }else {
               console.log("them that bai")
             }
         
             
         })
      })
     //update user profile
     $(document).on('click', 'a.btn-update', function(e) {
        e.preventDefault(); 
        const userId = e.target.getAttribute("user-id");
        localStorage.setItem("userId", userId);
        
        window.location.replace("http://127.0.0.1:5500/user-update.html")
    });
      $('#btn-update-user').click(function(e) {
        
        e.preventDefault();
        const userId = localStorage.getItem("userId")
        console.log(userId)
        //location.reload();
       
        
         
         var fullName  = $('#fullName').val()
         var email = $('#email').val()
         var password = $('#password').val()
         var avatar = $('#avatar').val()
         var roleId = $('#role').val();
       
       
        console.log(fullName,email,password,avatar,roleId,userId) 
        const data = {
            id:userId,
            fullName:fullName,
            password:password,
            email:email,
            avatar:avatar,
            roleId:roleId
        }
        $.ajax({
          url:"http://localhost:8080/crm/api/user",
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
    
    
      //delete user
      $("body").on('click',".btn-delete",function() {
        var userId  = $(this).attr("user-id")
        //location.reload();
        var This = $(this)
        $.ajax({
          url:`http://localhost:8080/crm/api/user?id=${userId}`,
          method:'DELETE',
      
      
         }).done(function(result) {
           
             if(result.isSuccess === true) {
                 This.closest('tr').remove();
             }else {
               console.log("xoa that bai")
             }
         
             
         })
     })
     //move to profile user page
     //delete user
     $(document).on('click', 'a.btn-info', function(e) {
      e.preventDefault(); 
      const userId = e.target.getAttribute("user-id");
      console.log(userId);
      localStorage.setItem("userId", userId);
      
      window.location.replace("http://127.0.0.1:5500/user-details.html")

   
  })
 
    
})

    

