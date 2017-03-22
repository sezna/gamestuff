window.onload = function() {LoadTable();};

	function LoadTable(){
		let xmlhttp = new XMLHttpRequest();

		xmlhttp.onreadystatechange=function() {
	    	if (this.readyState==4 && this.status==200) {
	    		console.log(this.responseText);
	      		document.getElementById("tableLoc").innerHTML=this.responseText;

	    	}
	    	
  		}

  		xmlhttp.open("GET","tableGen.php");
 	 	xmlhttp.send();
	}

	/*function startup(){
		$(document).ready(function(){
			$("form").filter($("#addTo")).submit(function(event){
				var addInfo = {
					'add':$('input[name=add]').val()
				}

				$.ajax({
					type:"POST",
					url:"t6add.php",
					data: addInfo,
					encode:true
				})
				LoadTable();
				event.preventDefault();
				LoadTable();
			})

			
		})
	}*/