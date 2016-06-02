	console.log('Avant appel de la focntion', 'ok');
	
	var xhr = new XMLHttpRequest();
	var URL = "http://localhost:8080/elearn-webapp-0.1/createcourse";
	
	courseManualData =
	{	"titleCourse" : "Cours Init 1" ,
		"descriptCourse" : "1er Exemple de cours" ,
		"skillsCourse" : "<skills>",
		"tagsCourse" : "<tagsCourse>",
		"requiredCourse" : "<requiredCourse>",
		"Chapters" :[ 
		             {   "titleChapter" : "1er Chapitre du 1er Cours", 
		                 "numChapter " : 1,
		            	 "Lectures" : [
		            	               { "numLecture" : 1,
		            	                 "linkVideoLecture" : "file:///C:/sea.mp4",
		            	            	 "txtLecture" : "1ere fois où voir la mer" 
		            	               },
		            	               { "numLecture" : 2,
		            	            	 "linkVideoLecture" : "file:///C:/sea.mp4",
		            	            	 "txtLecture" : "2eme fois où voir la mer" 
		            	               }
		            	              ]
		             },
		             {   "titleChapter" : "2eme Chapitre du 1er Cours", 
		            	 "numChapter " : 2,
		            	 "Lectures" : [
		            	               { "numLecture" : 1,
		            	            	 "linkVideoLecture" : "file:///C:/sea.mp4",
		            	            	 "txtLecture" : "1ere fois, 2eme chapitre, où voir la mer" 
		            	               },
		            	               { "numLecture" : 2,
		            	            	 "linkVideoLecture" : "file:///C:/sea.mp4",
		            	            	 "txtLecture" : "2eme fois, 2eme chapitre, où voir la mer" 
		            	               },  
		            	               { "numLecture" : 3,
		            	                 "linkVideoLecture" : "file:///C:/sea.mp4",
		            	            	 "txtLecture" : "3eme fois,  2eme chapitre, où voir la mer" 
		            	               }
		            	              ]
		             } 
		            ]
	};             
	

	creationInputDansleFormNouveauCours();

function creationInputDansleFormNouveauCours(){
	var numChap= 1;
	var linksChapitreliste = document.querySelector("#chapitrebutton a");
	console.log('mais voici que voila chap ', linksChapitreliste);
	linksChapitreliste.addEventListener('click', function(e) {
		e.preventDefault();
		creationInputChapitreDansFormNouveauCours(numChap);
		numChap++;
	});
	
	var linksSubmit = document.querySelector("#submitbutton a");
	console.log('prepare listener submit course ', linksSubmit);
	linksSubmit.addEventListener('click', function(e) {
		console.log('submit course check in progress..');
		e.preventDefault();
		 
		
		xhr.open('POST', URL);
		xhr.send(courseManualData)
		
//		$.ajax(
//				   {
//				      //course: courseManualData ,   //mydata={"name":"abc","age":"21"}
//				      method:POST,
//				      contentType: "application/json", // NOT dataType!
//				      course: JSON.stringify(courseManualData),
//				      url: "CourseFormServlet",
//				      success: function(response){alert(response);}
//				   })
		console.log('submit course check done');
	});
	
}
	
function creationInputChapitreDansFormNouveauCours(numChap) {
	var numLect= 1;
	
	//création d'une nouvelle division
	creationNouvelleDivision('chapitreliste','chapitre'+numChap);
	
	//ecriture dans la division créer
	var detailDiv = document.querySelector('#chapitre'+numChap);
	detailDiv.innerHTML += '<label for="chapitre'+numChap+'">Chapitre'+numChap+'  :</label><input id="chapitre'+numChap+'" type="text" name="chapitre'+numChap+'">';
	detailDiv.innerHTML +='<div id=lectureChapitre'+numChap+'><a  href="">Créer une lecture</a>';
	detailDiv.innerHTML +='</div>';
	//Ouverture du listener
	var linksLecture = document.querySelector('#lectureChapitre'+numChap+' a');
	
	linksLecture.addEventListener('click', function(e) {
		e.preventDefault();
		creationInputLectureDansFormNouveauCours(numChap,numLect);
	
		numLect++;
	});
}

function creationInputLectureDansFormNouveauCours(numChap,numLect){
		var detailDiv = document.querySelector('#lectureChapitre'+numChap);
		detailDiv.innerHTML += '<div id=lecture'+numLect+'><label for="lecture'+numLect+'">Lecture'+numLect+' :</label><input id="lecture'+numLect+'" type="text" name="lecture'+numLect+'"></div>';
}


function creationNouvelleDivision(parent,id){
	var divNew = document.createElement('div');
	divNew.setAttribute('id', id);
	document.getElementById(parent).appendChild(divNew);
}

function ecritureDivision(id, txt) {
	var detailDiv = document.querySelector(id);
	
}