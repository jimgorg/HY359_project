/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
//
//    google.charts.load('current', {'packages':['bar']});
//     
//google.charts.setOnLoadCallback(drawChart_2);


google.charts.load('current', {'packages':['table']});
google.charts.setOnLoadCallback(drawTable);


function FinishedBookingsStats(){
    
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsondata = JSON.stringify(data);
    console.log(jsondata);
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("forEdit").innerHTML = "";
            document.getElementById("forEdit").innerHTML += "Your booking statistics : ";
            document.getElementById("edit").innerHTML = "";
            document.getElementById("edit").innerHTML += xhr.responseText;
            const responseData = JSON.parse(xhr.responseText);
            console.log(responseData);
//            let dogs=responseData.Dogs;
//            console.log(dogs);
            drawTable(responseData);
            
        } else if (xhr.status !== 200) {
            document.getElementById("edit").innerHTML += "Request failed. Returned status of " + xhr.status + "<br>";  
            const responseData = JSON.parse(xhr.responseText);
        }
    };
        
    xhr.open('POST', 'FinishedStatistics');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsondata);
}






      function drawTable(respdata) {
        var data = new google.visualization.DataTable();
        
        data.addColumn('number', 'numOfBookings');
        data.addColumn('number', 'daysSummary');
        data.addColumn('number', 'income');
        data.addRows([
          [respdata.numOfBookings,  respdata.daysSummary,  respdata.income]
          
        ]);

        var table = new google.visualization.Table(document.getElementById('edit'));

        table.draw(data, {showRowNumber: true, width: '100%', height: '100%'});
      }


function drawChart_2(respdata) {
    console.log("numOfBookings :"+respdata.numOfBookings);
    console.log("daysSummary :"+respdata.daysSummary);
    console.log("income :"+respdata.income);
    


var data = google.visualization.arrayToDataTable([
          ['Category', 'Number'],
          ['numOfBookings ', respdata.numOfBookings],
          ['DaysSummary ', respdata.daysSummary],
          ['income', respdata.income]
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