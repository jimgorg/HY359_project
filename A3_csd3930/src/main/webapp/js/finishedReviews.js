/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
'use strict';
google.charts.load('current', {'packages':['table']});
google.charts.setOnLoadCallback(drawTable);

function FinishedBookingsReviews(){
    
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);
    console.log(jsondata);
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("reviewTable").innerHTML = "";
            document.getElementById("forReviewTable").innerHTML = "";
            document.getElementById("forReviewTable").innerHTML += "Your booking reviews : ";
            document.getElementById("reviewTable").innerHTML +=  xhr.responseText;
            const responseData = JSON.parse(xhr.responseText);
            console.log(responseData);
//            console.log(responseData.review3.keeper_id0);
//            console.log(responseData.review3.keeper_id1);
//            console.log(responseData.review3.owner_id1);
//            console.log(responseData.review3.reviewText1);
//            console.log(responseData.reviewScore1);
//            console.log("count : "+responseData.count);
//            
//            console.log("i is : "+i);
            var i = parseInt(responseData.count);
            console.log("count is : "+ typeof i);
            
            if(i === 1){
                drawTable_1(responseData);
            }
            else if(i ===2){
                drawTable_2(responseData);
            }
            else if(i ===3){
              drawTable_3(responseData);  
            }
            else if(i === 4){
                drawTable_4(responseData);
            }
            
//            drawTable_3(responseData);
            
        } else if (xhr.status !== 200) {
            document.getElementById("reviewTable").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";  
            const responseData = JSON.parse(xhr.responseText);
        }
    };
        
    xhr.open('POST', 'FinishedReviews');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);
}

 function drawTable_1(respdata) {
    var data = new google.visualization.DataTable();
        
    data.addColumn('string', 'keeper_id');
    data.addColumn('string', 'owner_id');
    data.addColumn('string', 'reviewText');
    data.addColumn('string', 'reviewScore');
    data.addRows([
        [respdata.review1.keeper_id0,  respdata.review1.owner_id0,  respdata.review1.reviewText0, respdata.review1.reviewScore0]
        
        ]);

        
        var table = new google.visualization.Table(document.getElementById('reviewTable'));

        table.draw(data, {showRowNumber: true, width: '100%', height: '100%'});
      }



 function drawTable_2(respdata) {
    var data = new google.visualization.DataTable();
        
    data.addColumn('string', 'keeper_id');
    data.addColumn('string', 'owner_id');
    data.addColumn('string', 'reviewText');
    data.addColumn('string', 'reviewScore');
    data.addRows([
        [respdata.review2.keeper_id0,  respdata.review2.owner_id0,  respdata.review2.reviewText0, respdata.review2.reviewScore0],
        [respdata.review2.keeper_id1,  respdata.review2.owner_id1,  respdata.review2.reviewText1, respdata.review2.reviewScore1]   
        ]);

        
        var table = new google.visualization.Table(document.getElementById('reviewTable'));

        table.draw(data, {showRowNumber: true, width: '100%', height: '100%'});
      }

      function drawTable_3(respdata) {
        var data = new google.visualization.DataTable();
        
        data.addColumn('string', 'keeper_id');
        data.addColumn('string', 'owner_id');
        data.addColumn('string', 'reviewText');
        data.addColumn('string', 'reviewScore');
        data.addRows([
          [respdata.review3.keeper_id0,  respdata.review3.owner_id0,  respdata.review3.reviewText0, respdata.review3.reviewScore0],
          [respdata.review3.keeper_id1,  respdata.review3.owner_id1,  respdata.review3.reviewText1, respdata.review3.reviewScore1],
          [respdata.review3.keeper_id2,  respdata.review3.owner_id2,  respdata.review3.reviewText2, respdata.review3.reviewScore2]
          ]);
//        for(var i=0 ; i<respdata.count ; i++){
//            data.addRows([
//            [respdata.review3.keeper_id,  respdata.review3.owner_id,  respdata.review3.reviewText, respdata.review3.reviewScore]
//        ]);
//        }
        

        var table = new google.visualization.Table(document.getElementById('reviewTable'));

        table.draw(data, {showRowNumber: true, width: '100%', height: '100%'});
      }
      
 function drawTable_4(respdata) {
    var data = new google.visualization.DataTable();
        
    data.addColumn('string', 'keeper_id');
    data.addColumn('string', 'owner_id');
    data.addColumn('string', 'reviewText');
    data.addColumn('string', 'reviewScore');
    data.addRows([
        [respdata.review1.keeper_id0,  respdata.review1.owner_id0,  respdata.review1.reviewText0, respdata.review1.reviewScore0],
        [respdata.review2.keeper_id1,  respdata.review2.owner_id1,  respdata.review2.reviewText1, respdata.review2.reviewScore1],
        [respdata.review3.keeper_id2,  respdata.review3.owner_id2,  respdata.review3.reviewText2, respdata.review3.reviewScore2],
        [respdata.review4.keeper_id3,  respdata.review4.owner_id3,  respdata.review4.reviewText3, respdata.review4.reviewScore3]
        
        ]);

        
        var table = new google.visualization.Table(document.getElementById('reviewTable'));

        table.draw(data, {showRowNumber: true, width: '100%', height: '100%'});
      }      