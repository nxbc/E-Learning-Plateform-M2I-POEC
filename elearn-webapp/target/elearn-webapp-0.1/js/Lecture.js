//constructeur lecture
function Lecture(titreLecture, numLecture,linkVideo, textLecture, idChapitre ){
	 if (!(this instanceof Lecture)){
		 throw new Error;
	 }
	this.titreLecture=titreLecture;
	this.numLecture=numLecture;
	this.linkVideo=linkVideo;
	this.textLecture=textLecture;
	this.idChapitre=idChapitre;
	
}

