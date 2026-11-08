'use strict';

document.getElementById('fini').style.display = 'none';
document.getElementById('review').style.display = 'none';

function MakeBookingPost(){
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            
            document.getElementById('fini').style.display = 'block';
//            const button = document.createElement('button');
//
//            button.innerText = 'finished';
//
//            button.id = 'fini_butt';
//            button.style.cssText = 'width:10%;border-radius:20px;height:3rem;margin-left:4.5%;background-color:#235484;font-weight:bold;font-size:18px;';
            
//            button.addEventListener('click', () => {
//                finish();
//            });

//            document.body.appendChild(button);
//            document.getElementById("fini").append(button);
            
            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML += xhr.responseText;
            
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";

           
           
        }
    };
        
    xhr.open('POST', 'MakeBooking');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);
}

function finish(){
    
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            
            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML = "Accommodation ended";
            
            document.getElementById('review').style.display = 'block';
//            var del = document.getElementById('fini_butt');
//            del.parentNode.removeChild(del);
            document.getElementById('fini').style.display = 'none';
            document.getElementById('makebooking').disabled ='true';
            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML += xhr.responseText;
//            const responseData = JSON.parse(xhr.responseText);
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";

//            const responseData = JSON.parse(xhr.responseText);
           

        }
    };
        
    xhr.open('POST', 'Finish');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);
}


function Review(){
        
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);
    console.log(jsondata);
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            
//            document.getElementById('fini').style.display = 'none';
            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML += xhr.responseText;
             setTimeout(function(){window.location = 'afterRegistration.html';}, 2000);
            const responseData = JSON.parse(xhr.responseText);
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";

           
        }
    };
        
    xhr.open('POST', 'AddReview');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);
}