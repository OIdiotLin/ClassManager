<?php

require_once 'Person.php';

function getPersonList() {
	$connection = mysqli_connect(DB_HOST, DB_USER, DB_PWD);
	if ($connection == null) {
		die('Could not connect: '.DB_HOST);
	}
	mysql_select_db(DB_NAME, $connection);
	$sql = "SELECT * from ".TBL_PERSONS." WHERE true";
	$result = mysql_fetch_assoc(mysql_query($sql, $connection));
	var_dump($result);
}

?>