
 function validateSignInForm() {
  var x = document.forms["SignIn"]["username"].value;
  var y = document.forms["SignIn"]["psw"].value;
  if (x=="" && y=="")
  {
	  Swal.fire({
		  icon: 'error',
		  title: 'Oops...',
		  text: 'Please fill out your username and password '
		})
    return false;
  }
  if (y=="")
  {
	
	  Swal.fire({
		  icon: 'error',
		  title: 'Oops...',
		  text: 'Please fill out your password '
		})

	  return false;
  }
  if (x=="")
  {
	  Swal.fire({
		  icon: 'error',
		  title: 'Oops...',
		  text: 'Please fill out your username '
		})
	return false;  
  }
}