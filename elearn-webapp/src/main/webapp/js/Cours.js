// constructor Cours
function Cours(titre,description,category,tag,skill,required,idUser,chapitres) {
   	if (!(this instanceof Cours)){
		 throw new Error;
	 }
	this.titre = titre;
    this.description = description;
    this.category = category;
    this.tag = tag;
    this.skill= skill;
    this.required=required;
    this.idUser=idUser;
    this.chapitres=new Array();
   	this.chapitres.push(chapitres);
	
}

Cours.prototype.addChapitre= function(chapitreAdd){
	this.chapitres.push(chapitreAdd);
};

function creationCours(titre,description,category,tag,skill,required,idUser,chapitres){
	if(Array.isArray(chapitres)){
		coursCreer= new Cours(titre,description,category,tag,skill,required,idUser,chapitres[0])
		for (var i = 1; i < chapitres.length; i++) {
			coursCreer.addChapitre(Lectures[i]);
		}
	}else{
		coursCreer= new Cours(titre,description,category,tag,skill,required,idUser,chapitres)
	}
}














