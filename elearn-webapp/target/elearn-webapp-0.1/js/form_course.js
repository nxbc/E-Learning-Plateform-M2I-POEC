	console.log('Avant appel de la focntion', 'ok');
	creationInputDansleFormNouveauCours();

function creationInputDansleFormNouveauCours(){
	var numChap= 1;
	var linksChapitreliste = document.querySelector("#chapitrebutton a");
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
	var txt = '<label for="chapitre'+numChap+'">Chapitre'+numChap+'  :</label><input id="chapitre'+numChap+'input" type="text" name="chapitre'+numChap+'"><div id=lectureChapitre'+numChap+'><a  href="">Creer une lecture</a></div><hr/>';
	ecritureDivision('chapitre'+numChap, txt);
	//Ouverture du listener
	var linksLecture = document.querySelector('#lectureChapitre'+numChap+' a');
		linksLecture.addEventListener('click', function(e) {
		e.preventDefault();
		creationInputLectureDansFormNouveauCours(numChap,numLect);
		numLect++;
	});
}

function creationInputLectureDansFormNouveauCours(numChap,numLect){
	// Création nouvelle division
	creationNouvelleDivision('chapitre'+numChap,'chapitre'+numChap+'lecture'+numLect);
	//ecriture dans la division
	var txt = '<label for="chapitre'+numChap+'lecture'+numLect+'">Lecture'+numLect+' :</label><input id="chapitre'+numChap+'lecture'+numLect+'input" type="text" name="lecture'+numLect+'"><br/>';
	txt+= '<label for="lienVideo'+'lecture'+numLect+'">Lien Video :</label><input id="chapitre'+numChap+'lecture'+numLect+'video" type="file" name="file"><br/>';
	txt+= '<label for="description'+'lecture'+numLect+'">Description :</label><br/><textarea id="chapitre'+numChap+'lecture'+numLect+'description" name="description"  ></textarea><hr/>';
	ecritureDivision('chapitre'+numChap+'lecture'+numLect, txt);
	tinymceStartWithId('#chapitre'+numChap+'lecture'+numLect+'description')
}


function creationNouvelleDivision(parent,id){
	var divNew = document.createElement('div');
	divNew.setAttribute('id', id);
	document.getElementById(parent).appendChild(divNew);
}

function ecritureDivision(id, txt) {
	
	var detailDiv = document.querySelector('#'+id);
	detailDiv.innerHTML = txt;
}