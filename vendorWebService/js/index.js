// My Crappy JS Skills :/

$(".sign-up").on('click', function() {
  $(".button").addClass("expanded");
  $(".sign-up").addClass("hidden");
  $(".content").addClass("background");
  $("button").removeClass("hidden");
  $("form").toggleClass("hidden");
})

$("button").on('click', function() {
	var key = document.getElementById("key").value;
	var item = document.getElementById("item").value;
	var cost = document.getElementById("price").value;
	switch(key) {
    case "tomato123":
        key = "tomato"
        break;
    case "tg123":
        key = "truegrits"
        break;
	case "ot123":
        key = "outtakes"
        break;
	case "sls123":
        key = "slsarita"
        break;
	case "pl123":
        key = "pollo"
        break;
	case "stbks123":
        key = "starbucks"
        break;
	case "chf123":
        key = "chickfila"
        break;
	case "msl123":
        key = "masala"
        break;
	case "pvd123":
        key = "puravida"
        break;
	case "adcfs123":
        key = "adminbldgcfeshop"
        break;
    default:
        alert("Please enter correct code and try again");
	}
	
	var url = "http://ec2-54-213-169-9.us-west-2.compute.amazonaws.com/provideritemadd.php?name="+item+"&cost="+cost+"&place="+key;
	alert(url);
	window.location = url;
	var xhr = new XMLHttpRequest();
	xhr.withCredentials = true;
    xhr.addEventListener("readystatechange", function () {       
	 if (this.readyState == 4 && this.status == 200) {
            alert(this.responseText);
			alert("Updated! Click on OK to add another Item, close the Tab to exit");
          }
        });
	try{
        xhr.open("GET", chrome.extension.getURL(url), true);
        xhr.send();
	}catch(e){
	console.log(e);}	
})