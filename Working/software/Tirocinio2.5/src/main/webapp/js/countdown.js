//countdown 3 2 1 0
var numero = 3;

colore_rgb = "rgb(0,0,0)";
// Applico il colore allo span "secondi"
secondi.style.color = colore_rgb;

document.getElementById("secondi").innerHTML = numero;
					
function cambiatesto()
	
{	
	if(numero>0)
	{ 
		numero = numero - 1;	
		document.getElementById("secondi").innerHTML = numero;
	} 
}


setInterval(cambiatesto,1000); 	   
    