/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
'use-strict'

var long, lat;
var position;
var map;
var map_mess = document.getElementById('map_mess');


function find_address(){
    
    map_close();
    map_mess.style.display = 'block'
    map_mess.innerHTML = "Address loading..";
    map_mess.style.color = "blue";
    setTimeout(find_, 1000);    
}

function find_(){
    const data = null;
    const xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4 && this.status === 200) {
            var list = JSON.parse(this.responseText);
            if(list[0] !== null){
                var locat = JSON.parse(JSON.stringify(list[0]));
                console.log(list[0]);
                map_mess.innerHTML = "ADDRESS located!";
                map_mess.style.color = "green";

                long = locat.lon;
                lat = locat.lat;
                console.log("longtitude : ",long);
                console.log("latitude : ",lat);

                setPosition(lat, long);               
            }                     
        }
        else {
            map_mess.innerHTML = "ERROR! address not found! wrong inputs, not valid JSON";

            map_mess.style.color = "red";
        }  
        
    });


    var addressName = document.getElementById("address").value;
    var city =document.getElementById("city").value;
    var country = document.getElementById("country").value;
    var address = addressName + " " + city + " "+ country; 

    xhr.open("GET", "https://forward-reverse-geocoding.p.rapidapi.com/v1/search?q="+address+"&accept-language=en&polygon_threshold=0.0");
    xhr.setRequestHeader("X-RapidAPI-Key", "da4b14a507msh5561f3787cf787cp1051bcjsn3601e72f7980");
    xhr.setRequestHeader("X-RapidAPI-Host", "forward-reverse-geocoding.p.rapidapi.com");

    xhr.send(data);    
}


function setPosition(lat, long){
    var fromProjection = new OpenLayers.Projection("EPSG:4326");   // Transform from WGS 1984
    var toProjection   = new OpenLayers.Projection("EPSG:900913"); // to Spherical Mercator Projection
    position       = new OpenLayers.LonLat(long, lat).transform( fromProjection, toProjection);
    return position;
}



function map_display(){
    console.log(position);

    document.getElementById('Map').style.height = "400px";
    
    document.getElementById('Map').style.width = "50%";

    map = new OpenLayers.Map("Map");
    var maplay = new OpenLayers.Layer.OSM();
    map.addLayer(maplay);
    
    var markers = new OpenLayers.Layer.Markers( "Markers" );
    map.addLayer(markers);
    var mar = new OpenLayers.Marker(position);
    markers.addMarker(mar);	
    mar.events.register('mousedown', mar, function(evt) { 
        handler(position,'ur here');}
    );

    
    const zoom = 16;
    map.setCenter(position, zoom);
    
}  

function map_close(){
    if(map){
    map.destroy();
    document.getElementById('Map').style.height = "0px";
    
    document.getElementById('Map').style.width = "0%";
    map_mess.style.display = 'none';

    }
}
    





