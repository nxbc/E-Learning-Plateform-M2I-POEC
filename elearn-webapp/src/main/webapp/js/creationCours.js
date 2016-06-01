creationTest();


function creationTest(){
	
	
	
	theNoirLecture = new Lecture('Lecture feuille de the Noir', 1,'video/feuilleTheNoir', 'Le the noir c fort sa apport des nouvelle forte',5 );
	feuilleTheChapitre= new Chapitre('Voyance dans les Feuilles de the',2,3,theNoirLecture );
	
	theVertLecture = new Lecture('Lecture feuille de the vert', 2,'video/feuilleTheVert', 'Le the vert sa ravigot sa apport des bonne nouvelle',5 );
	feuilleTheChapitre.addLecture(theVertLecture);
	
	astreBleuLecture = new Lecture('Lecture Astre bleu', 1,'video/astreBleu', 'Les astre bleu apport des nouvelle vital',4 );
	astreChapitre= new Chapitre('Voyance dans les Astre et leur couleur',1,3,astreBleuLecture );
	
	astreJauneLecture = new Lecture('Lecture Astre jaune', 2,'video/astreJaune', 'Les astre bleu apport des nouvelle d infidelite',4 );
	astreChapitre.addLecture(astreJauneLecture);
	
	astreRougeLecture = new Lecture('Lecture Astre rouge', 3,'video/astreRouge', 'Les astre bleu apport des nouvelle sur l amour',4 );
	astreChapitre.addLecture(astreRougeLecture);
	console.log(astreChapitre, 'ok');
	console.log(astreJauneLecture,'ok');
	
		
	voyanceCours= new Cours('Voyance','Apprent a reconnaitre les signe des astre et des feuilles de thé, grace a se cours le vole des papillon de nuit n aura plus de secret pour toi','Devellopement','','','',14,astreChapitre);
	voyanceCours.addChapitre(feuilleTheChapitre);
	console.log(voyanceCours, 'ok');
}



