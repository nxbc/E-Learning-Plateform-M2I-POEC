<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Création de cours</title>
  <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
  <script>tinymce.init({ selector:'textarea' });</script>
</head>
<body>
<h1>Création d'un cours</h1>
	
<form method="POST" action="http://localhost:8080/elearn-webapp-0.1/courseform">
	<div id="erreurcreate">
		<span class="erreur">${erreurs['erreur']}</span>
	</div>
	<div id="creationcour">
		<label for="titre">Titre * </label>
		<input id="titre" type="text" name="titre"><br/>
		
		<label for="description">Description * </label><br/>
		<textarea id="description" name="description"  ></textarea><br/>
		
		<label for="category">Catégorie * </label>
		<select id="category" name="category">
		 	<!-- <option style="display:none;" value="">&nbsp;</option> -->
			<option value="Developpement">Développement</option>
			<option value="Business">Business</option>
			<option value="InformatiqueLogiciels">Informatique et logiciels</option>
			<option value="ProductiviteBureautique">Productivité bureautique</option>
			<option value="DeveloppementPersonnel">Développement personnel</option>
			<option value="Design">Design</option>
			<option value="Marketing">Marketing</option>
			<option value="ModeDeVie">Mode de vie</option>
			<option value="Photographie">Photographie</option>
			<option value="Sante">Santé et bien-être</option>
			<option value="PreparationFormateur">Préparation des formateurs</option>
			<option value="Musique">Musique</option>
			<option value="Universitaire">Universitaire</option>
			<option value="Langue">Langues</option>
			<option value="PreparationTest">Préparation aux tests</option>
		</select><br/>
		
		<label for="skill">Compétence fournis par ce cours</label>
		<input id="skill" type="text" name="skill"><br/>
		
		<label for="tag">Tag</label>
		<input id="tag" type="text" name="tag"><br/>
		
		<label for="required">Compétence requise par le cours</label>
		<input id="required" type="text" name="required"><br/>
	<p>(*champs sont obligatoires)</p>
	
	
	
	
	</div>
	<div id="chapitrebutton">
		<a  href="">Créer un chapitre</a>
	</div>	
	<div id="chapitreliste">
		
	</div>		
	<br/>
	<br/>
	<br/>
	
	<div id="buttonSubmit">
		<a  href="">Créer un cours</a>
	</div>
	<br/>
	<input type="submit" value="Créer cours"> 


</form>

	 <script src="js/form_course.js"></script> 
	 <script src="js/Lecture.js"></script>
	 <script src="js/Chapitre.js"></script>
	 <script src="js/Cours.js"></script>
	 <script src="js/tinymcn.js"></script>
	 <script src="js/creationCours.js"></script>
	 <script src="js/ConstructionCours.js"></script>
	 <script src="js/jsToJson.js"></script>

</body>

</html>