$(document).ready(function() {
    $('.btn-login').click(function(e) {
        e.preventDefault();
   
        
         var email = $('#email').val()
         var password = $('#password').val()
       
       
       
          console.log(email, password) 
       
       
         $.ajax({
          type: 'POST',
          url: 'http://localhost:8080/crm/api/user/login',
          dataType: 'json',
          data:  {
            email:email,
            password:password
          },
          success: function(data){
           window.location.href = "http://127.0.0.1:5500/index.html";
          },
          error: function(xhr, type, exception) { 
            // if ajax fails display error alert
            alert("email or password incorrect");
          }
        });
      })
    })