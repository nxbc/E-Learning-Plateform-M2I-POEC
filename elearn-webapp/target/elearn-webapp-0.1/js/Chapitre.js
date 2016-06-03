//Constructeur de Chapitre
function Chapitre(titreChapitre,numChapitre,idCours, lectures){
	if (!(this instanceof Chapitre)){
		 throw new Error;
	 }	
	this.titreChapitre=titreChapitre;
	this.numChapitre=numChapitre;
	this.idCours=idCours;
	this.lectures= new Array();
	this.lectures.push(lectures);
	
}

Chapitre.prototype.addLecture= function(lectureAdd){
	this.lectures.push(lectureAdd);
};


function creationChapitre(titreChapitre,numChapitre,idCours, lectures){
	if(Array.isArray(lectures)){
		chapitreCreer= new Chapitre(titreChapitre,numChapitre,idCours, lectures[0])
		for (var i = 1; i < lectures.length; i++) {
			chapitreCreer.addLecture(Lectures[i]);
		}
	}else{
		chapitreCreer= new Chapitre(titreChapitre,numChapitre,idCours, lectures)
	}
}