	console.log('Avant appel de la focntion', 'ok');
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