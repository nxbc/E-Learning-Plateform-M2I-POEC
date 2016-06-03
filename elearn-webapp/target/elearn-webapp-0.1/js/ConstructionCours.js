console.log('Le boutton creation de cours est appuyé');
ListenerLienCreationCours();

function ListenerLienCreationCours(){
	var linksConstructionCours = document.querySelector('#buttonSubmit a');
	linksConstructionCours.addEventListener('click', function(e) {
		e.preventDefault();
		coursJs= GetCours();
		jsCoursToJsonCours(coursJs);
	});
	
}
	
function GetLectures(numChap,numLecture){
	var titreLecture = document.getElementById('chapitre'+numChap+'lecture'+numLecture+'input');
	
	if(titreLecture==null) { 
		console.log("oups erreur dans lecture");
		return null;
	}
	titreLecture = document.getElementById('chapitre'+numChap+'lecture'+numLecture+'input').value;
	var linkVideo=document.getElementById('chapitre'+numChap+'lecture'+numLecture+'video').value;
	var textLecture=document.getElementById('chapitre'+numChap+'lecture'+numLecture+'description').value;
	var idChapitre='coucou'//WARNING
	lectureSend = new Lecture(titreLecture, numLecture,linkVideo, textLecture, idChapitre );
	return lectureSend;
	
		
}
function GetChapitres(numChap,idCours){
	var titreChapitre = document.getElementById('chapitre'+numChap+'input');
	
	if(titreChapitre==null) { 
		
		return null;
	}
	titreChapitre = document.getElementById('chapitre'+numChap+'input').value;
	
	var numLect=1;
	chapitreCreer= new Chapitre('test','test','test',new Lecture('test','test','test','test','test'));
	chapitreCreer= null;
	while(true){
		if(GetLectures(numChap,numLect)== null){
			console.log('dernier lecture du chapitre'+numChap);
			return chapitreCreer;
		}else if(numLect==1){
			chapitreCreer=new Chapitre(titreChapitre,numChap,idCours, GetLectures(numChap, numLect));
		}else{
			chapitreCreer.addLecture(GetLectures(numChap, numLect));
		}
		numLect++;
	}
	GetLectures(numChap);
	chapitreCreer= new Chapitre(titreChapitre,numChap,idCours, lectures);
	return chapitreCreer;	
	
}
function GetCours(){
	GetChapitres();
	var titre= document.getElementById('titre').value;
	console.log('titre = ', titre);
	var description= document.getElementById('description').value;
	console.log('description = ', description);
	var category= document.getElementById('category').value;
	console.log('category = ', category);
	var skill= document.getElementById('skill').value;
	console.log('skill = ', skill);
	var tag= document.getElementById('tag').value;
	console.log('tag = ', tag);
	var required= document.getElementById('required').value;
	console.log('required = ', required);
	var idUser= 24;
	console.log('idUser = ', idUser);
	var numChap=1;
	var idCours='GFAIM';// WARNING
	courCreer=null;
	while(true){
		if(GetChapitres(numChap,idCours)== null){
			console.log("dernier chapitre atteint");
			if(coursCreer==null){
				return console.log('coucou');
			}else{
				return coursCreer;
			}
		}else if(numChap==1){
			coursCreer=new Cours(titre,description,category,tag,skill,required,idUser,GetChapitres(numChap, idCours));
		}else{
			coursCreer.addChapitre(GetChapitres(numChap, idCours));
		}
		numChap++;
	}
	
}
	
