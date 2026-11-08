/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

'use strict';



var submitAllowed = true;


function see_password(id) {
    var x = document.getElementById(id);
    if (x.type === "password") {
      x.type = "text";
    } else {
      x.type = "password";
    }
  }

  function missmatch(){
    if (document.getElementById('password').value === document.getElementById('re_password').value) {
      document.getElementById('miss').style.color = 'green';
      document.getElementById('miss').innerHTML = 'Perfect_Match';
    } else {
      document.getElementById('miss').style.color = 'red';
      document.getElementById('miss').innerHTML = 'Passwords_Missmatch';
    }
        
  }

  function security(id){
    var x = document.getElementById(id);
    var passSecurity = 0;
    submitAllowed = true;
    console.log(x.value);
    const button = document.getElementById('Submit_Button');
    if((x.value).includes("cat") || (x.value).includes("dog") || (x.value).includes("gata") || (x.value).includes("skulos")){
      document.getElementById('res').innerHTML = 'words : dog, cat, gata, skulos are not allowed';
      document.getElementById('res').style.color = 'red';
      passSecurity = 1;
      submitAllowed = false;
    }
    const l = (((x.value).length)/2);
    var count = 0;
    for(var i=0; i<((x.value).length);i++){
      if(!isNaN(((x.value).charAt(i)))){
        count+=1;
      }
    }
    if(count >= l){
      document.getElementById('res').innerHTML = 'Weak_Password';
      document.getElementById('res').style.color = 'red';
      passSecurity = 1;
      submitAllowed = false;
    }
     
    var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{4,})/;
    if (x.value.match(regex)){
      document.getElementById('res').innerHTML = 'Strong_Password';
      document.getElementById('res').style.color = 'green';
      passSecurity = 1;
      submitAllowed = true;
    }
    if (!passSecurity){
      document.getElementById('res').innerHTML = 'Medium_Password';
      document.getElementById('res').style.color = 'red';
      submitAllowed = true;
    }
    if (!submitAllowed){
      button.disabled = "disabled";
    }
    else{
      button.disabled = "";
    }
      
  }

//  document.getElementById('if_pet_keeper').style.display = 'none';

  function check_user(){      
      var user = document.getElementById('type');
//      var butt = document.getElementById("registerpet");
//      var bookbutt = document.getElementById("makebooking");
      
      if(user.value === 'PetOwner'){
        document.getElementById('if_pet_keeper').style.display = 'none';
//        document.getElementById('pet').style.display = 'block';
//        document.getElementById('booking').style.display='block';
//        butt.disabled = "";
//        bookbutt.disabled = "";
      
      }
      else{
          
//          document.getElementById('booking').style.display='none';        
//          document.getElementById('pet').style.display = 'none';
          document.getElementById('if_pet_keeper').style.display = 'block';
//          butt.disabled = "disabled";
//          bookbutt.disabled = "disabled";
      
      var place = document.getElementById('place');
      console.log(place.value);
      if(place.value === 'outside' && place.value !== 'both'){
        document.getElementById('forcat').disabled = true;

      }
      else{
        document.getElementById('forcat').disabled = false;
      }

      
    }

  }
  
//  function createTableFromJSON(data) {
//    var html = "<table><tr><th>Category</th><th>Value</th></tr>";
//    for (const x in data) {
//        var category = x;
//        var value = data[x];
//        if (value.endsWith('jpg') || value.endsWith("png")) {
//            value = "<img height=300 src='" + value + "'/>";
//        }
//        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
//    }
//    html += "</table>";
//    return html;
//
//}
//
//
//document.getElementById('fini').style.display = 'none';
//document.getElementById('review').style.display = 'none';

//function Review(){
//        
//    let myForm = document.getElementById('myForm');
//    let formData = new FormData(myForm);
//    const data = {};
//    formData.forEach((value, key) => (data[key] = value));
//    var jsondata = JSON.stringify(data);
//    
//    var xhr = new XMLHttpRequest();
//    xhr.onload = function () {
//        if (xhr.readyState === 4 && xhr.status === 200) {
//            
////            document.getElementById('fini').style.display = 'none';
//            document.getElementById("edit").innerHTML = "";
//            document.getElementById("edit").innerHTML += xhr.responseText;
//            
//            const responseData = JSON.parse(xhr.responseText);
//        } else if (xhr.status !== 200) {
//            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";
//
//            const responseData = JSON.parse(xhr.responseText);
//           
////            
//        }
//    };
//        
//    xhr.open('POST', 'AddReview');
//    xhr.setRequestHeader("Content-type", "application/json");
//    xhr.send(jsondata);
//}
//function finish(){
//    
//    let myForm = document.getElementById('myForm');
//    let formData = new FormData(myForm);
//    const data = {};
//    formData.forEach((value, key) => (data[key] = value));
//    var jsondata = JSON.stringify(data);
//    
//    var xhr = new XMLHttpRequest();
//    xhr.onload = function () {
//        if (xhr.readyState === 4 && xhr.status === 200) {
//            
////            document.getElementById("edit").innerHTML = "";
////            document.getElementById("edit").innerHTML = "Accommodation ended";
//            
////            var del = document.getElementById('fini_butt');
////            del.parentNode.removeChild(del);
//            document.getElementById('fini').style.display = 'none';
//            document.getElementById('review').style.display = 'block';
//            document.getElementById("edit").innerHTML = "";
//            document.getElementById("edit").innerHTML += xhr.responseText;
//            
//            const responseData = JSON.parse(xhr.responseText);
//        } else if (xhr.status !== 200) {
//            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";
//
//            const responseData = JSON.parse(xhr.responseText);
//           
////            
//        }
//    };
//        
//    xhr.open('POST', 'Finish');
//    xhr.setRequestHeader("Content-type", "application/json");
//    xhr.send(jsondata);
//}

//function MakeBookingPost(){
//    let myForm = document.getElementById('myForm');
//    let formData = new FormData(myForm);
//    const data = {};
//    formData.forEach((value, key) => (data[key] = value));
//    var jsondata = JSON.stringify(data);
//    
//    var xhr = new XMLHttpRequest();
//    xhr.onload = function () {
//        if (xhr.readyState === 4 && xhr.status === 200) {
//            
//            document.getElementById('fini').style.display = 'block'
////            const button = document.createElement('button');
////
////            button.innerText = 'finished';
////
////            button.id = 'fini_butt';
////            button.style.cssText = 'width:10%;border-radius:20px;height:3rem;margin-left:4.5%;background-color:#235484;font-weight:bold;font-size:18px;';
////            
////            button.addEventListener('click', () => {
////                finish();
////            });
//
////            document.body.appendChild(button);
////            document.getElementById("fini").append(button);
//            
//            document.getElementById("edit").innerHTML = "";
//            document.getElementById("edit").innerHTML += xhr.responseText;
//            
//        } else if (xhr.status !== 200) {
//            document.getElementById("edit").innerHTML = "";
//            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";
//
//            const responseData = JSON.parse(xhr.responseText);
//           
////            
//        }
//    };
//        
//    xhr.open('POST', 'MakeBooking');
//    xhr.setRequestHeader("Content-type", "application/json");
//    xhr.send(jsondata);
//}


function AdLogin(){
    document.getElementById('edit').innerHTML = "";
    var usr = document.getElementById("adminUsername").value;
    var pass = document.getElementById("adminPassword").value;
    console.log(usr);
    console.log("pass : "+pass);
    
    if(usr === "admin" && pass === "admin12*"){
        document.getElementById("edit").innerHTML = "Successfull administrator login...Loading.." ;
        setTimeout(function(){window.location = 'adminOptions.html';}, 1500);
        
    }
    else
    {
        
        document.getElementById('edit').innerHTML = "Wrong administrator credentials";
        document.getElementById('edit').style = 'red';
    }
}


//google.charts.load('current', {'packages':['corechart']});
//     
//google.charts.setOnLoadCallback(drawChart);
//google.charts.load('current', {'packages':['bar']});
//     
//google.charts.setOnLoadCallback(drawChart);

google.charts.load('current', {'packages':['bar']});
     
google.charts.setOnLoadCallback(drawChart);




function GetStatistics(){

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("edit").innerHTML = "";
//            document.getElementById("edit").innerHTML += xhr.responseText;
            const responseData = JSON.parse(xhr.responseText);
            console.log(responseData);
            let dogs=responseData.Dogs;
            console.log(dogs);
            drawChart(responseData);
            
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";            
        }
    };
        
    xhr.open('GET', 'Statistics');
    xhr.send();

}

     
function drawChart(respdata) {
    console.log("owners :"+respdata.PetOwners);
    console.log("keepers :"+respdata.PetKeepers);
    console.log("cats :"+respdata.Cats);
    console.log("dogs :"+respdata.Dogs);
    console.log("keepers earned :"+respdata.PetKeepersEarned);
    console.log("app earned :"+respdata.ApplicationEarned);


var data = google.visualization.arrayToDataTable([
          ['Category', 'Number'],
          ['Pet Owners', respdata.PetOwners],
          ['Pet Keepers', respdata.PetKeepers],
          ['Number of Cats', respdata.Cats],
          ['Number of Dogs',respdata.Dogs ],
          ['Price Earned for Pet Keepers', respdata.PetKeepersEarned],
          ['Price Earned for the Application', respdata.ApplicationEarned]
        ]);
        var options = {
            
            title: 'Pet Keeping Application Statistics ',
            width:1200,
            height:600,


          bars: 'horizontal' // Required for Material Bar Charts.
        };



    var chart = new google.charts.Bar(document.getElementById('edit'));

    chart.draw(data, google.charts.Bar.convertOptions(options));

 
      }
      
    



//function FinishedBookingsStats(){
//    
//    let myForm = document.getElementById('myForm');
//    let formData = new FormData(myForm);
//    const data = {};
//    formData.forEach((value, key) => (data[key] = value));
//    var jsondata = JSON.stringify(data);
//    console.log(jsondata);
//    var xhr = new XMLHttpRequest();
//    xhr.onload = function () {
//        if (xhr.readyState === 4 && xhr.status === 200) {
//            document.getElementById("edit").innerHTML = "";
//            document.getElementById("edit").innerHTML += xhr.responseText;
//            const responseData = JSON.parse(xhr.responseText);
//            console.log(responseData);
////            let dogs=responseData.Dogs;
////            console.log(dogs);
//            drawChart(responseData);
//            
//        } else if (xhr.status !== 200) {
//            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";  
//            const responseData = JSON.parse(xhr.responseText);
//        }
//    };
//        
//    xhr.open('POST', 'FinishedStatistics');
//    xhr.setRequestHeader("Content-type", "application/json");
//    xhr.send(jsondata);
//}
//
//function drawChart_2(respdata) {
//    console.log("numOfBookings :"+respdata.numOfBookings);
//    console.log("sumOfDays :"+respdata.sumOfDays);
//    console.log("income :"+respdata.income);
//    
//
//
//var data = google.visualization.arrayToDataTable([
//          ['Category', 'Number'],
//          ['numOfBookings ', respdata.numOfBookings],
//          ['sumOfDays ', respdata.sumOfDays],
//          ['income', respdata.income]
//        ]);
//        var options = {
//            
//            title: 'Pet Keeping Application Statistics ',
//            width:1200,
//            height:600,
//
//
//          bars: 'horizontal' // Required for Material Bar Charts.
//        };
//
//
//
//    var chart = new google.charts.Bar(document.getElementById('edit'));
//
//    chart.draw(data, google.charts.Bar.convertOptions(options));
//
// 
//      }


function FindKeeperGet(){
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
//            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML += xhr.responseText;
            
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";            
        }
    };
        
    xhr.open('GET', 'FindPetKeeper');
    xhr.send();
}

function DelKeeperPost(){
    
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML += xhr.responseText;
            
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";

//            const responseData = JSON.parse(xhr.responseText);
           
            
        }
    };
        
    xhr.open('POST', 'AdminDeleteKeeper');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);

    
}

function DelOwnerPost(){
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);
    console.log("this is delownerpost");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML += xhr.responseText;
            
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";

//            const responseData = JSON.parse(xhr.responseText);
           
            
        }
    };
        
    xhr.open('POST', 'AdminDelete');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);
}


function GetUsers(){
    var ed=document.getElementById("edit");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("edit").innerHTML = "";
//            ed.style.backgroundColor = '#000000';
            document.getElementById("edit").innerHTML += xhr.responseText;
            
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";            
        }
    };
        
    xhr.open('GET', 'AdminGetUsers');
    xhr.send();

}

function seePendingRequests(){
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("forEdit").innerHTML = "";
            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML += xhr.responseText;
//             setTimeout(function(){window.location = 'afterRegistration.html';}, 2000);
            const responseData = JSON.parse(xhr.responseText);
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";

//            const responseData = JSON.parse(xhr.responseText);
           
//            
        }
    };
        
    xhr.open('POST', 'KeeperViewPendingRequests');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);

}



function RegisterPetPost(){
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML += xhr.responseText;
             setTimeout(function(){window.location = 'afterRegistration.html';}, 2000);
            const responseData = JSON.parse(xhr.responseText);
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";

            const responseData = JSON.parse(xhr.responseText);
           
//            
        }
    };
        
    xhr.open('POST', 'RegisterPet');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);
}

function EditPetOwnerPost(){
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML += xhr.responseText;
             setTimeout(function(){window.location = 'afterRegistration.html';}, 2000);
            const responseData = JSON.parse(xhr.responseText);
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";

            const responseData = JSON.parse(xhr.responseText);
           
//            
        }
    };
        
    xhr.open('POST', 'EditPetOwner');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);
}
    


function EditPetKeeperPost(){
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML += xhr.responseText;
             setTimeout(function(){window.location = 'petKeeperAfterRegistration.html';}, 2000);
            const responseData = JSON.parse(xhr.responseText);
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";

            const responseData = JSON.parse(xhr.responseText);
           
//            
        }
    };
        
    xhr.open('POST', 'EditPetKeeper');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);
}

  
  
  function RegisterPOST() {
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);

    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);

//    $('#ajaxContent').html("");

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("ajaxContent").innerHTML += xhr.responseText;
            
//            const responseData = JSON.parse(xhr.responseText);
            document.getElementById("edit").innerHTML += "You successfully registered!...Loading..." ;
            document.getElementById("edit").innerHTML += xhr.responseText;
            setTimeout(function(){window.location = 'afterRegistration.html';}, 2000);

        } else if (xhr.status !== 200) {
            document.getElementById("ajaxContent").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";

//            const responseData = JSON.parse(xhr.responseText);
           
//            
        }
    };
        
    xhr.open('POST', 'Resgister');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);
}

function RegisterKeeperPOST(){
        let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);

    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);

//    $('#ajaxContent').html("");

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("ajaxContent").innerHTML += xhr.responseText;
            
//            const responseData = JSON.parse(xhr.responseText);
            document.getElementById("edit").innerHTML += "You successfully registered!...Loading..." ;
            document.getElementById("edit").innerHTML += xhr.responseText;
            setTimeout(function(){window.location = 'petKeeperAfterRegistration.html';}, 2000);

        } else if (xhr.status !== 200) {
            document.getElementById("ajaxContent").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";

//            const responseData = JSON.parse(xhr.responseText);
           
//            
        }
    };
        
    xhr.open('POST', 'Resgister');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);
}

function RequestsPOST(){
            let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);

    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);

//    $('#ajaxContent').html("");

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("ajaxContent").innerHTML += xhr.responseText;
            
//            const responseData = JSON.parse(xhr.responseText);
            document.getElementById("edit").innerHTML += "You successfully registered!...Loading..." ;
            document.getElementById("edit").innerHTML += xhr.responseText;
            setTimeout(function(){window.location = 'petKeeperAfterRegistration.html';}, 2000);

        } else if (xhr.status !== 200) {
            document.getElementById("ajaxContent").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";

//            const responseData = JSON.parse(xhr.responseText);
           
//            
        }
    };
        
    xhr.open('POST', 'AnswerKeeeper');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);
}


function AcceptRequest(){
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML += xhr.responseText;
            const responseData = JSON.parse(xhr.responseText);
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";

            const responseData = JSON.parse(xhr.responseText);
           
//            
        }
    };
        
    xhr.open('POST', 'AcceptKeeperRequest');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);
}


function DeclineRequest(){
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML += xhr.responseText;
            const responseData = JSON.parse(xhr.responseText);
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";

            const responseData = JSON.parse(xhr.responseText);
           
//            
        }
    };
        
    xhr.open('POST', 'RejectKeeperRequest');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);
}


function MessageOwner(){
     let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML += xhr.responseText;
            const responseData = JSON.parse(xhr.responseText);
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";

            const responseData = JSON.parse(xhr.responseText);
           
            
        }
    };
        
    xhr.open('POST', 'MessageToOwner');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);
}

function MessageKeeper(){
     let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML += xhr.responseText;
            const responseData = JSON.parse(xhr.responseText);
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";

            const responseData = JSON.parse(xhr.responseText);
           
            
        }
    };
        
    xhr.open('POST', 'MessageToKeeper');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);
}
