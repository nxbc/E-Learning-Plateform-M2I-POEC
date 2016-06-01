//constructeur lecture
function Lecture(titre, numLecture,linkVideo, textLecture, idChapitre ){
	 if (!(this instanceof Lecture)){
		 throw new Error;
	 }
	this.titre=titre;
	this.numLecture=numLecture;
	this.linkVideo=linkVideo;
	this.textLecture=textLecture;
	this.idChapitre=idChapitre;
	
}

